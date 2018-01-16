package com.ubs.connectfour.core;

/**
 * Represents a connect four game player.
 */
public interface Player {

    String UNDO = "u";

    /**
     * Choose next move, return true if player winners else is not clear.
     */
    boolean choose(Board board, Referee referee);

    /**
     * Returns a short description of player.
     */
    String getDescription();


}
