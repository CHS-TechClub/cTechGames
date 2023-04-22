package com.ctechcore.menu;

import com.ctechcore.CTechCore;
import com.ctechcore.listeners.BaseListener;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.Hashtable;

public class InventoryMenu extends BaseListener {

  private final Inventory inventory;
  private final Hashtable<Integer, InventoryItem> itemTable;

  public InventoryMenu(CTechCore core, String name, int slots) {
    super(core);
    if (slots <= 0) slots = 9;
    if (slots % 9 != 0) throw new Error("Inventory slots must be in intervals of 9!");
    this.inventory = Bukkit.createInventory(null, slots, name);
    this.itemTable = new Hashtable<>();
  }

  private Hashtable<Integer, InventoryItem> getItemTable() {
    return itemTable;
  }

  public Inventory getInventory() {
    return inventory;
  }

  public void addUnClickableItem(int slot, ItemStack item) {
    this.inventory.setItem(slot, item);
  }

  public void addInventoryItem(int slot, InventoryItem invItem) {
    invItem.setSlot(slot);
    this.inventory.setItem(slot, invItem.getItem());
    this.itemTable.put(slot, invItem);
  }

  public void removeInventoryItem(InventoryItem invItem) {
    this.itemTable.remove(invItem.getSlot());
  }

  @EventHandler
  public void onInventoryClick(InventoryClickEvent e) {
    if (e.getInventory() != getInventory()) return;
    e.setCancelled(true);
    ItemStack clickedItem = e.getCurrentItem();
    if (clickedItem == null) return;
    Hashtable<Integer, InventoryItem> itemTable = getItemTable();
    InventoryItem invItem = itemTable.get(e.getSlot());
    if (invItem == null) return;

    invItem.onItemClick(e);

    switch (e.getClick()) {
      case LEFT -> invItem.onItemLeftClick(e);
      case SHIFT_LEFT -> invItem.onItemShiftLeftClick(e);
      case RIGHT -> invItem.onItemRightClick(e);
      case SHIFT_RIGHT -> invItem.onItemShiftRightClick(e);
    }

  }

  @EventHandler
  public void inventoryCloseEvent(InventoryCloseEvent e) {
    if (e.getInventory() != getInventory()) return;

    HandlerList.unregisterAll(this);
    getCore().getLogger().info("[LISTENER] Unregistered " + getClass().getSimpleName());
  }

}
