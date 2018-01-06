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

    // Checks if connected 4 (Board#getWinCondition) discs in one row.
    static boolean isHorizontal(Board board, int row, int column, int value) {
        int condition = board.getWinCondition();
        for (int i = Math.max(0, column - condition + 1); i <= column; i++) {
            int j = i + condition;
            if (j - 1 < board.getColumns() && IntStream.range(i, j).allMatch(e -> board.getMatrix()[row][e] == value)) {
                return true;
            }
        }
        return false;
    }

    // Checks if connected 4 (Board#getWinCondition) discs in one column.
    static boolean isVertical(Board board, int row, int column, int value) {
        int condition = board.getWinCondition();
        for (int i = Math.max(0, row - condition + 1); i <= row; i++) {
            int j = i + condition;
            if (j - 1 < board.getRows() && IntStream.range(i, j).allMatch(e -> board.getMatrix()[e][column] == value)) {
                return true;
            }
        }
        return false;
    }

    // Checks if connected 4 (Board#getWinCondition) discs in diagonal line
    static boolean isDiagonal(Board board, int row, int column, int value) {
        int condition = board.getWinCondition();
        // Bottom left to upper right
        for (int k = Math.max(0, column - condition + 1); k <= column; k++) {
            int i = k;
            int j = row + column - i;
            if (i + condition <= board.getColumns() && j < board.getRows() && j - condition + 1 >= 0) {
                if (IntStream.range(0, condition).allMatch(e -> board.getMatrix()[j - e][i + e] == value)) {
                    return true;
                }
            }
        }
        // Upper Left to bottom right
        for (int k = Math.max(0, column - condition + 1); k <= column; k++) {
            int i = k;
            int j = row - (column - i);
            if (i + condition <= board.getColumns() && j >= 0 && j + condition <= board.getRows()) {
                if (IntStream.range(0, condition).allMatch(e -> board.getMatrix()[j + e][i + e] == value)) {
                    return true;
                }
            }
        }
        return false;
    }
}
