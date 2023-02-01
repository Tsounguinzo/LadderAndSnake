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
 * The class that represents the game of Ladder and Snake.
 * It contains the players and the game board.
 * It also implements the game logic and rules.
 */
public class LadderAndSnack {
    /**
     * An array of players playing the game.
     */
    private Player[] players;
    /**
     * The game board on which the players play.
     */
    private GameBoard board;

    /**
     * The default constructor for the LadderAndSnack class.
     */
    public LadderAndSnack(){}

    /**
     * The constructor for the LadderAndSnack class that takes an array of players as input.
     * It initializes the players and creates a new game board with those players.
     * @param players An array of players playing the game.
     */
    public LadderAndSnack(Player[] players){
        this.players = players;
        this.board = new GameBoard(this.players);
    }

    /**
     * Returns the array of players playing the game.
     * @return An array of players playing the game.
     */
    public Player[] getPlayers() {
        return this.players;
    }

    /**
     * Returns the game board on which the players are playing.
     * @return The game board on which the players are playing.
     */
    public GameBoard getBoard() {
        return this.board;
    }

    /**
     * Generates a random number between 1 and 6 to simulate a die roll.
     * @return A random number between 1 and 6.
     */
    public static int flipDice(){
        return (int) ( (Math.random() * 6) + 1);
    }

    /**
     * Returns the index of the player in the players array.
     * @param player The player for which the index is to be found.
     * @return The index of the player in the players array.
     */
    private int getPlayerIndex(Player player){
        int index = -1;
        for(int i = 0; i < this.players.length; i++){
            if(player.equals(this.players[i])){
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Moves a player by a given number of spaces on the game board.
     * @param player The player to be moved.
     * @param spaces The number of spaces the player is to be moved.
     */
    public void movePlayer(Player player, int spaces) {
        // Update player position on the game board
        players[getPlayerIndex(player)].move(spaces);
    }

    /**
     * Implements the game logic and starts the game.
     */
    public void play() {
        LadderAndSnack Game = InputParser.parseInputString();

        if (Game == null) {
            InputParser.getMessage();
            return;
        }

        System.out.println("""
                              
                              Before we start you have to know where
                               the snakes and ladder are located😉
                              ________________________________________________________
                                      |   16   48   62    64   93   95   97   98     |
                              Snakes  |    ⬇    ⬇    ⬇     ⬇    ⬇    ⬇    ⬇    ⬇      |
                                      |    6   30   19    60   68   24   76   78     |
                              ________|______________________________________________|
                                      |                                              |
                                      | 38   14   31   42   84   44   67   91   100  |
                              Ladders |  ⬆    ⬆    ⬆    ⬆    ⬆    ⬆    ⬆    ⬆     ⬆   |
                                      |  1    4    9   21   28   36   51   71    80  |
                              ________|______________________________________________|
                              """);
        while (true) {

            for (Player player : Game.getPlayers()) {

                int move = LadderAndSnack.flipDice();
                System.out.println(player.getName() + " got a dice value of " + move);
                Game.movePlayer(player, move);

                Game.getBoard().ladderAction(player, player.getPosition());
                Game.getBoard().snackAction(player, player.getPosition());
                Game.getBoard().kittingPlayer(player);

                if (Game.getBoard().gameWon()) {
                    System.out.println();
                    Game.getBoard().printBoard(Game.getPlayers());
                    System.out.println("\nGame over; " + player.getName() + " won the gaaaaammmeeeee!!!!");
                    return;
                }

            }
            System.out.println();
            Game.getBoard().printBoard(Game.getPlayers());
            System.out.println();

        }
    }
}
