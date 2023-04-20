package com.smp.listeners;

import com.ctechcore.CTechCore;
import com.ctechcore.listeners.BaseListener;
import com.ctechcore.player.TechPlayer;
import com.ctechcore.rank.Rank;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class SpawnListener extends BaseListener {

  public SpawnListener() {
    super(CTechCore.getInstance());
  }

  //x z
  //-10 10
  //30 -30
  @EventHandler
  public void blockPlaceEvent(BlockPlaceEvent e) {
    Block block = e.getBlock();
    Location blockLoc = block.getLocation();
    int blockX = blockLoc.getBlockX();
    int blockZ = blockLoc.getBlockZ();
    if (blockX >= -10 && blockX <= 30 && blockZ <= 10 && blockZ >= -30) {
      TechPlayer techPlayer = getCore().getTechPlayerManager().getTechPlayer(e.getPlayer().getUniqueId());
      if (techPlayer.getRank() != Rank.NONE) return;
      e.setCancelled(true);
      e.getPlayer().sendMessage(CTechCore.PREFIX + " You cannot place blocks in spawn!");
    }
  }

  @EventHandler
  public void blockBreakEvent(BlockBreakEvent e) {
    Block block = e.getBlock();
    Location blockLoc = block.getLocation();
    int blockX = blockLoc.getBlockX();
    int blockZ = blockLoc.getBlockZ();
    if (blockX >= -10 && blockX <= 30 && blockZ <= 10 && blockZ >= -30) {
      TechPlayer techPlayer = getCore().getTechPlayerManager().getTechPlayer(e.getPlayer().getUniqueId());
      if (techPlayer.getRank() != Rank.NONE) return;
      e.setCancelled(true);
      e.getPlayer().sendMessage(CTechCore.PREFIX + " You cannot break blocks in spawn!");
    }
  }

  @EventHandler
  public void entityDamageEvent(EntityDamageEvent e) {
    Location loc = e.getEntity().getLocation();
    int entityX = loc.getBlockX();
    int entityZ = loc.getBlockZ();
    if (entityX >= -10 && entityX <= 30 && entityZ <= 10 && entityZ >= -30) e.setCancelled(true);
  }

}
