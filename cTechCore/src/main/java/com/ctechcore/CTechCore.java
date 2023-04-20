package com.ctechcore;

import com.ctechcore.commands.GrantCommand;
import com.ctechcore.listeners.PlayerChatListener;
import com.ctechcore.listeners.PlayerConnectionListener;
import com.ctechcore.mongo.TechDatabaseManager;
import com.ctechcore.player.TechPlayerManager;
import com.ctechcore.teams.TeamManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class CTechCore extends JavaPlugin {

  private static CTechCore INSTANCE;
  private TechPlayerManager tpm;
  private TechDatabaseManager tdm;
  private TeamManager tm;
  public static final String PREFIX = String.format("%s%sCTECH>%s%s", ChatColor.YELLOW, ChatColor.BOLD, ChatColor.RESET, ChatColor.WHITE);

  @Override
  public void onEnable() {
    getLogger().info("cTechCore started!");
    tdm = new TechDatabaseManager();
    tpm = new TechPlayerManager();
    INSTANCE = this;
    tm = new TeamManager();

    registerListeners();
    registerCommands();
  }

  private void registerListeners() {
    new PlayerConnectionListener(this);
    new PlayerChatListener(this);
  }

  private void registerCommands() {
    getCommand("grant").setExecutor(new GrantCommand());
  }

  public TechPlayerManager getTechPlayerManager() {
    return tpm;
  }

  public TechDatabaseManager getDatabaseManager() {
    return tdm;
  }

  public TeamManager getTeamManager() {
    return tm;
  }

  public static CTechCore getInstance() {
    return INSTANCE;
  }
}
