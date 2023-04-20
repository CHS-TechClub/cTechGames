package com.smp.menu.groupmenu;

import com.ctechcore.CTechCore;
import com.ctechcore.menu.InventoryItem;
import com.ctechcore.mongo.TechDatabaseManager;
import com.ctechcore.player.TechPlayer;
import com.ctechcore.teams.Team;
import com.ctechcore.utils.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class DeleteGroupItem implements InventoryItem {

  private final Team team;
  private static final ItemStack ITEM = ItemUtil.createItem(
      Material.TNT,
      ChatColor.RED + "" + ChatColor.BOLD + "Delete Group",
      Arrays.asList(ChatColor.WHITE + "Click to delete group.", "", ChatColor.WHITE + "Only leaders can delete groups!"));

  public DeleteGroupItem(Team team) {
    this.team = team;
  }

  public Team getTeam() {
    return team;
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

    for (TechPlayer techPlayer : getTeam().getPlayers()) techPlayer.setTeam(Team.NONE);


    e.getWhoClicked().closeInventory();
    e.getWhoClicked().sendMessage(CTechCore.PREFIX + " Your team has been deleted!");
    CTechCore.getInstance().getTeamManager().deleteTeam(getTeam());
  }
}
