package com.smp;

import com.smp.commands.CreateGroupCommand;
import com.smp.commands.GroupCommand;
import com.smp.commands.JoinCommand;
import com.smp.commands.LeaveCommand;
import com.smp.listeners.CreateTechPlayerListener;
import com.smp.listeners.SpawnListener;
import org.bukkit.plugin.java.JavaPlugin;

public class SMP extends JavaPlugin {

  private static SMP INSTANCE;

  @Override
  public void onEnable() {
    INSTANCE = this;

    getLogger().info("SMP Starting!");
    registerCommands();
    registerListeners();
  }

  @Override
  public void onDisable() {
    getLogger().info("SMP Stopping!");
  }

  private void registerCommands() {
    getCommand("g").setExecutor(new GroupCommand());
    getCommand("group").setExecutor(new GroupCommand());
    getCommand("creategroup").setExecutor(new CreateGroupCommand());
    getCommand("join").setExecutor(new JoinCommand());
    getCommand("leave").setExecutor(new LeaveCommand());
  }

  public void registerListeners() {
    new CreateTechPlayerListener();
    new SpawnListener();
  }

  public static SMP getInstance() {
    return INSTANCE;
  }
}
