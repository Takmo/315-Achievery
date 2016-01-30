package com.bitwisehero.course315.achievery;

import java.util.ArrayList;

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

    public Achievement[] getAchievements() {
        Achievement[] achievementArray = new Achievement[this.achievements.size()];
        achievementArray = this.achievements.toArray(achievementArray);
        return achievementArray;
    }

    // Aggregators - Aggregate information and present it.

    public int getPoints() {
        int total = 0;
        for (Achievement achievement : this.achievements) {
            total += achievement.getPoints();
        }
        return total;
    }

    // Modifiers - change the player's game data somehow.

    public void addAchievement(Achievement achievement) {
        if (!this.achievements.contains(achievement)) {
            this.achievements.add(achievement);

            // Tell this achievement that our player plays it.
            achievement.addOwner(this.getPlayer());
        }
    }
}
