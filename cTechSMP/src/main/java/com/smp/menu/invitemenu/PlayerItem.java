package com.smp.menu.invitemenu;

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

public class PlayerItem implements InventoryItem {

  private final SMPPlayer smpPlayer;
  private final Team team;
  private final ItemStack item;
  private int slot;

  public PlayerItem(SMPPlayer smpPlayer, Team team) {
    this.smpPlayer = smpPlayer;
    this.team = team;
    this.item = ItemUtil.createItem(new ItemStack(Material.PLAYER_HEAD), smpPlayer.getPlayer().getName());
    SkullMeta sm = (SkullMeta) this.item.getItemMeta();
    sm.setDisplayName(ChatColor.YELLOW + smpPlayer.getPlayer().getName());
    sm.setLore(List.of(ChatColor.WHITE + "Click to invite player!"));
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
    getSmpPlayer().setInvitedTo(getTeam());
    getSmpPlayer().getPlayer().sendMessage(String.format("%s You have been invited to join %s! To join type: /join %s",
        CTechCore.PREFIX, getTeam().getName(), getTeam().getName()));

  }
}
