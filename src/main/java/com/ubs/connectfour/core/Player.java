package com.ubs.connectfour.core;

/**
 * Represents a connect four game player.
 */
public interface Player {

    String UNDO = "u";

    /**
     * Choose next move, return winning disc or NA if no winner yet.
     */
    Disc choose(Board board, Referee referee);

    /**
     * Returns player's selected disc.
     */
    Disc getDisc();

    /**
     * Returns a short description of player.
     */
    String getDescription();


}
