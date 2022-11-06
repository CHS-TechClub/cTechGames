package com.ctechcore.menu;

import com.ctechcore.CTechCore;
import com.ctechcore.listeners.BaseListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.Hashtable;

public class InventoryMenu extends BaseListener {

  private final Inventory inventory;
  private final Hashtable<Material, InventoryItem> itemTable;

  public InventoryMenu(CTechCore core, String name, int slots) {
    super(core);
    if (slots <= 0) slots = 9;
    if (slots % 9 != 0) throw new Error("Inventory slots must be in intervals of 9!");
    this.inventory = Bukkit.createInventory(null, slots, name);
    this.itemTable = new Hashtable<>();
  }

  private Hashtable<Material, InventoryItem> getItemTable() {
    return itemTable;
  }

  public Inventory getInventory() {
    return inventory;
  }

  public void addUnClickableItem(int slot, ItemStack item) {
    this.inventory.setItem(slot, item);
  }

  public void addInventoryItem(int slot, InventoryItem invItem) {
    this.inventory.setItem(slot, invItem.getItem());
    this.itemTable.putIfAbsent(invItem.getItem().getType(), invItem);
  }

  public void removeInventoryItem(InventoryItem invItem) {
    this.itemTable.remove(invItem.getItem().getType());
  }

  @EventHandler
  public void onInventoryClick(InventoryClickEvent e) {
    if (e.getInventory() != getInventory()) return;
    e.setCancelled(true);
    ItemStack clickedItem = e.getCurrentItem();
    if (clickedItem == null) return;
    Hashtable<Material, InventoryItem> itemTable = getItemTable();
    InventoryItem invItem = itemTable.get(clickedItem.getType());
    if (invItem == null) return;

    invItem.onItemClick(e);

    switch (e.getClick()) {
      case LEFT -> invItem.onItemLeftClick(e);
      case SHIFT_LEFT -> invItem.onItemShiftLeftClick(e);
      case RIGHT -> invItem.onItemRightClick(e);
      case SHIFT_RIGHT -> invItem.onItemShiftRightClick(e);
    }

  }

}
