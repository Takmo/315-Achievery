package com.bitwisehero.course315.achievery;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Our CommandParser conerts individual lines into Commands
        // that can be processed.
        CommandParser parser = new CommandParser();

        // Our Tracker holds all of the information in our database
        // and is responsible for processing Commands.
        Tracker tracker = new Tracker();

        // Let's open up our input and, line by line, parse
        // Commands and process them.
        Scanner inputScanner = new Scanner(System.in);
        while (inputScanner.hasNext()) {
            String input = inputScanner.nextLine();
            // Skip blank lines.
            if (input.isEmpty()) {
                continue;
            }
            Command command = parser.parse(input);
            tracker.processCommand(command);
        }
    }
}
