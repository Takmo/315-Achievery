package com.bitwisehero.course315.achievery;

import java.util.ArrayList;

public class Game {

    private int gameId;     // The ID number of this game.
    private String name;    // The lexical name of the game.

    private ArrayList<Achievement> achievements;    // The game's achievements.
    private ArrayList<Player> players;              // Players who play this game.

    // Constructor
    public Game(int gameId, String name) {
        this.gameId = gameId;
        this.name = name;
        this.achievements = new ArrayList<Achievement>();
        this.players = new ArrayList<Player>();
    }

    // Returns the gameId.
    public int getId() {
        return this.gameId;
    }

    // Returns the name of the game.
    public String getName() {
        return this.name;
    }

    // Add an achievement for this game.
    public void addAchievement(Achievement achievement) {
        if (!this.achievements.contains(achievement)) {
            this.achievements.add(achievement);
        }
    }

    // Returns all achievements for this game.
    public Achievement[] getAchievements() {
        Achievement[] achievementArray = new Achievement[this.achievements.size()];
        achievementArray = this.achievements.toArray(achievementArray);
        return achievementArray;
    }

    // Returns the number of achivements.
    public int getNumAchievements() {
        return this.achievements.size();
    }

    // Adds a player for this game.
    public void addPlayer(Player player) {
        if (!this.players.contains(player)) {
            this.players.add(player);
        }
    }

    // Returns all of the players for this game.
    public Player[] getPlayers() {
        Player[] playerArray = new Player[this.players.size()];
        playerArray = this.players.toArray(playerArray);
        return playerArray;
    }

    // Returns the number of players.
    public int getNumPlayers() {
        return this.players.size();
    }
}
