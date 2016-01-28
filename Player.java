package com.bitwisehero.course315.achievery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Player {

    // Holds all information a Player has on a single game.
    protected class GameData {

        private Game game;          // The game that this data belongs to.
        private String screenName;  // The player's screen name in this game.

        private Player player;      // The player object associated with this GameData.

        private ArrayList<Achievement> achievements;    // The player's achievements for this game.
        
        // Constructor
        public GameData(Game game, Player player, String screenName) {
            this.game = game;
            this.player = player;
            this.screenName = screenName;
            this.achievements = new ArrayList<Achievement>();
        }

        // Returns the game.
        public Game getGame() {
            return this.game;
        }

        // Returns the player's screen name.
        public String getScreenName() {
            return this.screenName;
        }

        // Adds and achievement for this player.
        public void addAchievement(Achievement achievement) {
            if (!this.achievements.contains(achievement)) {
                this.achievements.add(achievement);
                achievement.addOwner(this.player);
            }
        }

        // Returns an array of all achievements unlocked by this player.
        public Achievement[] getAchievements() {
            Achievement[] achievementArray = new Achievement[this.achievements.size()];
            achievementArray = this.achievements.toArray(achievementArray);
            return achievementArray;
        }
    }

    private int playerId;   // The player's ID.
    private String name;    // The player's name in our system.

    private HashMap<Game, GameData> gamesOwned;   // Map from gameId to GameData.

    // Constructor
    public Player(int playerId, String name) {
        this.playerId = playerId;
        this.name = name;
        this.gamesOwned = new HashMap<Game, GameData>();
    }

    // Returns the playerId.
    public int getId() {
        return this.playerId;
    }

    // Returns the player's name.
    public String getName() {
        return this.name;
    }

    // Adds a new game for the player.
    public void addGame(Game game, String screenName) {
        if (!this.hasGame(game)) {
            this.gamesOwned.put(game, new GameData(game, this, screenName));
            game.addPlayer(this); // Tell the game we play it.
        }
    }

    // Returns all GameData.
    public GameData[] getGameData() {
        Collection<GameData> gameDataValues = this.gamesOwned.values();
        GameData[] gameDataArray = new GameData[gameDataValues.size()];
        gameDataArray = gameDataValues.toArray(gameDataArray);
        return gameDataArray;
    }

    // Returns true if player has this game.
    public boolean hasGame(Game game) {
        return this.gamesOwned.containsKey(game);
    }
}
