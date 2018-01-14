package com.ubs.connectfour.core.impl;

import com.ubs.connectfour.core.Board;
import com.ubs.connectfour.core.Referee;

import java.util.stream.IntStream;

public class SimpleReferee implements Referee {

    @Override
    public boolean judge(Board board, int row, int column) {
        int value = board.getMatrix()[row][column];
        return isHorizontal(board, row, column, value)
                || isVertical(board, row, column, value)
                || isDiagonal(board, row, column, value);
    }

    private static boolean isConnectedFour(Board board, int row, int column, int value, int rDelta, int cDelta) {
        int condition = board.getWinCondition();
        // Assume column is 4, row is 4, and row delta is 0, column delta is 1
        for (int i = 0; i < condition; i++) {
            // r is always 4
            int r = row - rDelta * i;
            // So need to check c in [4, 3, 2, 1]
            int c = column - cDelta * i;
            // When c = 4, need to check if all values at columns [4, 5, 6, 7] == value
            boolean connectedFour = IntStream.range(0, condition).allMatch(e -> {
                int x = r + e * rDelta;
                int y = c + e * cDelta;
                return 0 <= x && x < board.getRows() && 0 <= y
                        && y < board.getColumns() && board.getMatrix()[x][y] == value;
            });
            if (connectedFour)
                return true;
        }
        return false;
    }

    // Checks if connected 4 (Board#getWinCondition) discs in one row.
    static boolean isHorizontal(Board board, int row, int column, int value) {
        return isConnectedFour(board, row, column, value, 0, 1);
    }

    // Checks if connected 4 (Board#getWinCondition) discs in one column.
    static boolean isVertical(Board board, int row, int column, int value) {
        return isConnectedFour(board, row, column, value, 1, 0);
    }

    // Checks if connected 4 (Board#getWinCondition) discs in diagonal line
    static boolean isDiagonal(Board board, int row, int column, int value) {
        return isConnectedFour(board, row, column, value, -1, 1) ||
                isConnectedFour(board, row, column, value, 1, 1);
    }
}
