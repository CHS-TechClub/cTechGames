package com.ctechcore.mongo;

import static com.mongodb.client.model.Filters.eq;

import com.ctechcore.CTechCore;
import com.ctechcore.player.TechPlayer;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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

  public TechDatabaseManager() {
    this.mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
    this.techDatabase = this.mongoClient.getDatabase("ctech");
    this.players = this.techDatabase.getCollection("players");
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

  public Document getTechPlayerData(UUID uuid) {
    return getTechDatabase().getCollection("players").find(eq("uuid", uuid.toString())).first();
  }

  public void saveTechPlayer(TechPlayer techPlayer) {
    Bukkit.getScheduler().runTaskAsynchronously(CTechCore.getInstance(), () -> {
      Document query = new Document().append("uuid", techPlayer.getPlayer().getUniqueId().toString());
      Bson updates = Updates.combine(
          Updates.set("rank", techPlayer.getRank().getName()),
          Updates.set("coins", techPlayer.getCoins())
      );
      UpdateOptions options = new UpdateOptions().upsert(true); //insert if the document doesn't exist.
      getPlayers().updateOne(query, updates, options);
    });
  }

}
