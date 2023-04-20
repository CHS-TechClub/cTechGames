package com.ctechcore.mongo;

import static com.mongodb.client.model.Filters.eq;

import com.ctechcore.CTechCore;
import com.ctechcore.player.TechPlayer;
import com.ctechcore.teams.Team;
import com.mongodb.client.*;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * Access control is not setup because this is a private database instance that is not exposed to the web.
 */
public final class TechDatabaseManager {

  private final MongoClient mongoClient;
  private final MongoDatabase techDatabase;
  private final MongoCollection<Document> players;
  private final MongoCollection<Document> teams;

  public TechDatabaseManager() {
    this.mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
    this.techDatabase = this.mongoClient.getDatabase("ctech");
    this.players = this.techDatabase.getCollection("players");
    this.teams = this.techDatabase.getCollection("teams");
  }

  private MongoClient getMongoClient() {
    return mongoClient;
  }

  private MongoDatabase getTechDatabase() {
    return techDatabase;
  }

  private MongoCollection<Document> getPlayers() {
    return players;
  }

  public FindIterable<Document> getPlayerDataList() {
    return getPlayers().find();
  }

  public Document getTechPlayerData(UUID uuid) {
    return getPlayers().find(eq("uuid", uuid.toString())).first();
  }

  public void saveTechPlayer(TechPlayer techPlayer) {
    Bukkit.getScheduler().runTaskAsynchronously(CTechCore.getInstance(), () -> {
      Document query = new Document().append("uuid", techPlayer.getPlayer().getUniqueId().toString());
      Bson updates = Updates.combine(
          Updates.set("rank", techPlayer.getRank().getName()),
          Updates.set("coins", techPlayer.getCoins()),
          Updates.set("team", techPlayer.getTeam().getName())
      );
      UpdateOptions options = new UpdateOptions().upsert(true); //insert if the document doesn't exist.
      getPlayers().updateOne(query, updates, options);
    });
  }

  private MongoCollection<Document> getTeams() {
    return teams;
  }

  public FindIterable<Document> getTeamDataList() {
    return getTeams().find();
  }

  public Document getTeamData(String name) {
    return getTeams().find(eq("name", name)).first();
  }

  public void saveTeam(Team team) {
    Bukkit.getScheduler().runTaskAsynchronously(CTechCore.getInstance(), () -> {
      Document query = new Document().append("name", team.getName());
      Bson updates = Updates.combine(
          Updates.set("leader", (team.getLeader() == null ? null : team.getLeader().toString())),
          Updates.set("color", team.getColor().name())
      );
      UpdateOptions options = new UpdateOptions().upsert(true);
      getTeams().updateOne(query, updates, options);
    });
  }

  public void deleteTeam(Team team) {
    Bukkit.getScheduler().runTaskAsynchronously(CTechCore.getInstance(), () -> {
      Bson query = eq("name", team.getName());
      getTeams().deleteOne(query);
    });
  }

}
