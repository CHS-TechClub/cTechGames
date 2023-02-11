package com.lobby;

import com.ctechcore.CTechCore;
import com.lobby.listeners.CreateTechPlayerListener;
import com.lobby.listeners.PlayerDamageListener;
import com.lobby.listeners.WeatherListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobby extends JavaPlugin {

  private static Lobby INSTANCE;

  @Override
  public void onEnable() {
    INSTANCE = this;

    registerListeners();
  }

  private void registerListeners() {
    CTechCore core = CTechCore.getInstance();
    new PlayerDamageListener(core);
    new WeatherListener(core);
    new CreateTechPlayerListener(core);
  }

  public static Lobby getInstance() {
    return INSTANCE;
  }
}
