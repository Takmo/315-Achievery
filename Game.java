package com.bitwisehero.course315.achievery;

public class Game {

    private int gameId;     // The ID number of this game.
    private String name;    // The lexical name of the game.

    private List<Achievement> achievements; // The game's achievements.
    private List<Player> players;           // Players who play this game.

    // Constructor
    public Game(int gameId, String name) {
        this.gameId = gameId;
        this.name = name;
        this.achievements = new ArrayList<Achievement>();
        this.players = new ArrayList<Player>();
    }

    // Returns the gameId.
    public getGameId() {
        return this.gameId;
    }

    // Returns the name of the game.
    public getName() {
        return this.name;
    }

    // Returns all achievements for this game.
    public Achievement[] getAchievements() {
        Achievement[] achievementArray = new Achievements[this.achievements.size()];
        achievementArray = this.achievements.toArray(achievementArray);
        return achievementArray;
    }

    // Returns all of the players for this game.
    public Player[] getPlayers() {
        Player[] playerArray = new Player[this.players.size()];
        playerArray = this.players.toArray(playerArray);
        return playerArray;
    }

    // Returns the number of achivements.
    public int getNumAchievements() {
        return this.achievements.size();
    }

    // Returns the number of players.
    public int getNumPlayers() {
        return this.players.size();
    }
}
