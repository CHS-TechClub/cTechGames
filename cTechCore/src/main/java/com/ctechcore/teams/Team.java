package com.ctechcore.teams;

import com.ctechcore.player.TechPlayer;
import org.bukkit.ChatColor;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

public class Team {

  //Use Hashtable for fast #contains
  private final Hashtable<UUID, TechPlayer> players;
  private final String name;
  private final ChatColor color;

  public static final Team NONE = new Team("NONE", ChatColor.YELLOW);

  public Team(String name, ChatColor color) {
    this.name = name;
    this.color = color;
    this.players = new Hashtable<>();
  }

  public String getName() {
    return name;
  }

  public ChatColor getColor() {
    return color;
  }

  public Collection<TechPlayer> getPlayers() {
    return this.players.values();
  }

  public void addPlayer(TechPlayer techPlayer) {
    this.players.put(techPlayer.getPlayer().getUniqueId(), techPlayer);
  }

  public void removePlayer(TechPlayer techPlayer) {
    this.players.remove(techPlayer.getPlayer().getUniqueId());
  }

  public void removePlayer(UUID uuid) {
    this.players.remove(uuid);
  }

  public TechPlayer getPlayer(UUID uuid) {
    return this.players.get(uuid);
  }

  public void addPlayers(List<TechPlayer> techPlayers) {
    for (TechPlayer techPlayer : techPlayers) this.players.put(techPlayer.getPlayer().getUniqueId(), techPlayer);
  }

  public void clearTeam() {
    this.players.clear();
  }

  public boolean isOnTeam(UUID uuid) {
    return this.players.contains(uuid);
  }

}
