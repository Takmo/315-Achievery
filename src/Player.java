package com.bitwisehero.course315.achievery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Player {

    private int playerId;   // The player's ID.
    private String name;    // The player's name in our system.

    private HashMap<Game, PlayerGameData> gamesOwned;   // Map from gameId to PlayerGameData.

    // Constructor
    public Player(int playerId, String name) {
        this.playerId = playerId;
        this.name = name;
        this.gamesOwned = new HashMap<Game, PlayerGameData>();
    }

    // Getters - access attributes in a friendly way.

    public int getId() {
        return this.playerId;
    }

    public String getName() {
        return this.name;
    }

    public PlayerGameData[] getPlayerGameData() {
        Collection<PlayerGameData> gameDataValues = this.gamesOwned.values();
        PlayerGameData[] gameDataArray = new PlayerGameData[gameDataValues.size()];
        gameDataArray = gameDataValues.toArray(gameDataArray);
        return gameDataArray;
    }

    public boolean hasGame(Game game) {
        return this.gamesOwned.containsKey(game);
    }

    // Modifiers - change the player somehow.

    public void addAchievement(Game game, Achievement achievement) {
        if (!this.hasGame(game)) {
            System.err.println("Player '" + this.getName() +
                    "' cannot achieve '" + achievement.getName() +
                    "' because they do not play '" + game.getName() + "'.");
            return;
        }
        this.gamesOwned.get(game).addAchievement(achievement);
    }

    public void addGame(Game game, String screenName) {
        if (!this.hasGame(game)) {
            this.gamesOwned.put(game, new PlayerGameData(game, this, screenName));
        }
    }

}
