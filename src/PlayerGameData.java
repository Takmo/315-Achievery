package com.bitwisehero.course315.achievery;

import java.util.ArrayList;

// PlayerGameData - contains all information and operations related to
// a player's involvement in a single game tracked by this database.

public class PlayerGameData {

    private Game game;          // The game that this data belongs to.
    private Player player;      // The player object associated with this PlayerGameData.
    private String screenName;  // The player's screen name in this game.

    private ArrayList<Achievement> achievements;    // The player's achievements for this game.

    // Constructor
    public PlayerGameData(Game game, Player player, String screenName) {
        this.game = game;
        this.player = player;
        this.screenName = screenName;
        this.achievements = new ArrayList<Achievement>();

        // Tell the game that our player plays it.
        game.addPlayer(this.getPlayer());
    }

    // Getters - access attributes in a friendly way.

    public Game getGame() {
        return this.game;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getScreenName() {
        return this.screenName;
    }

    public ArrayList<Achievement> getAllAchievements() {
        return new ArrayList<Achievement>(this.achievements);
    }

    public int getNumAchievements() {
        return this.achievements.size();
    }

    // Aggregators - Aggregate information and present it.

    public int getGamerscore() {
        int gamerscore = 0;
        for (Achievement achievement : this.achievements) {
            gamerscore += achievement.getPoints();
        }
        return gamerscore;
    }

    // Modifiers - change the player's game data somehow.

    public void addAchievement(Achievement achievement) {
        if (!this.achievements.contains(achievement)) {
            this.achievements.add(achievement);

            // Tell this achievement that our player plays it.
            achievement.addPlayer(this.getPlayer());
        }
    }
}
