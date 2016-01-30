package com.bitwisehero.course315.achievery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Game {

    private int gameId;     // The ID number of this game.
    private String name;    // The lexical name of the game.

    private HashMap<Integer, Achievement> achievements; // The game's achievements.
    private ArrayList<Player> players;                  // Players who play this game.

    // Constructor
    public Game(int gameId, String name) {
        this.gameId = gameId;
        this.name = name;
        this.achievements = new HashMap<Integer, Achievement>();
        this.players = new ArrayList<Player>();
    }

    // Getters - provide a friendly way of accessing attributes.
    
    public int getId() {
        return this.gameId;
    }

    public String getName() {
        return this.name;
    }

    public Achievement getAchievement(int achievementId) {
        return this.achievements.get(achievementId);
    }

    public Achievement[] getAllAchievements() {
        Collection<Achievement> achievementValues = this.achievements.values();
        Achievement[] achievementArray = new Achievement[achievementValues.size()];
        achievementArray = achievementValues.toArray(achievementArray);
        return achievementArray;
    }

    public int getNumAchievements() {
        return this.achievements.size();
    }

    public Player[] getPlayers() {
        Player[] playerArray = new Player[this.players.size()];
        playerArray = this.players.toArray(playerArray);
        return playerArray;
    }

    public int getNumPlayers() {
        return this.players.size();
    }

    // Modifiers - the game has changed.

    public void addAchievement(int achievementId, Achievement achievement) {
        if (!this.achievements.containsKey(achievementId)) {
            this.achievements.put(achievementId, achievement);
        }
    }

    public void addPlayer(Player player) {
        if (!this.players.contains(player)) {
            this.players.add(player);
        }
    }
}
