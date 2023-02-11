package com.ctechcore.kits.types;

import com.ctechcore.CTechCore;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

public interface Interact extends Listener {

  default void register() {
    CTechCore plugin = CTechCore.getInstance();
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  default void unregister() {
    HandlerList.unregisterAll(this);
  }

}
