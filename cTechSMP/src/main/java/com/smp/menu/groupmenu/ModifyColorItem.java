package com.smp.menu.groupmenu;

import com.ctechcore.menu.InventoryItem;
import com.ctechcore.teams.Team;
import com.ctechcore.utils.ItemUtil;
import com.smp.menu.colorchangemenu.ColorChangeMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import java.util.Arrays;

public class ModifyColorItem implements InventoryItem {

  private int slot;
  private final Team team;
  private static final ItemStack ITEM = ItemUtil.createItem(
      Material.GRAY_DYE,
      ChatColor.BLUE + "" + ChatColor.BOLD + "Change Color",
      Arrays.asList(ChatColor.WHITE + "Click to modify the group color.", "", ChatColor.WHITE + "Only leaders can modify colors!"));

  public ModifyColorItem(Team team) {
    this.team = team;
  }

  public Team getTeam() {
    return team;
  }

  @Override
  public int getSlot() {
    return slot;
  }

  @Override
  public void setSlot(int slot) {
    this.slot = slot;
  }

  @Override
  public ItemStack getItem() {
    return ITEM;
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
    if (!getTeam().isLeader(e.getWhoClicked().getUniqueId())) return;

    e.getWhoClicked().openInventory(new ColorChangeMenu(getTeam()).getInventory());
  }
}
