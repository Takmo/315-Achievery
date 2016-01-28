package com.bitwisehero.course315.achievery;

import java.util.ArrayList;

public class Achievement {

    private int achievementId;  // The ID number of the achievement.
    private Game parentGame;    // The game that this achievement belongs to.
    private String name;        // The lexical name of the achievement.

    private ArrayList<Player> achievementOwners;    // Players who unlocked this achievement.

    // Constructor
    public Achievement(Game parentGame, int achievementId, String name) {
        this.parentGame = parentGame;
        this.achievementId = achievementId;
        this.name = name;
        this.achievementOwners = new ArrayList<Player>();
    }

    // Returns the achievementId.
    public int getId() {
        return this.achievementId;
    }

    // Returns the parent Game of this Achievement.
    public Game getParentGame() {
        return this.parentGame;
    }

    // Returns the lexical name of the Achievement.
    public String getName() {
        return this.name;
    }

    // Adds a player as an owner of this achievement.
    public void addOwner(Player player) {
        if (!this.achievementOwners.contains(player)) {
            this.achievementOwners.add(player);
        }
    }

    // Returns an array of all players who have unlocked this Achievement.
    public Player[] getOwners() {
        Player[] ownerArray = new Player[this.achievementOwners.size()];
        ownerArray = this.achievementOwners.toArray(ownerArray);
        return ownerArray;
    }

    // Returns the number of players have unlocked this achievement.
    public int getNumOwners() {
        return this.achievementOwners.size();
    }
}
