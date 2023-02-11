package com.ctechcore.kits.types;

import org.bukkit.ChatColor;
import java.text.DecimalFormat;

public interface Cooldown {

  String getName();
  long getCooldown();
  long getLastUsed();
  void setLastUsed();
  default double getRemainingCooldown() {
    return (getCooldown() - ((System.currentTimeMillis() - getLastUsed())) / 1000D);
  }

  default boolean isOnCooldown() {
    return (System.currentTimeMillis() - getLastUsed()) < getCooldown() * 1000L;
  }
  default String getCooldownMessage() {
    DecimalFormat df = new DecimalFormat("0.00");
    return String.format("%sSkill> %s%s%s is still on cooldown for %s seconds.", ChatColor.BLUE, ChatColor.GREEN, getName(), ChatColor.GRAY, df.format(getRemainingCooldown()));
  }

}
