package com.bitwisehero.course315.achievery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

// Game - contains all information and operations relevant to a game in our system.

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

    public ArrayList<Achievement> getAllAchievements() {
        return new ArrayList<Achievement>(this.achievements.values());
    }

    public int getNumAchievements() {
        return this.achievements.size();
    }

    public ArrayList<Player> getPlayers() {
        return new ArrayList<Player>(this.players);
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
