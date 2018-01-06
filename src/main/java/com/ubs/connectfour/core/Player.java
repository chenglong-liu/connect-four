package com.ubs.connectfour.core;

/**
 * Represents a connect four game player.
 */
public interface Player {

    /**
     * Chooses column for next drop.
     *
     * @throws IllegalArgumentException if input is not an integer.
     */
    int choose();

    /**
     * Returns player's selected disc.
     */
    Disc getDisc();

    /**
     * Returns a short description of player.
     */
    String getDescription();


}
