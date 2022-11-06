package com.ctechcore.listeners;

import com.ctechcore.CTechCore;
import org.bukkit.event.Listener;

public class BaseListener implements Listener {

  private final CTechCore core;

  public BaseListener(CTechCore plugin) {
    this.core = plugin;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
    plugin.getLogger().info(String.format("[LISTENER] Registered listener: %s", this.getClass().getSimpleName()));
  }

  public CTechCore getCore() {
    return core;
  }

}
