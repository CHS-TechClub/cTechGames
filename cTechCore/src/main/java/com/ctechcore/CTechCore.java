package com.ctechcore;

import org.bukkit.plugin.java.JavaPlugin;

public class CTechCore extends JavaPlugin {

  private static CTechCore INSTANCE;

  @Override
  public void onEnable() {
    getLogger().info("cTechCore started!");
    INSTANCE = this;
  }

  public static CTechCore getInstance() {
    return INSTANCE;
  }
}
