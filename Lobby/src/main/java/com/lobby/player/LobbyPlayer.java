package com.lobby.player;

import com.ctechcore.player.TechPlayer;
import com.ctechcore.rank.Rank;
import com.ctechcore.scoreboard.TechScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LobbyPlayer extends TechPlayer {

  private final TechScoreboard lobbyScoreboard;

  public LobbyPlayer(Player player) {
    super(player);

    this.lobbyScoreboard = new TechScoreboard(getPlayer(), ChatColor.BOLD + "Welcome to CTechGames!");
    detailScoreboard();
  }

  private void detailScoreboard() {
    this.lobbyScoreboard.setLine(15, " ");
    this.lobbyScoreboard.setLine(14, ChatColor.GREEN + "" + ChatColor.BOLD + "RANK");
    this.lobbyScoreboard.setLine(13, getRank().getColor() + getRank().getName());
    this.lobbyScoreboard.setLine(12, "  ");
    this.lobbyScoreboard.setLine(11, ChatColor.GOLD + "" + ChatColor.BOLD + "COINS");
    this.lobbyScoreboard.setLine(10, "" + getCoins());
    this.lobbyScoreboard.showScoreboard();
  }

  private void updateScoreBoard() {
    this.lobbyScoreboard.setLine(13, getRank().getColor() + getRank().getName());
    this.lobbyScoreboard.setLine(10, "" + getCoins());
  }

  @Override
  public void resetTitle() {
    super.resetTitle();
    updateScoreBoard();
  }

}
