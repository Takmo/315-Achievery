package com.bitwisehero.course315.achievery;

import java.util.HashMap;
import java.util.StringTokenizer;

public class CommandParser {

    // Regex representing WHITESPACE.
    private static final String WHITESPACE = "\\s+";

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

    public Command parse(String input) {
        Command command = new Command();
        try {
            // Clean up the input line for processing.
            input = input.trim(); // Delete all leading and trailing whitespace.
            input = input.replaceAll(" +", " "); // Get rid of extra spaces.

            // Extract the command.
            String[] commandSplit = input.split(" ", 2);
            command.commandTypeText = commandSplit[0];
            command.commandType = stringToCommandType(commandSplit[0]);

            // Now handle each command separately.
            String remainingInput = commandSplit[1];
            switch (command.commandType) {

                case AddPlayer:
                    String[] playerSplit = remainingInput.split(" ", 2);
                    command.playerId = Integer.parseInt(playerSplit[0]);
                    command.name = playerSplit[1];
                    command.name = command.name.replaceAll("\"", ""); // remove quotation marks
                    break;

                case AddGame:
                    String[] gameSplit = remainingInput.split(" ", 2);
                    command.gameId = Integer.parseInt(gameSplit[0]);
                    command.name = gameSplit[1];
                    command.name = command.name.replaceAll("\"", ""); // remove quotation marks
                    break;

                case AddAchievement:
                    String[] achievementSplit = remainingInput.split(" ", 3);
                    command.gameId = Integer.parseInt(achievementSplit[0]);
                    command.achievementId = Integer.parseInt(achievementSplit[1]);
                    String[] namePointsSplit = achievementSplit[2].split("\"", 3);
                    // Note that namePointsSplit[0] should be an empty string.
                    command.name = namePointsSplit[1];
                    command.points = Integer.parseInt(namePointsSplit[2].trim());
                    break;

                case Plays:
                    String[] playSplit = remainingInput.split(" ", 3);
                    command.playerId = Integer.parseInt(playSplit[0]);
                    command.gameId = Integer.parseInt(playSplit[1]);
                    command.name = playSplit[2];
                    command.name = command.name.replaceAll("\"", ""); // remove quotation marks
                    break;

                case AddFriends:
                    String[] friendSplit = remainingInput.split(" ", 2);
                    command.playerId = Integer.parseInt(friendSplit[0]);
                    command.secondPlayerId = Integer.parseInt(friendSplit[1]);
                    break;

                case Achieve:
                    String[] achievedSplit = remainingInput.split(" ", 3);
                    command.playerId = Integer.parseInt(achievedSplit[0]);
                    command.gameId = Integer.parseInt(achievedSplit[1]);
                    command.achievementId = Integer.parseInt(achievedSplit[2]);
                    break;

                case FriendsWhoPlay:
                    String[] gameFriendsSplit = remainingInput.split(" ", 2);
                    command.playerId = Integer.parseInt(gameFriendsSplit[0]);
                    command.gameId = Integer.parseInt(gameFriendsSplit[1]);
                    break;

                case ComparePlayers:
                    String[] compareSplit = remainingInput.split(" ", 3);
                    command.playerId = Integer.parseInt(compareSplit[0]);
                    command.secondPlayerId = Integer.parseInt(compareSplit[1]);
                    command.gameId = Integer.parseInt(compareSplit[2]);
                    break;

                case SummarizePlayer:
                    command.playerId = Integer.parseInt(remainingInput);
                    break;

                case SummarizeGame:
                    command.gameId = Integer.parseInt(remainingInput);
                    break;

                case SummarizeAchievement:
                    String[] summarizeSplit = remainingInput.split(" ", 2);
                    command.gameId = Integer.parseInt(summarizeSplit[0]);
                    command.achievementId = Integer.parseInt(summarizeSplit[1]);
                    break;

                case AchievementRanking:
                    // This command requires no additional arguments.
                    break;
            }
        } catch (Exception e) {
            System.err.println("Invalid formatting for commmand type: " + command.commandTypeText);
            System.err.println("INPUT: " + input);
        }
        return command;
    }

    private CommandType stringToCommandType(String input) {
        if (stringCommandTypeMap.containsKey(input)) {
            return stringCommandTypeMap.get(input);
        }
        return CommandType.END;
    }
}
