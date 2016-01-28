package com.bitwisehero.course315.achievery;

import java.util.ArrayList;

public class Achievement {

    private Game parentGame;    // The game that this achievement belongs to.
    private int achievementId;  // The ID number of the achievement.
    private String name;        // The lexical name of the achievement.

    private ArrayList<Player> achievementOwners;    // Players who unlocked this achievement.

    // Constructor
    public Achievement(Game parentGame, int achievementId, String name) {
        this.parentGame = parentGame;
        this.achievementId = achievementId;
        this.name = name;
        this.achievementOwners = new ArrayList<Player>();
    }

    // Getters - Here, have some stuff.

    public Game getParentGame() {
        return this.parentGame;
    }

    public int getId() {
        return this.achievementId;
    }

    public String getName() {
        return this.name;
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
