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
 * Player class represents a player in a game.
 */
public class Player {
    private final String name;
    private int position;
    private final char identifier;


    /**
     * Constructs a Player object with the given name, initial position and identifier.
     *
     * @param name the name of the player
     * @param position the initial position of the player
     * @param identifier the identifier of the player
     */
    public Player(String name, int position, char identifier) {
        this.name = name;
        this.position = position;
        this.identifier = identifier;
    }

    /**
     * Updates the player's position based on the dice flip.
     *
     * @param diceFlip the value of the dice flip
     */
    public void move(int diceFlip) {
        this.position = (this.position + diceFlip > 100)? this.position - diceFlip : this.position + diceFlip;
    }

    /**
     * Updates the player's position to the new position.
     *
     * @param newPosition the new position of the player
     */
    public void changePosition(int newPosition) {
        this.position = newPosition;
    }

    /**
     * Returns the current position of the player.
     *
     * @return the current position of the player
     */
    public int getPosition(){
        return this.position;
    }

    /**
     * Returns the identifier of the player.
     *
     * @return the identifier of the player
     */
    public char getIdentifier() {
        return identifier;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public  String getName(){
        return this.name;
    }

    /**
     * Compares this player object to the specified player object.
     *
     * @param player the player object to compare with
     * @return true if both players have the same name and position, false otherwise
     */
    public boolean equals(Player player){
        return this.name.equals(player.getName()) && this.position == player.getPosition();
    }
}
