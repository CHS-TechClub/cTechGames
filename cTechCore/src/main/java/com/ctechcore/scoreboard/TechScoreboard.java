package com.ctechcore.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import java.util.HashMap;

public class TechScoreboard {

  private final Player player;
  private final Scoreboard sb;
  private final Objective o;
  private final HashMap<Integer, String> entries;

  public TechScoreboard(Player player, String title) {
    this.player = player;
    this.sb = Bukkit.getScoreboardManager().getNewScoreboard();
    this.o = this.sb.registerNewObjective("CTECH", "CTECH");
    this.o.setDisplayName(title);
    this.o.setDisplaySlot(DisplaySlot.SIDEBAR);
    this.entries = new HashMap<>();
    initializeEntries();
  }

  private void initializeEntries() {
    Objective o = getObjective();
    for (int i = 15; i >= 0; i--) {
      this.entries.put(i, "");
      o.getScore("").setScore(i);
    }
  }

  public void setTitle(String value) {
    Objective o = getObjective();
    o.setDisplayName(value);
  }

  public void setLine(int lineNumber, String value) {
    if (lineNumber < 0 || lineNumber > 15) return;
    Objective o = getObjective();
    String oldValue = getEntries().get(lineNumber);
    o.getScoreboard().resetScores(oldValue);
    o.getScore(value).setScore(lineNumber);
    this.entries.remove(lineNumber);
    this.entries.put(lineNumber, value);
  }

  private Player getPlayer() {
    return player;
  }

  public void showScoreboard() {
    getPlayer().setScoreboard(getBukkitScoreboard());
  }

  public void hideScoreboard() {
    getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
  }

  public Scoreboard getBukkitScoreboard() {
    return sb;
  }

  private Objective getObjective() {
    return o;
  }

  private HashMap<Integer, String> getEntries() {
    return entries;
  }
}
