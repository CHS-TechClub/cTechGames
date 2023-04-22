package com.smp.commands;

import com.ctechcore.CTechCore;
import com.ctechcore.teams.Team;
import com.smp.player.SMPPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveCommand implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) return false;
    Player player = (Player) sender;
    SMPPlayer smpPlayer = (SMPPlayer) CTechCore.getInstance().getTechPlayerManager().getTechPlayer(player.getUniqueId());

    if (smpPlayer.getTeam() == Team.NONE) {
      player.sendMessage(String.format("%s You must be in a team to leave one!", CTechCore.PREFIX));
      return true;
    }

    if (smpPlayer.getTeam().isLeader(player.getUniqueId())) {
      player.sendMessage(String.format("%s Leaders cannot leave the team! Delete your team using /g", CTechCore.PREFIX));
      return true;
    }

    smpPlayer.setTeam(Team.NONE);
    player.sendMessage(String.format("%s You have left the team!", CTechCore.PREFIX));
    return true;
  }
}
