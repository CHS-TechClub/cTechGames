package com.smp.listeners;

import com.ctechcore.CTechCore;
import com.ctechcore.events.CreateTechPlayerEvent;
import com.ctechcore.listeners.BaseListener;
import com.ctechcore.player.TechPlayerManager;
import com.smp.player.SMPPlayer;
import org.bukkit.event.EventHandler;

public class CreateTechPlayerListener extends BaseListener {
  public CreateTechPlayerListener(CTechCore plugin) {
    super(plugin);
  }

  @EventHandler
  public void techPlayerCreateEvent(CreateTechPlayerEvent e) {
    e.setCancelled(true);

    SMPPlayer smpPlayer = new SMPPlayer(e.getPlayer());
    TechPlayerManager tpm = getCore().getTechPlayerManager();
    tpm.addPlayer(smpPlayer);

  }

}
