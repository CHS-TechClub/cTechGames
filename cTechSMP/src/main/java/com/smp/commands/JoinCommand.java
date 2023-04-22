package com.smp.commands;

import com.ctechcore.CTechCore;
import com.ctechcore.teams.Team;
import com.smp.player.SMPPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) return false;
    Player player = (Player) sender;
    SMPPlayer smpPlayer = (SMPPlayer) CTechCore.getInstance().getTechPlayerManager().getTechPlayer(player.getUniqueId());

    if (args.length != 1) {
      player.sendMessage(String.format("%s Invalid arguments! /join name", CTechCore.PREFIX));
      return true;
    }

    if (smpPlayer.getTeam() != Team.NONE) {
      player.sendMessage(String.format("%s You must leave your current group before joining a new one.", CTechCore.PREFIX));
      return true;
    }

    Team team = CTechCore.getInstance().getTeamManager().getTeamByName(args[0]);
    if (team == Team.NONE) {
      player.sendMessage(String.format("%s Invalid team!.", CTechCore.PREFIX));
      return true;
    }

    if (!smpPlayer.isInvitedTo(team)) {
      player.sendMessage(String.format("%s You must be invited before joining a team!", CTechCore.PREFIX));
      return true;
    }

    smpPlayer.setTeam(team);
    smpPlayer.setInvitedTo(Team.NONE);
    player.sendMessage(String.format("%s You have joined %s!", CTechCore.PREFIX, team.getName()));

    return true;
  }
}
