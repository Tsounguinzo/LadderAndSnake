/*
 * Copyright (c) 2022 Beaudelaire Tsoungui Nzodoumkouo. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under My consent.
 *
 * This code is shared on GitHub in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY OF FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Please contact Me at +1 438 509 3906
 * or LinkedIn: https://www.linkedin.com/in/beaudelaire-tsoungui-nzodoumkouo-809744231
 * if you need additional information or have any questions.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The InputParser class is responsible for processing user inputs and returning the initialized player information.
 */
public class InputParser {
    private static String errorMessage;

    /**
     * parseInputString is a public static method that returns LadderAndSnack object after initializing the player information.
     *
     * @return LadderAndSnack object
     */
    public static LadderAndSnack parseInputString(){
        Scanner sc = new Scanner(System.in);

        // Get the number of players
        System.out.print("\nHi enter the number of players: ");
        byte numPlayer = 0;
        boolean inputIsValid = false;
        int invalidInputCount = 0;

        while (!inputIsValid) {
            // If there are invalid inputs, prompt the user to enter again
            if(invalidInputCount > 0){
                sc.nextLine();
                if(invalidInputCount == 1){
                    System.out.print("What you've entered is not an integer, please enter an integer, try again: ");
                } else {
                    System.out.print("Invalid input. Please enter an integer: ");
                }
            }

            try {
                numPlayer = sc.nextByte();
                sc.nextLine();
                inputIsValid = true;

                // If number of players is greater than 2, set it to 2
                if (numPlayer > 2) {
                    System.out.println("\nInitialization was attempted for " + numPlayer + " players; however, this is only\n" +
                            "expected for an extended version the game. Value will be set to 2\n");
                    numPlayer = 2;
                }

                // If number of players is less than 2, return null
                if (numPlayer < 2) {
                    errorMessage = "Error: Cannot execute the game with less than 2 players! Will exit";
                    return null;
                }

            } catch (InputMismatchException e) {
                invalidInputCount++;
            }
        }

        // Initialize the player information
        Player[] players = new Player[numPlayer];
        String[] playersName = new String[numPlayer];
        StringBuilder greetings = new StringBuilder();
        char[] playersIdentifier = new char[numPlayer];

        for (int i = 0; i < players.length; i++){

            System.out.print("\nEnter the name of player #" + (i+1) + ": ");
            playersName[i] = sc.nextLine();

            System.out.print("\nEnter a character, excluding [0 - 9] to serve as an identifier for player #" + (i+1) + ": ");
            String id =  sc.nextLine();

            playersIdentifier[i] = (id.length() != 0)? id.charAt(0) : ' ';
            if(id.length() == 0) id = " ";
            boolean flag = false;

            while (!flag){
                flag = true;
            for (int a = 0; a < i; a++) {
                while (playersIdentifier[a] == id.charAt(0)) {
                    System.out.println("\nThe identifier \"" + playersIdentifier[i] + "\"" +
                            " has already being assigned to a player");
                    System.out.print("Choose a different identifier pls: ");
                    id = sc.nextLine().toUpperCase();
                    id = (id.length() != 0)? id : " ";
                    System.out.println();
                }
            }

                while (id.length() != 1 || id.matches(".*[0-9].*") || id.isBlank()) {
                    System.out.print("Enter ONE character excluding [0 - 9] please: ");
                    id = sc.nextLine();
                    flag = false;
                }
                playersIdentifier[i] = id.charAt(0);

            }

            if(i == (players.length - 1) ) {
                greetings.append("Hey ").append(playersName[i]).append(".");
            } else {
                greetings.append("Hey ").append(playersName[i]).append(", ");
            }
        }

        System.out.println("\n" + greetings);
        System.out.println("lets decide who goes first with the good old tie rolling");
        System.out.println();

        byte[] playersPosition = new byte[numPlayer];
        for (int i = 0; i < players.length; i++){
            playersPosition[i] = (byte) LadderAndSnack.flipDice();
        }

        for (int i = 0; i < players.length; i++){
            players[i] = new Player(playersName[i], playersPosition[i], playersIdentifier[i]);
        }

        byte numOfAttemps = 0;

        for ( Player player : players){
            System.out.println(player.getName() + " got a dice value of " + player.getPosition());
        }

        for (int i = 0; i < players.length; i++){
            for (int j = (i + 1); j < players.length; j++){
                while (players[i].getPosition() == players[j].getPosition()){

                    System.out.println("\nOh my god "  + players[i].getName() + " and " + players[j].getName() +
                            "  got the same dice value. Attempting to break the tie\n");

                    players[i].changePosition((byte) LadderAndSnack.flipDice());
                    players[j].changePosition((byte) LadderAndSnack.flipDice());

                    System.out.println(players[i].getName() + " now have a dice value of " + players[i].getPosition());
                    System.out.println(players[j].getName() + " now have a dice value of " + players[j].getPosition());
                    numOfAttemps++;
                }

                if(numOfAttemps != 0) System.out.println("\nIt took " + numOfAttemps +
                        ( (numOfAttemps == 1)? " attempt" : " attempts") + " to break that tie!");
            }
        }

        Player[] playersInOderOfStarting = sortPlayers(players);

        System.out.println("\nReached final decision on order of playing: ");
        for (int i = 0; i < playersInOderOfStarting.length; i++){
            System.out.println((i+1) + ". " + playersInOderOfStarting[i].getName());
        }

        System.out.println();

        for (Player player : playersInOderOfStarting) player.changePosition(0);

        return new LadderAndSnack(playersInOderOfStarting);
    }

    /**
     * This method prints the error message.
     */
    public static void getMessage() {
        System.out.println(errorMessage);
    }

    /**
     * This method sorts the players according to their positions.
     *
     * @param players the array of players to sort
     * @return a sorted array of players
     */
    public static Player[] sortPlayers(Player[] players){
        Player[] sorted = players.clone();

        boolean check = true;
        while(check){
            check = false;
            for(int i =0; i<sorted.length-1; i++){
                if(sorted[i].getPosition() < sorted[i+1].getPosition()){
                    Player temp = sorted[i]; sorted[i] = sorted[i+1];
                    sorted[i+1] = temp;
                    check = true;
                }
            }
        }
        return sorted;
    }
}
