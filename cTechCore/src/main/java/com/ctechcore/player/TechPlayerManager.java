package com.ctechcore.player;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class TechPlayerManager {

  private final HashMap<UUID, TechPlayer> players;

  public TechPlayerManager() {
    this.players = new HashMap<>();
  }

  public void addPlayer(TechPlayer techPlayer) {
    this.players.put(techPlayer.getPlayer().getUniqueId(), techPlayer);
  }

  public TechPlayer getTechPlayer(UUID uuid) {
    return this.players.get(uuid);
  }

  public void removePlayer(UUID uuid) {
    this.players.remove(uuid);
  }

  public void removePlayer(TechPlayer techPlayer) {
    this.players.remove(techPlayer.getPlayer().getUniqueId());
  }

  public Collection<TechPlayer> getTechPlayers() {
    return players.values();
  }
}
