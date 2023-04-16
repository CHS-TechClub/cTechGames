package com.smp.commands;

import com.ctechcore.CTechCore;
import com.ctechcore.teams.Team;
import com.smp.player.SMPPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GroupCommand implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) return false;
    SMPPlayer smpPlayer = (SMPPlayer) CTechCore.getInstance().getTechPlayerManager().getTechPlayer(((Player) sender).getUniqueId());
    if (smpPlayer.getTeam() == Team.NONE) {
      if (args.length == 0) {
        smpPlayer.getPlayer().sendMessage(String.format("%sError>%s You need to be in a group or provide a group name to use this command."));
      }
    }


    return true;
  }
}
