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

/**
 * GameBoard.java
 * A class to represent the game board of a snake and ladder game.
 */
public class GameBoard {

    /**
     * ANSI reset code to reset the terminal color.
     */
    public static final String ANSI_RESET = "\u001B[0m";
    /**
     * ANSI yellow color code.
     */
    private static final String ANSI_YELLOW = "\u001B[33m";
    /**
     * ANSI blue color code.
     */
    private static final String ANSI_BLUE = "\u001B[34m";

    /**
     * An array of ladder objects on the board.
     */
    private Ladder[] ladders;
    /**
     * An array of snake objects on the board.
     */
    private Snake[] snakes;
    /**
     * An array of player objects playing the game.
     */
    private final Player[] players;
    /**
     * A 2-D array representing the game board interface.
     */
    private String[][] boardInterface;

    /**
     * A constructor to initialize the game board with players.
     * @param players An array of player objects playing the game.
     */
    public GameBoard(Player[] players) {
        setBoardInterface();
        setLadders();
        setSnacks();
        this.players = players;
    }

    /**
     * A helper method to get the index of a player in the players array.
     * @param player The player object whose index needs to be found.
     * @return The index of the player in the players array.
     */
    private int getPlayersIndex(Player player) {
        int index = -1;
        for (int i = 0; i < this.players.length; i++) {
            if (player.equals(this.players[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * A helper method to check if there is a ladder at a given position.
     * @param position The position on the game board to check for a ladder.
     * @return True if there is a ladder at the given position, False otherwise.
     */
    private boolean isLadder(int position) {
        return containsKey(this.ladders, position);
    }

    /**
     * A helper method to check if there is a snake at a given position.
     * @param position The position on the game board to check for a snake.
     * @return True if there is a snake at the given position, False otherwise.
     */
    private boolean isSnake(int position) {
        return containsKey(this.snakes, position);
    }


    /**
     * A helper method to get the end point of a ladder given its start point
     * @param startPoint the beginning of a ladder
     * @return the end point of the ladder with the given starting point
     */
    private int getLadderEndPoint(int startPoint) {
        int endPoint = -1;
        for (Ladder ladder : this.ladders) {
            if (ladder.getStart() == startPoint) {
                endPoint = ladder.getEnd();
                break;
            }
        }
        return endPoint;
    }

    /**
     * This method performs the ladder action.
     * If the current player's position is on a ladder,
     * it updates the player's position to the end of the ladder.
     *
     * @param player the player whose position is being checked
     * @param position the current position of the player
     */
    public void ladderAction(Player player, int position) {
        if (isLadder(position)) {
            this.players[getPlayersIndex(player)].changePosition(getLadderEndPoint(position));
            System.out.println("Oh it looks like " + this.players[getPlayersIndex(player)].getName() +
                    " is on a ladder ups jumping to " + getLadderEndPoint(position));
        }
    }

    /**
     * The method checks if any two players are on the same position,
     * If the players are on the same position, the latter is kidded back to the beginning
     * @param testPlayer The player to check and potentially kick off another player
     */
    public void kittingPlayer(Player testPlayer) {
        for (Player player : this.players) {
            if (player.equals(testPlayer)) continue;
            if (player.getPosition() == testPlayer.getPosition()) {
                player.changePosition(0);
                System.out.println("Oh Oh Oh " + player.getName() + " you have been kicked off by " + testPlayer.getName());
            }
        }
    }

    /**
     * A helper method to get the end point of a snake given its start point
     * @param startPoint the beginning of a snake
     * @return the end point of the snake with the given starting point
     */
    private int getSnakeEndPoint(int startPoint) {
        int endPoint = -1;
        for (Snake snake : this.snakes) {
            if (snake.getStart() == startPoint) {
                endPoint = snake.getEnd();
                break;
            }
        }
        return endPoint;
    }

    public void snackAction(Player player, int position) {
        if (isSnake(position)) {
            this.players[getPlayersIndex(player)].changePosition(getSnakeEndPoint(position));
            System.out.println("Oh it looks like " + this.players[getPlayersIndex(player)].getName() +
                    " is on a snack ups going to " + getSnakeEndPoint(position));
        }
    }

    /**
     * gameWon method checks if any player has reached the final position (100) on the game board.
     * @return a boolean value indicating whether the game has been won (true) or not (false)
     */
    public boolean gameWon() {
        // Check if any player has reached the final position on the game board
        return players[0].getPosition() == 100 || players[1].getPosition() == 100;
    }

    /**
     * This method checks if the start of any ladder in the input ladders array
     * matches the given key.
     *
     * @param ladders An array of Ladder objects
     * @param key The key to search for
     * @return A boolean value indicating whether a match was found or not.
     */
    private boolean containsKey(Ladder[] ladders, int key) {
        boolean truth = false;
        for (Ladder ladder : ladders) {
            if (ladder.getStart() == key) {
                truth = true;
                break;
            }
        }
        return truth;
    }

    /**
     * This overloaded method checks if the start of any snake in the input snakes array
     * matches the given key.
     *
     * @param snakes An array of Snake objects
     * @param key The key to search for
     * @return A boolean value indicating whether a match was found or not.
     */
    private boolean containsKey(Snake[] snakes, int key) {
        boolean truth = false;
        for (Snake snake : snakes) {
            if (snake.getStart() == key) {
                truth = true;
                break;
            }
        }
        return truth;
    }

    /**
     * Sets the Ladders on the board with their start and end positions.
     */
    private void setLadders() {
        ladders = new Ladder[9];

        int[] ladderStartPositions = {1, 4, 9, 21, 28, 36, 51, 71, 80};
        int[] ladderEndPositions = {38, 14, 31, 42, 84, 44, 67, 91, 100};

        for (int i = 0; i < ladders.length; i++) {
            ladders[i] = new Ladder(ladderStartPositions[i], ladderEndPositions[i]);
        }
    }

    /**
     * Sets the Snakes on the board with their start and end positions.
     */
    private void setSnacks() {
        snakes = new Snake[8];

        int[] snakeStartPositions = {16, 48, 62, 64, 93, 95, 97, 98};
        int[] snakeEndPositions = {6, 30, 19, 60, 68, 24, 76, 78};

        for (int i = 0; i < snakes.length; i++) {
            snakes[i] = new Snake(snakeStartPositions[i], snakeEndPositions[i]);
        }
    }

    /**
     * Method to fill the board with numbers from 1 to 100,
     * with all even numbers being yellow and odd numbers blue
     */
    private void setBoardInterface() {
        boardInterface = new String[10][10];
        int value = 1;
        for (int i = 0; i < boardInterface.length; i++) {
            for (int j = 0; j < boardInterface[i].length; j++, value++) {
                if (value % 2 == 0) {
                    boardInterface[i][j] = ANSI_YELLOW + value + ANSI_RESET;
                } else {
                    boardInterface[i][j] = ANSI_BLUE + value + ANSI_RESET;
                }
            }
            if (i % 2 != 0) boardInterface[i] = reverse(boardInterface[i]);
        }
    }

    /**
     * Method to print the game board with player positions.
     *
     * @param players the players in the game
     */
    public void printBoard(Player[] players) {
        int play = 0; // flag to check if player is present on this cell

        for (int i = this.boardInterface.length - 1; i >= 0; i--) {
            for (int j = 0; j < this.boardInterface[i].length; j++) {
                for (Player player : players) {
                    if (this.boardInterface[i][j].equals(ANSI_YELLOW + player.getPosition() + ANSI_RESET)
                            || this.boardInterface[i][j].equals(ANSI_BLUE + player.getPosition() + ANSI_RESET)) {

                        play = 1; // set flag if player is present

                        // print player initial
                        if (i == 0) {
                            System.out.print(" " + player.getIdentifier() + "  ");
                        } else if (i < this.boardInterface.length - 1) {
                            System.out.print(" " + player.getIdentifier() + "  ");
                        } else {
                            System.out.print(" " + player.getIdentifier() + "  ");
                        }
                    }
                }
                if (play == 1){ // reset flag and continue if player is present
                    play = 0;
                    continue;
                }

                // print the value if player not present
                    if (i == 0) {
                        System.out.print(" " + this.boardInterface[i][j] + "  ");
                    } else if (i < this.boardInterface.length - 1) {
                        System.out.print(" " + this.boardInterface[i][j] + " ");
                    } else {
                        System.out.print(" " + this.boardInterface[i][j] + " ");
                    }

            }
            // newline after each row
            System.out.println();
        }
    }

    /**
     * A helper method to reverse an array of strings.
     *
     * @param strings the array of strings to be reversed
     * @return the reversed array of strings
     */
    private String[] reverse(String[] strings) {
        String[] reverse = new String[strings.length];
        for (int i = 0; i < reverse.length; i++) {
            reverse[i] = strings[strings.length - 1 - i];
        }
        return reverse;
    }

    }