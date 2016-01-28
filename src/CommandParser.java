package com.bitwisehero.course315.achievery;

import java.util.HashMap;
import java.util.StringTokenizer;

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

    public Command parse(String input) {
        StringTokenizer tokens = new StringTokenizer(input);
        if (!tokens.hasMoreTokens()) {
            System.err.println("Parse error: no tokens in string.");
            return new Command(CommandType.END);
        }

        // Get the type of command being input and start setting up the command.
        String commandTypeText = tokens.nextToken();
        CommandType commandType = stringToCommandType(commandTypeText);
        Command command = new Command(commandType);

        // Now handle each command separately.
        switch (command.commandType) {

            case AddPlayer:
                break;

            case AddGame:
                break;

            case AddAchievement:
                break;

            case Plays:
                break;

            case AddFriends:
                break;

            case Achieve:
                break;

            case FriendsWhoPlay:
                break;

            case ComparePlayers:
                break;

            case SummarizePlayer:
                break;

            case SummarizeGame:
                break;

            case SummarizeAchievement:
                break;

            case AchievementRanking:
                // This command requires no additional arguments.
                break;
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
