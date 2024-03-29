package com.ctechcore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CreateTechPlayerEvent extends Event implements Cancellable {

  private boolean cancelled;
  private final Player player;
  private static final HandlerList handlers = new HandlerList();

  public CreateTechPlayerEvent(Player player) {
    this.player = player;
    this.cancelled = false;
  }

  public Player getPlayer() {
    return player;
  }

  @Override
  public boolean isCancelled() {
    return cancelled;
  }

  @Override
  public void setCancelled(boolean cancel) {
    this.cancelled = cancel;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }
}
