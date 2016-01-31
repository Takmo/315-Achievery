package com.bitwisehero.course315.achievery;

import java.util.HashMap;
import java.util.StringTokenizer;

// CommandParser - converts an input string into a valid Command.

public class CommandParser {

    // Contains the map of strings to valid CommandTypes.
    private HashMap<String, CommandType> stringCommandTypeMap;

    // Constructor
    public CommandParser() {
        stringCommandTypeMap = new HashMap<String, CommandType>();
        stringCommandTypeMap.put("AddPlayer", CommandType.AddPlayer);
        stringCommandTypeMap.put("AddGame", CommandType.AddGame);
        stringCommandTypeMap.put("AddAchievement", CommandType.AddAchievement);
        stringCommandTypeMap.put("Plays", CommandType.Plays);
        stringCommandTypeMap.put("AddFriends", CommandType.AddFriends);
        stringCommandTypeMap.put("Achieve", CommandType.Achieve);
        stringCommandTypeMap.put("FriendsWhoPlay", CommandType.FriendsWhoPlay);
        stringCommandTypeMap.put("ComparePlayers", CommandType.ComparePlayers);
        stringCommandTypeMap.put("SummarizePlayer", CommandType.SummarizePlayer);
        stringCommandTypeMap.put("SummarizeGame", CommandType.SummarizeGame);
        stringCommandTypeMap.put("SummarizeAchievement", CommandType.SummarizeAchievement);
        stringCommandTypeMap.put("AchievementRanking", CommandType.AchievementRanking);
    }

    // Easily converts an input token to a CommandType.
    private CommandType stringToCommandType(String input) {
        if (stringCommandTypeMap.containsKey(input)) {
            return stringCommandTypeMap.get(input);
        }
        return CommandType.END;
    }

    // Parse a command line and create a Command object.
    public Command parse(String input) {
        // Start with a default, empty Command.
        Command command = new Command();

        // Any number of errors can occur while processing an input line, so we'll
        // catch any exceptions and let the user know if there was a problem.
        try {

            // Start getting rid of unnecessary whitespace in the input line.
            input = input.trim();
            input = input.replaceAll(" +", " ");

            // Extract the command. Split on first space into String[2].
            String[] commandSplit = input.split(" ", 2);
            command.commandTypeText = commandSplit[0];
            command.commandType = stringToCommandType(commandSplit[0]);

            // If there are no command parameters, we should go ahead and return.
            if (commandSplit.length == 1) {
                return command;
            }

            // CommandType token will be present for all lines, but all other input varies.
            String remainingInput = commandSplit[1];
            switch (command.commandType) {

                case AddPlayer:
                    // AddPlayer [int id] "User Name"
                    String[] addPlayerSplit = remainingInput.split(" ", 2);
                    command.playerId = Integer.parseInt(addPlayerSplit[0]);
                    // Extract their name, but we don't want the quotation marks.
                    command.name = addPlayerSplit[1].replaceAll("\"", "");
                    break;

                case AddGame:
                    // AddGame [int id] "Game Name"
                    String[] addGameSplit = remainingInput.split(" ", 2);
                    command.gameId = Integer.parseInt(addGameSplit[0]);
                    // Extract the game's name, but we don't want the quotation marks.
                    command.name = addGameSplit[1].replaceAll("\"", "");
                    break;

                case AddAchievement:
                    // AddAchievement [int gameId] [int achievementId] "Achievement Name" [int points]
                    // Because of the line format, this requires multiple splits. First, let's
                    // separate the first two IDs. Then work on the rest.
                    String[] addAchievementSplit = remainingInput.split(" ", 3);
                    command.gameId = Integer.parseInt(addAchievementSplit[0]);
                    command.achievementId = Integer.parseInt(addAchievementSplit[1]);

                    // Now that we've split those, we'll split the rest by quotation marks in order
                    // to get the achievement name all in one. The first index should be empty, the
                    // second index should contain the achievement name, and the third index should
                    // contain the number of points, which MUST be trimmed of extra whitespace.
                    String[] namePointsSplit = addAchievementSplit[2].split("\"", 3);
                    command.name = namePointsSplit[1];
                    command.points = Integer.parseInt(namePointsSplit[2].trim());
                    break;

                case Plays:
                    // Plays [int playerId] [int gameId] "In-Game Name"
                    String[] playsSplit = remainingInput.split(" ", 3);
                    command.playerId = Integer.parseInt(playsSplit[0]);
                    command.gameId = Integer.parseInt(playsSplit[1]);
                    // Extract their name, but we don't want the quotation marks.
                    command.name = playsSplit[2].replaceAll("\"", "");
                    break;

                case AddFriends:
                    // AddFriends [int playerId] [int secondPlayerId]
                    String[] addFriendsSplit = remainingInput.split(" ", 2);
                    command.playerId = Integer.parseInt(addFriendsSplit[0]);
                    command.secondPlayerId = Integer.parseInt(addFriendsSplit[1]);
                    break;

                case Achieve:
                    // Achieve [int playerId] [int gameId] [int achievementId]
                    String[] achieveSplit = remainingInput.split(" ", 3);
                    command.playerId = Integer.parseInt(achieveSplit[0]);
                    command.gameId = Integer.parseInt(achieveSplit[1]);
                    command.achievementId = Integer.parseInt(achieveSplit[2]);
                    break;

                case FriendsWhoPlay:
                    // FriendsWhoPlay [int playerId] [int gameId]
                    String[] friendsWhoPlaySplit = remainingInput.split(" ", 2);
                    command.playerId = Integer.parseInt(friendsWhoPlaySplit[0]);
                    command.gameId = Integer.parseInt(friendsWhoPlaySplit[1]);
                    break;

                case ComparePlayers:
                    // ComparePlayers [int playerId] [int secondPlayerId] [int gameId]
                    String[] comparePlayersSplit = remainingInput.split(" ", 3);
                    command.playerId = Integer.parseInt(comparePlayersSplit[0]);
                    command.secondPlayerId = Integer.parseInt(comparePlayersSplit[1]);
                    command.gameId = Integer.parseInt(comparePlayersSplit[2]);
                    break;

                case SummarizePlayer:
                    // SummarizePlayer [int playerId]
                    command.playerId = Integer.parseInt(remainingInput);
                    break;

                case SummarizeGame:
                    // SummarizeGame [int gameId]
                    command.gameId = Integer.parseInt(remainingInput);
                    break;

                case SummarizeAchievement:
                    // SummarizeAchievement [int gameId] [int achievementId]
                    String[] summarizeAchievementSplit = remainingInput.split(" ", 2);
                    command.gameId = Integer.parseInt(summarizeAchievementSplit[0]);
                    command.achievementId = Integer.parseInt(summarizeAchievementSplit[1]);
                    break;

                case AchievementRanking:
                    // This command requires no additional arguments.
                    break;
            }
        } catch (Exception e) {
            System.err.println("Invalid command input: " + input);
        }
        return command;
    }
}
