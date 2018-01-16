package com.ubs.connectfour.core.impl;

import com.ubs.connectfour.core.Board;
import com.ubs.connectfour.core.Disc;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class SimpleBoard implements Board {
    private final int[][] matrix;
    // row index = 0 means top, row index = rows -1 means bottom
    private final int rows;
    private final int columns;
    private final int winCondition;
    private final LinkedList<Integer> history = new LinkedList<>();
    // Available columns, start index is 1
    private final Set<Integer> availableColumns = new TreeSet<>();

    public SimpleBoard() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS, DEFAULT_WIN_CONDITION);
    }

    SimpleBoard(int rows, int columns, int winCondition) {
        if (winCondition > rows && winCondition > columns) {
            throw new IllegalArgumentException("win condition must <= rows or <=columns");
        }
        this.rows = rows;
        this.columns = columns;
        this.winCondition = winCondition;
        this.matrix = new int[this.rows][this.columns];

        for (int column = 0; column < columns; column++)
            this.availableColumns.add(column + 1);
    }

    @Override
    public String showTips() {
        if (this.availableColumns.size() == this.columns) {
            return String.format("please choose column [1 - %s]", this.getColumns());
        }
        return String.format("please choose column %s", this.availableColumns);
    }

    private void checkColumn(int column) {
        if (column < 0 || column >= this.columns)
            throw new IllegalArgumentException("invalid column");
    }

    @Override
    public int drop(Disc disc, int column) {
        checkColumn(column);
        for (int row = this.rows - 1; row > -1; row--) {
            if (this.matrix[row][column] == Disc.NA.value) {
                this.matrix[row][column] = disc.value;
                if (row == 0) {
                    // Column is full now
                    this.availableColumns.remove(column + 1);
                }
                this.history.add(column);
                return row;
            }
        }
        throw new IllegalArgumentException("selected column is full");
    }

    @Override
    public void undo() {
        if (this.history.isEmpty()) {
            throw new IllegalArgumentException("no disc on board");
        }
        int column = this.history.removeLast();
        // Row index = 0 means top, row index = rows -1 means bottom
        for (int row = 0; row < this.rows; row++) {
            if (Disc.NA.value != this.matrix[row][column]) {
                this.matrix[row][column] = Disc.NA.value;
                break;
            }
        }
        // After a successful undo, the column should be available anyway.
        this.availableColumns.add(column + 1);
    }


    @Override
    public void display() {
        for (int[] row : this.matrix) {
            for (int value : row) {
                System.out.print("|");
                System.out.print(value == Disc.RED.value ? "R" : value == Disc.GREEN.value ? "G" : " ");
            }
            System.out.println("|");
        }
        System.out.println();
    }

    @Override
    public boolean isAvailable() {
        return !this.availableColumns.isEmpty();
    }

    @Override
    public int getWinCondition() {
        return this.winCondition;
    }

    @Override
    public int[][] getMatrix() {
        return this.matrix;
    }

    @Override
    public int getColumns() {
        return this.columns;
    }

    @Override
    public int getRows() {
        return this.rows;
    }

}
