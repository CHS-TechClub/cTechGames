package com.smp.player;

import com.ctechcore.player.TechPlayer;
import com.ctechcore.scoreboard.TechScoreboard;
import com.ctechcore.teams.Team;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SMPPlayer extends TechPlayer {

  private final TechScoreboard smpScoreboard;
  private Team invitedTo;

  public SMPPlayer(Player player) {
    super(player);

    this.smpScoreboard = new TechScoreboard(getPlayer(), ChatColor.BOLD + "CTechSMP");
    this.invitedTo = Team.NONE;
    detailScoreboard();
  }

  private void detailScoreboard() {
    this.smpScoreboard.setLine(15, " ");
    this.smpScoreboard.setLine(14, ChatColor.GREEN + "" + ChatColor.BOLD + "RANK");
    this.smpScoreboard.setLine(13, getRank().getColor() + getRank().getName());
    this.smpScoreboard.setLine(12, "  ");
    this.smpScoreboard.setLine(11, ChatColor.GOLD + "" + ChatColor.BOLD + "COINS");
    this.smpScoreboard.setLine(10, "" + getCoins());
    this.smpScoreboard.setLine(9, "   ");
    this.smpScoreboard.setLine(8, ChatColor.BLUE + "" + ChatColor.BOLD + "TEAM");
    this.smpScoreboard.setLine(7, getTeam().getColor() + getTeam().getName());
    this.smpScoreboard.showScoreboard();
  }

  private void updateScoreBoard() {
    this.smpScoreboard.setLine(13, getRank().getColor() + getRank().getName());
    this.smpScoreboard.setLine(10, "" + getCoins());
    this.smpScoreboard.setLine(7, getTeam().getColor() + getTeam().getName());
  }

  public boolean isInvitedTo(Team team) {
    return team == this.invitedTo;
  }

  public void setInvitedTo(Team invitedTo) {
    this.invitedTo = invitedTo;
  }

  @Override
  public void resetTitle() {
    super.resetTitle();
    updateScoreBoard();
  }

}
