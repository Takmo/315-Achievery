package com.bitwisehero.course315.achievery;

public class Command {

    public CommandType commandType;     // Type of command input.

    public int achievementId;           // ID of achievement (if applicable)
    public int gameId;                  // ID of game (if applicable)
    public int playerId;                // ID of player (if applicable)

    public String name;                 // name of achievement or player (if applicable)
    public int secondaryPlayerId;       // ID of second player (if applicable)

    // Constructor - just zero everything out, except for type.
    public Command(CommandType commandType) {
        this.commandType = commandType;
        this.achievementId = -1;
        this.gameId = -1;
        this.playerId = -1;
        this.name = "";
        this.secondaryPlayerId = -1;
    }

    // Mark that the Command is no longer valid.
    public void cancel() {
        this.commandType = CommandType.END;
    }
}
