package com.smp.menu.groupmenu;

import com.ctechcore.CTechCore;
import com.ctechcore.menu.InventoryItem;
import com.ctechcore.teams.Team;
import com.ctechcore.utils.ItemUtil;
import com.smp.player.SMPPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class KickPlayerItem implements InventoryItem {

  private int slot;
  private final SMPPlayer smpPlayer;
  private final Team team;
  private final ItemStack item;

  public KickPlayerItem(SMPPlayer smpPlayer, Team team) {
    this.smpPlayer = smpPlayer;
    this.team = team;
    this.item = ItemUtil.createItem(new ItemStack(Material.PLAYER_HEAD), smpPlayer.getPlayer().getName());
    SkullMeta sm = (SkullMeta) this.item.getItemMeta();
    sm.setDisplayName(ChatColor.YELLOW + smpPlayer.getPlayer().getName());
    sm.setLore(List.of(ChatColor.WHITE + "Click to kick player!"));
    sm.setOwnerProfile(smpPlayer.getPlayer().getPlayerProfile());
    this.item.setItemMeta(sm);
  }

  public SMPPlayer getSmpPlayer() {
    return smpPlayer;
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
    return item;
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
    if (getTeam().isLeader(getSmpPlayer().getPlayer().getUniqueId())) return;
    getSmpPlayer().setTeam(Team.NONE);
    e.getWhoClicked().closeInventory();
    e.getWhoClicked().sendMessage(CTechCore.PREFIX + " The player has been removed from your team!");
  }

}
