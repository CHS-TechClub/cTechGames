package com.ctechcore.kits;

import com.ctechcore.player.TechPlayer;
import org.bukkit.ChatColor;

public abstract class Ability {

  private final TechPlayer techPlayer;

  public Ability(TechPlayer techPlayer) {
    this.techPlayer = techPlayer;
  }

  public abstract String getName();
  public abstract void doAbility();

  public TechPlayer getTechPlayer() {
    return techPlayer;
  }

  public String getUsedMessage() {
    return String.format("%sSkill> %sYou used %s%s%s!", ChatColor.BLUE, ChatColor.GRAY, ChatColor.GREEN, getName(), ChatColor.GRAY);
  }

}
