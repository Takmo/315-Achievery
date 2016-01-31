package com.bitwisehero.course315.achievery;

public class Command {

    public CommandType commandType;     // Type of command input.
    public String commandTypeText;      // The text name for the command input.

    public int achievementId;           // ID of achievement (if applicable)
    public int gameId;                  // ID of game (if applicable)
    public int playerId;                // ID of player (if applicable)

    public String name;                 // name parameter for all commands
    public int secondPlayerId;          // ID of second player (if applicable)
    public int points;                  // The points that an achievement awards.

    // Constructor - just zero everything out, except for type.
    public Command() {
        this.commandType = CommandType.END;
        this.commandTypeText = "NONE";
        this.achievementId = -1;
        this.gameId = -1;
        this.playerId = -1;
        this.name = "";
        this.secondPlayerId = -1;
        this.points = -1;
    }
}
