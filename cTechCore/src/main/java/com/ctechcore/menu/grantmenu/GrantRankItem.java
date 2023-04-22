package com.ctechcore.menu.grantmenu;

import com.ctechcore.CTechCore;
import com.ctechcore.menu.InventoryItem;
import com.ctechcore.player.TechPlayer;
import com.ctechcore.rank.Rank;
import com.ctechcore.utils.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GrantRankItem implements InventoryItem {

  private final Player player;
  private final Rank rank;
  int slot;

  public GrantRankItem(Player player, Rank rank) {
    this.player = player;
    this.rank = rank;
  }

  public Player getPlayer() {
    return player;
  }

  public Rank getRank() {
    return rank;
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
    return ItemUtil.createItem(getRank().getItem(), getRank().getRankChatFormat());
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
    Rank rank = getRank();
    Player player = getPlayer();
    TechPlayer techPlayer = CTechCore.getInstance().getTechPlayerManager().getTechPlayer(player.getUniqueId());
    techPlayer.setRank(rank);
    player.sendMessage(String.format("%sCTECH> %sYou have been granted %s%s%S rank!", ChatColor.BLUE, ChatColor.GRAY, rank.getColor(), rank.getName(), ChatColor.GRAY));
    e.getWhoClicked().closeInventory();
  }
}
