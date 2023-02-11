package com.ctechcore.kits.types;

import com.ctechcore.CTechCore;
import org.bukkit.Bukkit;

public interface Charged {

  int getDelay();
  int getInterval();
  void run();
  void finish();
  int getId();
  void setId(int id);

  default void start() {
    int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(CTechCore.getInstance(), this::run, getDelay(), getInterval());
    setId(id);
  }

  default void cancel() {
    Bukkit.getScheduler().cancelTask(getId());
    finish();
  }

}
