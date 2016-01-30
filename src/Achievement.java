package com.bitwisehero.course315.achievery;

import java.util.ArrayList;

public class Achievement {

    private int achievementId;  // The ID number of the achievement.
    private Game parentGame;    // The game that this achievement belongs to.
    private String name;        // The lexical name of the achievement.
    private int points;         // The points value of this achievement.

    private ArrayList<Player> achievementOwners;    // Players who unlocked this achievement.

    // Constructor
    public Achievement(int achievementId, Game parentGame, String name, int points) {
        this.parentGame = parentGame;
        this.achievementId = achievementId;
        this.name = name;
        this.points = points;
        this.achievementOwners = new ArrayList<Player>();
    }

    // Getters - Here, have some stuff.

    public int getId() {
        return this.achievementId;
    }

    public Game getParentGame() {
        return this.parentGame;
    }

    public String getName() {
        return this.name;
    }

    public int getPoints() {
        return this.points;
    }

    public Player[] getAchievementOwners() {
        Player[] ownerArray = new Player[this.achievementOwners.size()];
        ownerArray = this.achievementOwners.toArray(ownerArray);
        return ownerArray;
    }

    public int getNumAchievementOwners() {
        return this.achievementOwners.size();
    }

    // Modifiers - change this achievement somehow.

    public void addOwner(Player player) {
        if (!this.achievementOwners.contains(player)) {
            this.achievementOwners.add(player);
        }
    }
}
