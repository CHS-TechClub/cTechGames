package com.lobby.listeners;

import com.ctechcore.CTechCore;
import com.ctechcore.listeners.BaseListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener extends BaseListener {
  public WeatherListener(CTechCore plugin) {
    super(plugin);
  }

  @EventHandler
  public void weatherChangeEvent(WeatherChangeEvent e) {
    //WeatherChangeEvent#toWeatherState returns true if it is not sunny and false if it is sunny
    e.setCancelled(e.toWeatherState());
  }

}
