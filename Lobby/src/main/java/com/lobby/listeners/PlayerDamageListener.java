package com.lobby.listeners;

import com.ctechcore.CTechCore;
import com.ctechcore.listeners.BaseListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener extends BaseListener {

  public PlayerDamageListener(CTechCore plugin) {
    super(plugin);
  }

  @EventHandler
  public void playerDamageEvent(EntityDamageEvent e) {
    if (e.getEntity() instanceof Player) e.setCancelled(true);
  }

}
