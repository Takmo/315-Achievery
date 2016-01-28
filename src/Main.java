package com.bitwisehero.course315.achievery;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private enum CommandType {
        AddPlayer,
        AddGame,
        AddAchievement,
        Plays,
        AddFriends,
        Achieve,
        FriendsWhoPlay,
        ComparePlayers,
        SummarizePlayer,
        SummarizeGame,
        SummarizeAchievement,
        AchievementRanking,
        END
    }

    private static HashMap<String, CommandType> stringCommandTypeMap;

    public static void main(String[] args) {
        // Setup friendly string to command map.
        initializeStringCommandTypeMap();

        // Loop through input.
        Scanner inputScanner = new Scanner(System.in);
        CommandType unexpectedCommandType = CommandType.END;

        while (inputScanner.hasNext()) {

            // If an unexpected command was found, continue from there.
            // Otherwise, read the next command.
            CommandType commandInput = CommandType.END;
            if (unexpectedCommandType != CommandType.END) {
                commandInput = unexpectedCommandType;
            } else {
                commandInput = stringToCommandType(inputScanner.next());
            }

            // Handle the command now.
            switch (commandInput) {
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
                    break;
            }
        }
    }

    private static void initializeStringCommandTypeMap() {
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

    private static CommandType stringToCommandType(String input) {
        if (stringCommandTypeMap.containsKey(input)) {
            return stringCommandTypeMap.get(input);
        }
        return CommandType.END;
    }
}
