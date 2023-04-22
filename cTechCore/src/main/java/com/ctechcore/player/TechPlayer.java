package com.ctechcore.player;

import com.ctechcore.CTechCore;
import com.ctechcore.kits.Kit;
import com.ctechcore.mongo.TechDatabaseManager;
import com.ctechcore.rank.Rank;
import com.ctechcore.teams.Team;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.util.concurrent.CompletableFuture;

public class TechPlayer {

  private final Player player;
  private Team team;
  private Rank rank;
  private Kit kit;
  private String title;
  private int coins;

  public TechPlayer(Player player) {
    this.player = player;
    this.team = Team.NONE; //default to no team
    this.rank = Rank.NONE;
    this.coins = 0;

    pullPlayerData();
  }

  private void pullPlayerData() {
    TechDatabaseManager tdm = CTechCore.getInstance().getDatabaseManager();
    CompletableFuture.supplyAsync(() -> tdm.getTechPlayerData(player.getUniqueId())).thenAccept(result -> {
      if (result == null) {
        tdm.saveTechPlayer(this);
        resetTitle();
        return;
      }

      if (result.get("coins") != null) this.coins = result.getInteger("coins");

      String rank = (String) result.get("rank");
      this.rank = Rank.getRankByName(rank);

      String team = (String) result.get("team");
      setTeam(CTechCore.getInstance().getTeamManager().getTeamByName(team));

      resetTitle();
    });

  }

  public void resetTitle() {
    String title = String.format("%s[%s%s%s] %s%s%s%s %s",
        ChatColor.GRAY,
        getTeam().getColor(),
        getTeam().getName(),
        ChatColor.GRAY,
        getRank().getColor(),
        ChatColor.BOLD,
        getRank().getName(),
        ChatColor.RESET,
        getPlayer().getName()
    );
    this.title = title;
    getPlayer().setPlayerListName(title);
  }

  public Player getPlayer() {
    return player;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team.removePlayer(this);
    this.team = team;
    this.team.addPlayer(this);
    resetTitle();
    CTechCore.getInstance().getDatabaseManager().saveTechPlayer(this);
  }

  public Rank getRank() {
    return rank;
  }

  public void setRank(Rank rank) {
    this.rank = rank;
    resetTitle();
    CTechCore.getInstance().getDatabaseManager().saveTechPlayer(this);
  }

  public Kit getKit() {
    return kit;
  }

  public void setKit(Kit kit) {
    this.kit = kit;
  }

  public String getTitle() {
    return title;
  }

  public int getCoins() {
    return coins;
  }

  public void setCoins(int coins) {
    this.coins = coins;
    CTechCore.getInstance().getDatabaseManager().saveTechPlayer(this);
  }
}
