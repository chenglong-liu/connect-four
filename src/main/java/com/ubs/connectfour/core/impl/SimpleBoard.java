package com.ubs.connectfour.core.impl;

import com.ubs.connectfour.core.Board;
import com.ubs.connectfour.core.Disc;

import java.util.ArrayList;
import java.util.List;

public class SimpleBoard implements Board {
    private final int[][] matrix;
    private final int rows;
    private final int columns;
    private final int winCondition;

    // Available columns, start index is 1
    private final List<Integer> availableColumns = new ArrayList<>();

    public SimpleBoard() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS, DEFAULT_WIN_CONDITION);
    }

    SimpleBoard(int rows, int columns, int winCondition) {
        assert winCondition > 0;
        this.rows = rows;
        this.columns = columns;
        this.winCondition = winCondition;
        this.matrix = new int[this.rows][this.columns];

        for (int i = 1; i < columns + 1; i++)
            this.availableColumns.add(i);
    }

    @Override
    public String showTips() {
        if (this.availableColumns.size() == this.columns) {
            return String.format("please choose column [1 - %s]", this.getColumns());
        }
        return String.format("please choose column %s", this.availableColumns);
    }

    @Override
    public int drop(Disc disc, int column) {
        if (column < 0 || column >= this.columns) {
            throw new IllegalArgumentException("invalid column");
        }
        for (int row = this.rows - 1; row > -1; row--) {
            if (this.matrix[row][column] == Disc.NA.value) {
                this.matrix[row][column] = disc.value;
                if (row == 0) {
                    // Column is full now
                    this.availableColumns.remove(this.availableColumns.indexOf(column + 1));
                }
                return row;
            }
        }
        throw new IllegalArgumentException("selected column is full");
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
