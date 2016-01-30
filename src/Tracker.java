package com.bitwisehero.course315.achievery;

import java.util.ArrayList;
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

        // And we might have to consider a second player.
        Player secondPlayer = null;
        if (command.secondPlayerId != -1) {
            secondPlayer = this.players.get(command.secondPlayerId);
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
                // Verify that the game exists but that the achievement does not.
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
                // Verify that both the player and game exist.
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
                // Verify that both players exist.
                if (player == null) {
                    System.err.println("Invalid playerId: " + command.playerId + " does not exist.");
                    return;
                }
                if (secondPlayer == null) {
                    System.err.println("Invalid playerId: " + command.secondPlayerId + " does not exist.");
                }

                // Make friends!
                player.addFriend(secondPlayer);
                secondPlayer.addFriend(player);
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
                // Verify that both the player and game exist, and that the player plays that game.
                if (player == null) {
                    System.err.println("Invalid playerId: " + command.playerId + " does not exist.");
                    return;
                }
                if (game == null) {
                    System.err.println("Invalid gameId: " + command.gameId + "does not exist.");
                    return;
                }
                if (!player.hasGame(game)) {
                    System.err.println("Player " + command.playerId + " does not play " + command.gameId + ".");
                    return;
                }

                // Get the list of friends and cull out the ones who don't play.
                ArrayList<Player> friendsWhoPlay = new ArrayList<Player>();
                for (Player friend : player.getFriends()) {
                    if (friend.hasGame(game)) {
                        friendsWhoPlay.add(friend);
                    }
                }

                // Print everything in a pretty format.
                System.out.println("Player: " + player.getName());
                System.out.println("Game: " + game.getName());
                System.out.println("IGN: " + player.getPlayerGameData(game).getScreenName() + "\n");
                
                System.out.println("PLAYER\t\tPOINTS\t\tIGN");
                System.out.println("-------------------------------------------------");

                for (Player friend : friendsWhoPlay) {
                    System.out.println(friend.getName() + "\t\t" +
                            friend.getPlayerGameData(game).getPoints() + "\t\t" +
                            friend.getPlayerGameData(game).getScreenName());
                }
                
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
