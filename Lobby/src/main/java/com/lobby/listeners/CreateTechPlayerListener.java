package com.lobby.listeners;

import com.ctechcore.CTechCore;
import com.ctechcore.events.CreateTechPlayerEvent;
import com.ctechcore.listeners.BaseListener;
import com.ctechcore.player.TechPlayerManager;
import com.lobby.player.LobbyPlayer;
import org.bukkit.event.EventHandler;

public class CreateTechPlayerListener extends BaseListener {
  public CreateTechPlayerListener(CTechCore plugin) {
    super(plugin);
  }

  @EventHandler
  public void techPlayerCreateEvent(CreateTechPlayerEvent e) {
    e.setCancelled(true);

    LobbyPlayer lobbyPlayer = new LobbyPlayer(e.getPlayer());
    TechPlayerManager tpm = getCore().getTechPlayerManager();
    tpm.addPlayer(lobbyPlayer);

  }

}
