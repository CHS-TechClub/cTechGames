package com.ctechcore.listeners;

import com.ctechcore.CTechCore;
import com.ctechcore.player.TechPlayer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener extends BaseListener {

  public PlayerChatListener(CTechCore plugin) {
    super(plugin);
  }

  @EventHandler
  public void playerChat(AsyncPlayerChatEvent e) {
    TechPlayer techPlayer = getCore().getTechPlayerManager().getTechPlayer(e.getPlayer().getUniqueId());
    e.setFormat(String.format("%s %s> ", techPlayer.getTitle(), ChatColor.WHITE) + e.getMessage());
  }
}
