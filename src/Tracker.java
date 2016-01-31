package com.bitwisehero.course315.achievery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// Tracker - manages all players and games, as well as processes Commands.

public class Tracker {

    HashMap<Integer, Game> games;       // Games in the database.
    HashMap<Integer, Player> players;   // Players in the database.

    // Constructor
    public Tracker() {
        this.games = new HashMap<Integer, Game>();
        this.players = new HashMap<Integer, Player>();
    }

    // Handle commands and such.
    public void processCommand(Command command) {
        // If any of these exist, go ahead and grab them.
        Player player = null;
        Player secondPlayer = null;
        Game game = null;
        Achievement achievement = null;
        if (command.playerId != -1) {
            player = this.players.get(command.playerId);
        }
        if (command.secondPlayerId != -1) {
            secondPlayer = this.players.get(command.secondPlayerId);
        }
        if (command.gameId != -1) {
            game = this.games.get(command.gameId);
        }
        if (game != null && command.achievementId != -1) {
            achievement = game.getAchievement(command.achievementId);
        }

        // Verify that inputs are correct. Because many commands have similar
        // requirements, doing it this way eliminates a fair bit of code duplication.
        CommandType ct = command.commandType;

        // These commands require the player to exist.
        if (player == null && (ct == CommandType.Plays
                    || ct == CommandType.AddFriends
                    || ct == CommandType.Achieve
                    || ct == CommandType.FriendsWhoPlay
                    || ct == CommandType.ComparePlayers
                    || ct == CommandType.SummarizePlayer)) {
                System.err.println("Invalid playerId: " + command.playerId + " does not exist.");
                return;
        }

        // These commands require the game to exist.
        if (game == null && (ct == CommandType.AddAchievement
                    || ct == CommandType.Plays
                    || ct == CommandType.Achieve
                    || ct == CommandType.FriendsWhoPlay
                    || ct == CommandType.ComparePlayers
                    || ct == CommandType.SummarizeGame
                    || ct == CommandType.SummarizeAchievement)) {
                System.err.println("Invalid gameId: " + command.gameId + " does not exist.");
                return;
        }

        // These commands require the achievement to exist.
        if (achievement == null && (ct == CommandType.Achieve
                    || ct == CommandType.SummarizeAchievement)) {
                System.err.println("Invalid achievementId: " + command.achievementId + " does not exist.");
                return;
        }

        // These commands require that the second player exist.
        if (secondPlayer == null && (ct == CommandType.AddFriends
                    || ct == CommandType.ComparePlayers)) {
                System.err.println("Invalid secondPlayerId: " + command.secondPlayerId + " does not exist.");
                return;
        }

        // These commands require that the Player owns the Game in question.
        if ((player != null && !player.hasGame(game)) && (ct == CommandType.Achieve
                    || ct == CommandType.FriendsWhoPlay
                    || ct == CommandType.ComparePlayers)) {
            System.err.println("Player " + command.playerId + " does not play " + command.gameId + ".");
            return;
        }

        // With most of the input verification done, finish anything else and process commands here.
        switch (command.commandType) {

            case AddPlayer:
                // The player must not already exist.
                if (player != null) {
                    System.err.println("Invalid playerId: " + command.playerId + "already exists.");
                    return;
                }
                this.players.put(command.playerId, new Player(command.playerId, command.name));
                break;

            case AddGame:
                // The game must not already exist.
                if (game != null) {
                    System.err.println("Invalid gameId: " + command.gameId + "already exists.");
                    return;
                }
                this.games.put(command.gameId, new Game(command.gameId, command.name));
                break;

            case AddAchievement:
                // The achievement must not already exist.
                if (achievement != null) {
                    System.err.println("Invalid achievementId: " + command.achievementId +
                            " already exists for gameId " + command.gameId + ".");
                    return;
                }
                Achievement newAch = new Achievement(command.achievementId, game, command.name, command.points);
                game.addAchievement(command.achievementId, newAch);
                break;

            case Plays:
                player.addGame(game, command.name);
                break;

            case AddFriends:
                player.addFriend(secondPlayer);
                secondPlayer.addFriend(player);
                break;

            case Achieve:
                player.addAchievement(game, achievement);
                break;

            case FriendsWhoPlay:
                // For each of the player's friends, check if they play the same game.
                // If so, record their GameData. Then sort it all by decreasing gamerscore.
                ArrayList<PlayerGameData> friendsGameData = new ArrayList<PlayerGameData>();
                for (Player friend : player.getFriends()) {
                    if (friend.hasGame(game)) {
                        friendsGameData.add(friend.getGameData(game));
                    }
                }
                Collections.sort(friendsGameData, new PlayerGameDataComparator());

                // Print everything in a pretty format.
                System.out.println("Player: " + player.getName());
                System.out.println("Gamerscore: " + player.getGameData(game).getGamerscore());
                System.out.println("IGN: " + player.getGameData(game).getScreenName() + "\n");

                System.out.format("%-20s\t%-6s\t%-20s\n", "Player", "Score", "IGN");
                System.out.println("------------------------------------------------------");

                for (PlayerGameData singleFriendData : friendsGameData) {
                    System.out.format("%-20s\t%-6d\t%-20s\n", singleFriendData.getPlayer().getName(),
                            singleFriendData.getGamerscore(), singleFriendData.getScreenName());
                }
                break;

            case ComparePlayers:
                // The second player must also play the same game.
                if (!secondPlayer.hasGame(game)) {
                    System.err.println("secondPlayer " + command.secondPlayerId + " does not play " + command.gameId + ".");
                    return;
                }

                // Get all achievements and sort them in decreasing order by gamerscore.
                ArrayList<Achievement> mutualGameAchievements = game.getAllAchievements();
                Collections.sort(mutualGameAchievements, new AchievementComparator());

                // Print general information.
                System.out.println("Game: " + game.getName() + "\n");

                System.out.println("Player: " + player.getName());
                System.out.println("Gamerscore: " + player.getGameData(game).getGamerscore() + "\n");

                System.out.println("Player: " + secondPlayer.getName());
                System.out.println("Gamerscore: " + secondPlayer.getGameData(game).getGamerscore() + "\n");

                System.out.format("%-20s%-10s%-30s\n", "Achievement", "Points", "Unlocked By");
                System.out.println("------------------------------------------------------------");

                // For each achievement print which players have earned it (or don't show it if neither have).
                for (Achievement ach : mutualGameAchievements) {
                    if (ach.hasPlayer(player) && ach.hasPlayer(secondPlayer)) {
                        System.out.format("%-20s%-10s%-30s\n", ach.getName(), ach.getPoints(), "Both");
                    }
                    else if (ach.hasPlayer(player) && !ach.hasPlayer(secondPlayer)) {
                        System.out.format("%-20s%-10s%-30s\n", ach.getName(), ach.getPoints(), player.getName());
                    }
                    else if (!ach.hasPlayer(player) && ach.hasPlayer(secondPlayer)) {
                        System.out.format("%-20s%-10s%-30s\n", ach.getName(), ach.getPoints(), secondPlayer.getName());
                    }
                }
                break;

            case SummarizePlayer:
                // Get all games a player owns and sort them decreasing based on their gamerscore in each.
                ArrayList<PlayerGameData> summarizePlayerGameDatas = player.getAllGameData();
                Collections.sort(summarizePlayerGameDatas, new PlayerGameDataComparator());

                // Get all of the player's friends and sort them decreasing based on total gamerscore.
                ArrayList<Player> summarizePlayerFriends = player.getFriends();
                Collections.sort(summarizePlayerFriends, new PlayerComparator());

                // Print general information.
                System.out.println("Player: " + player.getName());
                System.out.println("Total Gamerscore: " + player.getTotalGamerscore() + "\n");

                // Print their game statistics.
                System.out.format("%-25s%-20s%-15s%-20s\n", "Game", "Achievements", "Gamerscore", "IGN");
                System.out.println("--------------------------------------------------------------------------------");

                for (PlayerGameData pgd : summarizePlayerGameDatas) {
                    String achievementFraction = String.format("%d/%d",
                            pgd.getNumAchievements(), pgd.getGame().getNumAchievements());
                    System.out.format("%-25s%-20s%-15s%-20s\n", pgd.getGame().getName(),
                            achievementFraction, pgd.getGamerscore(), pgd.getScreenName());
                }

                // Now print out their friend statistics.
                System.out.format("\n%-15s%-22s\n", "Friend", "Total Gamerscore");
                System.out.println("---------------------------------");

                for (Player friend : summarizePlayerFriends) {
                    System.out.format("%-20s%-10s\n", friend.getName(), friend.getTotalGamerscore());
                }
                break;

            case SummarizeGame:
                // Collect all players and rank them by gamerscore.
                ArrayList<Player> summarizeGamePlayers = game.getPlayers();
                Collections.sort(summarizeGamePlayers, new PlayerComparator());

                // Collect all game achievements and rank them by point value.
                ArrayList<Achievement> summarizeGameAchievements = game.getAllAchievements();
                Collections.sort(summarizeGameAchievements, new AchievementComparator());

                // Print the general information.
                System.out.println("Game: " + game.getName());
                System.out.println("Total Players: " + game.getNumPlayers());

                // Print player information.
                System.out.format("\n%-20s%-15s%-20s\n", "Player", "Gamerscore", "IGN");
                System.out.println("------------------------------------------------------------");

                for (Player p : summarizeGamePlayers) {
                    System.out.format("%-20s%-15s%-20s\n", p.getName(), p.getGameData(game).getGamerscore(),
                            p.getGameData(game).getScreenName());
                }

                // Print achievement stats.
                System.out.format("\n%-25s%-15s\n", "Achievement", "Times Achieved");
                System.out.println("----------------------------------------");

                for (Achievement ach : summarizeGameAchievements) {
                    System.out.format("%-25s%-15s\n", ach.getName(), ach.getNumPlayers());
                }
                break;

            case SummarizeAchievement:
                // Print general information.
                System.out.println("Game: " + game.getName());
                System.out.println("Achievement: " + achievement.getName());
                System.out.println("Percent Players Unlocked: " +
                        (100.0 * achievement.getNumPlayers() / game.getNumPlayers()) + "%\n");

                // Print all player information.
                System.out.format("%-25s%-25s\n", "Player", "IGN");
                System.out.println("--------------------------------------------------");
                for (Player p : achievement.getPlayers()) {
                    System.out.format("%-25s%-25s\n", p.getName(), p.getGameData(game).getScreenName());
                }
                break;

            case AchievementRanking:
                // Since there are no parameters for this command, we don't need to verify anything.
                // Let's just go ahead and print all of the players in decreasing order by gamerscore.
                ArrayList<Player> allPlayers = new ArrayList<Player>(this.players.values());
                Collections.sort(allPlayers, new PlayerComparator());

                System.out.println("Total Players: " + allPlayers.size());
                System.out.format("\n%-6s%-25s%-20s\n", "Rank", "Player", "Total Gamerscore");
                System.out.println("------------------------------------------------------");

                for (int i = 0; i < allPlayers.size(); ++i) {
                    System.out.format("%-6d%-25s%-20s\n", i + 1, allPlayers.get(i).getName(),
                            allPlayers.get(i).getTotalGamerscore());
                }
                break;
        }
    }
}
