package com.ubs.connectfour.core;

/**
 * Board for connect four game.
 */
public interface Board {
    int DEFAULT_ROWS = 6;
    int DEFAULT_COLUMNS = 7;
    int DEFAULT_WIN_CONDITION = 4;

    /**
     * Drops a disc to the board on given column.
     *
     * @return fallen row of this disc.
     * @throws IllegalArgumentException if selected column is out of range or full.
     */
    int drop(Disc disc, int column);

    /**
     * Remove a disc from bottom of given column, discs above the removed one will shift down one row.
     *
     * @return row of removed disc.
     * @throws IllegalArgumentException if selected column is out of range or has no given disc.
     */
    int undo(Disc disc, int column);

    /**
     * Checks if available for new disc.
     */
    boolean isAvailable();

    void display();

    String showTips();

    int[][] getMatrix();

    int getColumns();

    int getRows();

    int getWinCondition();

}