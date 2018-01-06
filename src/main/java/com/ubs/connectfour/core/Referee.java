package com.ubs.connectfour.core;

public interface Referee {
    /**
     * Checks if last drop win the game.
     *
     * @return true if last drop win the game, false if it's still not clear or draw.
     */
    boolean judge(Board board, int lastRow, int lastColumn);

}
