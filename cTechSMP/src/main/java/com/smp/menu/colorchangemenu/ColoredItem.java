package com.smp.menu.colorchangemenu;

import com.ctechcore.menu.InventoryItem;
import com.ctechcore.teams.Team;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ColoredItem implements InventoryItem {

  private final Team team;
  private final ItemStack item;
  private final ChatColor color;

  public ColoredItem(Team team, ItemStack item, ChatColor color) {
    this.team = team;
    this.item = item;
    this.color = color;
  }

  public Team getTeam() {
    return team;
  }

  @Override
  public ItemStack getItem() {
    return item;
  }

  public ChatColor getColor() {
    return color;
  }

  @Override
  public void onItemLeftClick(InventoryClickEvent e) {

  }

  @Override
  public void onItemShiftLeftClick(InventoryClickEvent e) {

  }

  @Override
  public void onItemRightClick(InventoryClickEvent e) {

  }

  @Override
  public void onItemShiftRightClick(InventoryClickEvent e) {

  }

  @Override
  public void onItemClick(InventoryClickEvent e) {
    getTeam().setColor(getColor());
    Player player = (Player) e.getWhoClicked();
    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 3);
    player.closeInventory();
  }
}
