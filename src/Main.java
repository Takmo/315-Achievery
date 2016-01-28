package com.bitwisehero.course315.achievery;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Setup the CommandParser.
        CommandParser parser = new CommandParser();

        // Loop through input.
        Scanner inputScanner = new Scanner(System.in);
        while (inputScanner.hasNext()) {
            String input = inputScanner.nextLine();
            // Skip blank lines.
            if (input == "") {
                continue;
            }
            Command command = parser.parse(input);
        }
    }
}
