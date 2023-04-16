package com.smp;

import org.bukkit.plugin.java.JavaPlugin;

public class SMP extends JavaPlugin {

  private static SMP INSTANCE;

  @Override
  public void onEnable() {
    INSTANCE = this;

    getLogger().info("SMP Starting!");
  }

  @Override
  public void onDisable() {
    getLogger().info("SMP Stopping!");
  }

  public static SMP getInstance() {
    return INSTANCE;
  }
}
