package com.ctechcore.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public interface InventoryItem {
  ItemStack getItem();
  void onItemLeftClick(InventoryClickEvent e);
  void onItemShiftLeftClick(InventoryClickEvent e);
  void onItemRightClick(InventoryClickEvent e);
  void onItemShiftRightClick(InventoryClickEvent e);
  void onItemClick(InventoryClickEvent e);
}
