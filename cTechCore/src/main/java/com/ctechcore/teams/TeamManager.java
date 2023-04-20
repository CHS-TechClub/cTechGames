package com.ctechcore.teams;

import com.ctechcore.CTechCore;
import com.ctechcore.mongo.TechDatabaseManager;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class TeamManager {

  private final HashMap<String, Team> teams;

  public TeamManager() {
    this.teams = new HashMap<>();

    Bukkit.getScheduler().runTaskAsynchronously(CTechCore.getInstance(), () -> {
      TechDatabaseManager tdm = CTechCore.getInstance().getDatabaseManager();
      for (Document teamData : tdm.getTeamDataList()) {
        UUID leader = teamData.get("leader") == null ? null : UUID.fromString(teamData.getString("leader"));
        ChatColor color = ChatColor.valueOf(teamData.getString("color"));
        String name = teamData.getString("name");
        this.teams.put(name, new Team(name, color, leader));
      }
    });

  }

  public Collection<Team> getTeams() {
    return teams.values();
  }

  public Team getTeamByName(String name) {
    return this.teams.getOrDefault(name, Team.NONE);
  }

  public void addTeam(Team team) {
    this.teams.put(team.getName(), team);
    CTechCore.getInstance().getDatabaseManager().saveTeam(team);
  }

  public void deleteTeam(Team team) {
    this.teams.remove(team.getName());
    CTechCore.getInstance().getDatabaseManager().deleteTeam(team);
  }

  public boolean teamExists(String name) {
    return getTeamByName(name) != Team.NONE;
  }

}
