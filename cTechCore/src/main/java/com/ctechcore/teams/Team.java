package com.ctechcore.teams;

import com.ctechcore.CTechCore;
import com.ctechcore.player.TechPlayer;
import org.bukkit.ChatColor;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

public class Team {

  //Use Hashtable for fast #contains
  private final Hashtable<UUID, TechPlayer> players;
  private String name;
  private ChatColor color;
  private UUID leader;

  public static final Team NONE = new Team("NONE", ChatColor.GRAY);
  public static final Team RED = new Team("RED", ChatColor.RED);
  public static final Team YELLOW = new Team("YELLOW", ChatColor.YELLOW);
  public static final Team GREEN = new Team("GREEN", ChatColor.GREEN);
  public static final Team BLUE = new Team("BLUE", ChatColor.BLUE);

  public Team(String name, ChatColor color) {
    this.name = name;
    this.color = color;
    this.players = new Hashtable<>();
    this.leader = null; //this is for like mini games
  }

  public Team(String name, ChatColor color, UUID leader) {
    this.name = name;
    this.color = color;
    this.players = new Hashtable<>();
    this.leader = leader;
    CTechCore.getInstance().getTeamManager().addTeam(this);
  }

  public boolean isLeader(UUID uuid) {
    return uuid.equals(leader);
  }

  public void setLeader(UUID uuid) {
    this.leader = uuid;
  }

  public UUID getLeader() {
    return leader;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ChatColor getColor() {
    return color;
  }

  public void setColor(ChatColor color) {
    this.color = color;
    CTechCore.getInstance().getDatabaseManager().saveTeam(this);
    for (TechPlayer techPlayer : getPlayers()) techPlayer.resetTitle();
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
