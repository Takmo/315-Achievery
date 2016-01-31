package com.bitwisehero.course315.achievery;

import java.util.ArrayList;

public class Achievement {

    private int achievementId;  // The ID number of the achievement.
    private Game game;          // The game that this achievement belongs to.
    private String name;        // The lexical name of the achievement.
    private int points;         // The points value of this achievement.

    private ArrayList<Player> players;  // Players who unlocked this achievement.

    // Constructor
    public Achievement(int achievementId, Game game, String name, int points) {
        this.achievementId = achievementId;
        this.game = game;
        this.name = name;
        this.points = points;
        this.players = new ArrayList<Player>();
    }

    // Getters - Here, have some stuff.

    public int getId() {
        return this.achievementId;
    }

    public Game getGame() {
        return this.game;
    }

    public String getName() {
        return this.name;
    }

    public int getPoints() {
        return this.points;
    }

    public ArrayList<Player> getPlayers() {
        return new ArrayList<Player>(this.players);
    }

    public int getNumPlayers() {
        return this.players.size();
    }

    public boolean hasPlayer(Player player) {
        return this.players.contains(player);
    }

    // Modifiers - change this achievement somehow.

    public void addPlayer(Player player) {
        if (!this.hasPlayer(player)) {
            this.players.add(player);
        }
    }
}
