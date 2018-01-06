package com.ubs.connectfour.core.impl;

import com.ubs.connectfour.core.Board;
import com.ubs.connectfour.core.impl.SimpleBoard;
import com.ubs.connectfour.core.impl.SimpleReferee;
import org.junit.Assert;
import org.junit.Test;

public class SimpleRefereeTest {

    @Test
    public void isHorizontal() throws Exception {
        Board board = new SimpleBoard();
        int[][] matrix = board.getMatrix();
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[0][3] = 1;
        matrix[0][4] = 1;
        int matchPoints = 0;
        for (int i = 0; i < Board.DEFAULT_ROWS; i++) {
            for (int j = 0; j < Board.DEFAULT_COLUMNS; j++) {
                if (SimpleReferee.isHorizontal(board, i, j, 1)) {
                    matchPoints++;
                }
            }
        }
        Assert.assertEquals(4, matchPoints);
    }


    @Test
    public void isVertical() throws Exception {
        Board board = new SimpleBoard();
        int[][] matrix = board.getMatrix();
        matrix[1][0] = 1;
        matrix[2][0] = 1;
        matrix[3][0] = 1;
        matrix[4][0] = 1;
        int matchPoints = 0;
        for (int i = 0; i < Board.DEFAULT_ROWS; i++) {
            for (int j = 0; j < Board.DEFAULT_COLUMNS; j++) {
                if (SimpleReferee.isVertical(board, i, j, 1)) {
                    matchPoints++;
                }
            }
        }
        Assert.assertEquals(4, matchPoints);
    }


    @Test
    public void isDiagonal() {
        Board board = new SimpleBoard();
        int[][] matrix = board.getMatrix();
        matrix[0][6] = 1;
        matrix[1][5] = 1;
        matrix[2][4] = 1;
        matrix[3][3] = 1;
        matrix[1][2] = 1;
        matrix[2][3] = 1;
        matrix[3][4] = 1;
        matrix[4][5] = 1;
        int matchPoints = 0;
        for (int i = 0; i < Board.DEFAULT_ROWS; i++) {
            for (int j = 0; j < Board.DEFAULT_COLUMNS; j++) {
                if (SimpleReferee.isDiagonal(board, i, j, 1)) {
                    matchPoints++;
                }
            }
        }
        Assert.assertEquals(8, matchPoints);
    }
}