package com.ctechcore.rank;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public enum Rank {

  COUNCIL("COUNCIL", ChatColor.RED, new ItemStack(Material.NETHERITE_BLOCK), 0),
  JRCOUNCIL("JR. COUNCIL", ChatColor.GOLD, new ItemStack(Material.DIAMOND_BLOCK), 1),
  NONE("", ChatColor.GRAY, new ItemStack(Material.BARRIER), 2);

  private final String name;
  private final ChatColor color;
  private final ItemStack item;
  private final int permissionLevel;
  private final static HashMap<String, Rank> ranks = new HashMap<>();

  Rank(String name, ChatColor color, ItemStack item, int permissionLevel) {
    this.name = name;
    this.color = color;
    this.item = item;
    this.permissionLevel = permissionLevel;
  }

  public String getName() {
    return name;
  }

  public ChatColor getColor() {
    return color;
  }

  public ItemStack getItem() {
    return item;
  }

  public int getPermissionLevel() {
    return permissionLevel;
  }

  public String getRankChatFormat() {
    return String.format("%s%s%s", getColor(), ChatColor.BOLD, getName());
  }

  public static Rank getRankByName(String name) {
    return ranks.getOrDefault(name.toUpperCase(), Rank.NONE);
  }

  static {
    //already upper case
    for (Rank rank : values()) ranks.put(rank.getName(), rank);
  }

}
