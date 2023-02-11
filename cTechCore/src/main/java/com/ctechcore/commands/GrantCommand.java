package com.ctechcore.commands;

import com.ctechcore.CTechCore;
import com.ctechcore.menu.grantmenu.GrantMenu;
import com.ctechcore.player.TechPlayer;
import com.ctechcore.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GrantCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) return false;

    Player player = (Player) sender;
    TechPlayer techPlayer = CTechCore.getInstance().getTechPlayerManager().getTechPlayer(player.getUniqueId());
    if (techPlayer == null) return false;
    if (techPlayer.getRank() != Rank.COUNCIL && !player.isOp()) {
      player.sendMessage(String.format("%s You lack permission to execute this command!", CTechCore.PREFIX));
      return true;
    }

    if (args.length == 0) {
      player.sendMessage(String.format("%s You must provide a name!", CTechCore.PREFIX));
      return true;
    }

    Player rankedPlayer = Bukkit.getPlayer(args[0]);
    if (rankedPlayer == null) {
      player.sendMessage(String.format("%s Player not found!", CTechCore.PREFIX));
      return true;
    }

    player.openInventory(new GrantMenu(CTechCore.getInstance(), rankedPlayer).getInventory());

    return true;
  }

}
