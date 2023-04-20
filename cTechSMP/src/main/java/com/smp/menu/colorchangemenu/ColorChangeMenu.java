package com.smp.menu.colorchangemenu;

import com.ctechcore.CTechCore;
import com.ctechcore.menu.InventoryMenu;
import com.ctechcore.teams.Team;
import com.ctechcore.utils.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ColorChangeMenu extends InventoryMenu {

  public ColorChangeMenu(Team team) {
    super(CTechCore.getInstance(), "Change Team Color", 9);

    addInventoryItem(0, new ColoredItem(team, ItemUtil.createItem(new ItemStack(Material.RED_DYE), ChatColor.RED + "" + ChatColor.BOLD + "RED"), ChatColor.RED));
    addInventoryItem(1, new ColoredItem(team, ItemUtil.createItem(new ItemStack(Material.BLUE_DYE), ChatColor.BLUE + "" + ChatColor.BOLD + "BLUE"), ChatColor.BLUE));
    addInventoryItem(2, new ColoredItem(team, ItemUtil.createItem(new ItemStack(Material.YELLOW_DYE), ChatColor.YELLOW + "" + ChatColor.BOLD + "YELLOW"), ChatColor.YELLOW));
    addInventoryItem(3, new ColoredItem(team, ItemUtil.createItem(new ItemStack(Material.GREEN_DYE), ChatColor.GREEN + "" + ChatColor.BOLD + "GREEN"), ChatColor.GREEN));
    addInventoryItem(4, new ColoredItem(team, ItemUtil.createItem(new ItemStack(Material.MAGENTA_DYE), ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "MAGENTA"), ChatColor.LIGHT_PURPLE));
    addInventoryItem(5, new ColoredItem(team, ItemUtil.createItem(new ItemStack(Material.WHITE_DYE), ChatColor.WHITE + "" + ChatColor.BOLD + "WHITE"), ChatColor.WHITE));
    addInventoryItem(6, new ColoredItem(team, ItemUtil.createItem(new ItemStack(Material.CYAN_DYE), ChatColor.AQUA + "" + ChatColor.BOLD + "AQUA"), ChatColor.AQUA));
    addInventoryItem(7, new ColoredItem(team, ItemUtil.createItem(new ItemStack(Material.ORANGE_DYE), ChatColor.GOLD + "" + ChatColor.BOLD + "GOLD"), ChatColor.GOLD));
  }

}
