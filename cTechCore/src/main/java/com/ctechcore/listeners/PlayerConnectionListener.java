package com.ctechcore.listeners;

import com.ctechcore.CTechCore;
import com.ctechcore.events.CreateTechPlayerEvent;
import com.ctechcore.player.TechPlayer;
import com.ctechcore.player.TechPlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener extends BaseListener {

  public PlayerConnectionListener(CTechCore plugin) {
    super(plugin);
  }

  @EventHandler
  public void playerJoinEvent(PlayerJoinEvent e) {
    Player player = e.getPlayer();
    e.setJoinMessage(String.format("%s%s[%s+%s] %s", ChatColor.RESET, ChatColor.GRAY, ChatColor.GREEN, ChatColor.GRAY, player.getName()));

    CreateTechPlayerEvent event = new CreateTechPlayerEvent(player);
    getCore().getServer().getPluginManager().callEvent(event);
    if (event.isCancelled()) return;

    TechPlayerManager tpm = getCore().getTechPlayerManager();
    TechPlayer techPlayer = new TechPlayer(player);
    player.setPlayerListName(techPlayer.getTitle());
    tpm.addPlayer(techPlayer);
  }

  @EventHandler
  public void playerLeaveEvent(PlayerQuitEvent e) {
    Player player = e.getPlayer();
    e.setQuitMessage(String.format("%s%s[%s-%s] %s", ChatColor.RESET, ChatColor.GRAY, ChatColor.RED, ChatColor.GRAY, player.getName()));
    getCore().getTechPlayerManager().removePlayer(player.getUniqueId());
  }

}
