package com.bitwisehero.course315.achievery;

import java.util.HashMap;

public class Tracker {

    HashMap<Integer, Game> games;       // Games in the database.
    HashMap<Integer, Player> players;   // Players in the database.

    // Constructor
    public Tracker() {
        this.games = new HashMap<Integer, Game>();
        this.players = new HashMap<Integer, Player>();
    }

    // Handle commands and such.
    public void handleCommand(Command command) {
        // Many of the commands require a Player, so if the playerId is set, try to get it.
        Player player = null;
        if (command.playerId != -1) {
            player = this.players.get(command.playerId);
        }

        // Let's do the same with Game and gameId.
        Game game = null;
        if (command.gameId != -1) {
            game = this.games.get(command.gameId);
        }

        // And we'll grab Achievement from its game.
        Achievement achievement = null;
        if (game != null && command.achievementId != -1) {
            achievement = game.getAchievement(command.achievementId);
        }

        switch (command.commandType) {

            case AddPlayer:
                // Verify that a player does not exist.
                if (player != null) {
                    System.err.println("Invalid playerId: " + command.playerId + "already exists.");
                    return;
                }
                this.players.put(command.playerId, new Player(command.playerId, command.name));
                break;

            case AddGame:
                // Verify that a game does not exist.
                if (game != null) {
                    System.err.println("Invalid gameId: " + command.gameId + "already exists.");
                    return;
                }
                this.games.put(command.gameId, new Game(command.gameId, command.name));
                break;

            case AddAchievement:
                // Verify that the player and game exist but that the achievement does not.
                if (player == null) {
                    System.err.println("Invalid playerId: " + command.playerId + " does not exist.");
                    return;
                }
                if (game == null) {
                    System.err.println("Invalid gameId: " + command.gameId + "does not exist.");
                    return;
                }
                if (achievement != null) {
                    System.err.println("Invalid achievementId: " + command.achievementId +
                            " already exists for gameId " + command.gameId + ".");
                    return;
                }

                // Add the achievement.
                game.addAchievement(command.achievementId,
                        new Achievement(command.achievementId, game, command.name, command.points));
                break;

            case Plays:
                // Verify that both the player and game exit.
                if (player == null) {
                    System.err.println("Invalid playerId: " + command.playerId + " does not exist.");
                    return;
                }
                if (game == null) {
                    System.err.println("Invalid gameId: " + command.gameId + "does not exist.");
                    return;
                }
                
                // Add the game for them.
                player.addGame(game, command.name);
                break;

            case AddFriends:
                System.err.println("Still working on this!");
                break;

            case Achieve:
                // Verify that the player, the game, and the achievement exist.
                if (player == null) {
                    System.err.println("Invalid playerId: " + command.playerId + " does not exist.");
                    return;
                }
                if (game == null) {
                    System.err.println("Invalid gameId: " + command.gameId + "does not exist.");
                    return;
                }
                if (achievement == null) {
                    System.err.println("Invalid achievementId: " + command.achievementId +
                            " does not exist for gameId " + command.gameId + ".");
                    return;
                }

                // Add the achievement for them.
                player.addAchievement(game, achievement);
                break;

            case FriendsWhoPlay:
                System.err.println("Still working on this!");
                break;

            case ComparePlayers:
                System.err.println("Still working on this!");
                break;

            case SummarizePlayer:
                System.err.println("Still working on this!");
                break;

            case SummarizeGame:
                System.err.println("Still working on this!");
                break;

            case SummarizeAchievement:
                System.err.println("Still working on this!");
                break;

            case AchievementRanking:
                System.err.println("Still working on this!");
                break;
        }
    }
}
