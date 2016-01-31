package com.bitwisehero.course315.achievery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

// Player - contains all information and operations related to
// a player in our database.

public class Player {

    private int playerId;   // The player's ID.
    private String name;    // The player's name in our system.

    private HashMap<Game, PlayerGameData> games;    // Map from gameId to PlayerGameData.
    private ArrayList<Player> friends;              // Friends

    // Constructor
    public Player(int playerId, String name) {
        this.playerId = playerId;
        this.name = name;
        this.games = new HashMap<Game, PlayerGameData>();
        this.friends = new ArrayList<Player>();
    }

    // Getters - access attributes in a friendly way.

    public int getId() {
        return this.playerId;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<PlayerGameData> getAllGameData() {
        return new ArrayList<PlayerGameData>(this.games.values());
    }

    public PlayerGameData getGameData(Game game) {
        return this.games.get(game);
    }

    public boolean hasGame(Game game) {
        return this.games.containsKey(game);
    }

    public ArrayList<Player> getFriends() {
        return new ArrayList<Player>(this.friends);
    }

    public int getTotalGamerscore() {
        int total = 0;
        for (PlayerGameData gameData : this.getAllGameData()) {
            total += gameData.getGamerscore();
        }
        return total;
    }

    // Modifiers - change the player somehow.

    public void addAchievement(Game game, Achievement achievement) {
        if (!this.hasGame(game)) {
            System.err.println("Player '" + this.getName() +
                    "' cannot achieve '" + achievement.getName() +
                    "' because they do not play '" + game.getName() + "'.");
            return;
        }
        this.games.get(game).addAchievement(achievement);
    }

    public void addGame(Game game, String screenName) {
        if (!this.hasGame(game)) {
            this.games.put(game, new PlayerGameData(game, this, screenName));
        }
    }
    
    public void addFriend(Player friend) {
        if (!this.friends.contains(friend)) {
            this.friends.add(friend);
        }
    }
}
