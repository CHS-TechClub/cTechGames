package com.smp.commands;

import com.ctechcore.CTechCore;
import com.ctechcore.teams.Team;
import com.smp.player.SMPPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateGroupCommand implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) return false;
    Player player = (Player) sender;
    SMPPlayer smpPlayer = (SMPPlayer) CTechCore.getInstance().getTechPlayerManager().getTechPlayer(player.getUniqueId());

    if (args.length != 1) {
      player.sendMessage(String.format("%s Invalid arguments! /creategroup name", CTechCore.PREFIX));
      return true;
    }

    if (smpPlayer.getTeam() != Team.NONE) {
      player.sendMessage(String.format("%s You must leave your current group before making a new one.", CTechCore.PREFIX));
      return true;
    }

    Team team = new Team(args[0], ChatColor.GREEN, player.getUniqueId());
    smpPlayer.setTeam(team);
    player.sendMessage(String.format("%s Created group: %s.", CTechCore.PREFIX, args[0]));

    return true;
  }
}
