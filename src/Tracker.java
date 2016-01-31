package com.bitwisehero.course315.achievery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

                // Get the list of friends gamedata and cull out the ones who don't play.
                ArrayList<PlayerGameData> friendsWhoPlay = new ArrayList<PlayerGameData>();
                for (Player friend : player.getFriends()) {
                    if (friend.hasGame(game)) {
                        friendsWhoPlay.add(friend.getPlayerGameData(game));
                    }
                }
                Collections.sort(friendsWhoPlay, new PlayerGameDataComparator());

                // Print everything in a pretty format.
                System.out.println("Player: " + player.getName());
                System.out.println("Gamerscore: " + player.getPlayerGameData(game).getPoints());
                System.out.println("IGN: " + player.getPlayerGameData(game).getScreenName() + "\n");
                
                System.out.format("%-20s\t%-6s\t%-20s\n", "Player", "Score", "IGN");
                System.out.println("------------------------------------------------------");

                for (PlayerGameData friendData : friendsWhoPlay) {
                    System.out.format("%-20s\t%-6d\t%-20s\n", friendData.getPlayer().getName(),
                            friendData.getPoints(), friendData.getScreenName());
                }
                
                break;

            case ComparePlayers:
                // Verify that both players and the game exist. Also, players must play this game.
                if (player == null) {
                    System.err.println("Invalid playerId: " + command.playerId + " does not exist.");
                    return;
                }
                if (secondPlayer == null) {
                    System.err.println("Invalid playerId: " + command.secondPlayerId + " does not exist.");
                }
                if (game == null) {
                    System.err.println("Invalid gameId: " + command.gameId + "does not exist.");
                    return;
                }
                if (!player.hasGame(game)) {
                    System.err.println("Player " + command.playerId + " does not play " + command.gameId + ".");
                    return;
                }
                if (!secondPlayer.hasGame(game)) {
                    System.err.println("secondPlayer " + command.secondPlayerId + " does not play " + command.gameId + ".");
                    return;
                }

                // Print general information.
                System.out.println("Game: " + game.getName() + "\n");
                System.out.println("Player: " + player.getName());
                System.out.println("Gamerscore: " + player.getPlayerGameData(game).getPoints() + "\n");
                System.out.println("Player: " + secondPlayer.getName());
                System.out.println("Gamerscore: " + secondPlayer.getPlayerGameData(game).getPoints() + "\n");
                System.out.format("%-20s%-10s%-30s\n", "Achievement", "Points", "Unlocked By");
                System.out.println("------------------------------------------------------------");

                // Get all achievements and sort them in decreasing order by gamerscore.
                ArrayList<Achievement> playerAchievements = new ArrayList<Achievement>(Arrays.asList(game.getAllAchievements()));
                Collections.sort(playerAchievements, new AchievementComparator());
                // Loop through each achievement to see if a player has earned it.
                for (Achievement ach : playerAchievements) {
                    if (ach.hasAchievementOwner(player) && ach.hasAchievementOwner(secondPlayer)) {
                        System.out.format("%-20s%-10s%-30s\n", ach.getName(), ach.getPoints(), "Both");
                    }
                    else if (ach.hasAchievementOwner(player) && !ach.hasAchievementOwner(secondPlayer)) {
                        System.out.format("%-20s%-10s%-30s\n", ach.getName(), ach.getPoints(), player.getName());
                    }
                    else if (!ach.hasAchievementOwner(player) && ach.hasAchievementOwner(secondPlayer)) {
                        System.out.format("%-20s%-10s%-30s\n", ach.getName(), ach.getPoints(), secondPlayer.getName());
                    }
                }
                break;

            case SummarizePlayer:
                // Verify that this player exists.
                if (player == null) {
                    System.err.println("Invalid playerId: " + command.playerId + " does not exist.");
                    return;
                }

                // Print general information.
                System.out.println("Player: " + player.getName());
                System.out.println("Total Gamerscore: " + player.getTotalPoints() + "\n");
                System.out.format("%-25s%-20s%-15s%-20s\n", "Game", "Achievements", "Gamerscore", "IGN");
                System.out.println("--------------------------------------------------------------------------------");

                // Loop through all games and print their information.
                ArrayList<PlayerGameData> gameDatas = new ArrayList<PlayerGameData>(Arrays.asList(player.getAllPlayerGameData()));
                Collections.sort(gameDatas, new PlayerGameDataComparator());
                for (PlayerGameData pgd : gameDatas) {
                    String achievementFraction = String.format("%d/%d",
                            pgd.getNumAchievements(), pgd.getGame().getNumAchievements());
                    System.out.format("%-25s%-20s%-15s%-20s\n", pgd.getGame().getName(),
                            achievementFraction, pgd.getPoints(), pgd.getScreenName());
                }
                
                // Now print out their friends.
                System.out.format("\n%-15s%-22s\n", "Friend", "Total Gamerscore");
                System.out.println("---------------------------------");
                ArrayList<Player> summarizeFriends = new ArrayList<Player>(Arrays.asList(player.getFriends()));
                Collections.sort(summarizeFriends, new PlayerComparator());
                for (Player friend : summarizeFriends) {
                    System.out.format("%-20s%-10s\n", friend.getName(), friend.getTotalPoints());
                }
                break;

            case SummarizeGame:
                // Game must exist.
                if (game == null) {
                    System.err.println("Invalid gameId: " + command.gameId + "does not exist.");
                    return;
                }

                // Print the general information.
                System.out.println("Game: " + game.getName());
                System.out.println("Total Players: " + game.getNumPlayers());

                // Print player information.
                System.out.format("\n%-20s%-15s%-20s\n", "Player", "Gamerscore", "IGN");
                System.out.println("------------------------------------------------------------");
                ArrayList<Player> summarizePlayers = new ArrayList<Player>(Arrays.asList(game.getPlayers()));
                Collections.sort(summarizePlayers, new PlayerComparator());
                for (Player p : summarizePlayers) {
                    System.out.format("%-20s%-15s%-20s\n", p.getName(), p.getPlayerGameData(game).getPoints(),
                            p.getPlayerGameData(game).getScreenName());
                }

                // Print achievement stats.
                System.out.format("\n%-25s%-15s\n", "Achievement", "Times Achieved");
                System.out.println("----------------------------------------");
                ArrayList<Achievement> gameAchievements = new ArrayList<Achievement>(Arrays.asList(game.getAllAchievements()));
                Collections.sort(gameAchievements, new AchievementComparator());
                for (Achievement ach : gameAchievements) {
                    System.out.format("%-25s%-15s\n", ach.getName(), ach.getNumAchievementOwners());
                }
                break;

            case SummarizeAchievement:
                // Verify that the game and the achievement exist.
                if (game == null) {
                    System.err.println("Invalid gameId: " + command.gameId + "does not exist.");
                    return;
                }
                if (achievement == null) {
                    System.err.println("Invalid achievementId: " + command.achievementId +
                            " does not exist for gameId " + command.gameId + ".");
                    return;
                }

                // Print general information.
                System.out.println("Game: " + game.getName());
                System.out.println("Achievement: " + achievement.getName());
                System.out.println("Percent Players Unlocked: " +
                        (100.0 * achievement.getNumAchievementOwners() / game.getNumPlayers()) + "%\n");

                // Print all player information.
                System.out.format("\n%-25s%-25s\n", "Player", "IGN");
                System.out.println("--------------------------------------------------");
                for (Player p : achievement.getAchievementOwners()) {
                    System.out.format("%-25s%-25s\n", p.getName(), p.getPlayerGameData(game).getScreenName());
                }
                break;

            case AchievementRanking:
                System.err.println("Still working on this!");
                break;
        }
    }
}
