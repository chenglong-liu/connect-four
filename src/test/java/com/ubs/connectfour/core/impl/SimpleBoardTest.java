package com.ubs.connectfour.core.impl;

import com.ubs.connectfour.core.Board;
import com.ubs.connectfour.core.Disc;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SimpleBoardTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void display1() throws Exception {
        (new SimpleBoard()).display();
    }

    @Test
    public void display2() throws Exception {
        Board board = new SimpleBoard(3, 2, 3);
        board.drop(Disc.GREEN, 0);
        board.drop(Disc.GREEN, 0);
        board.drop(Disc.RED, 1);
        board.drop(Disc.RED, 1);
        board.display();
    }

    @Test
    public void drop1() throws Exception {
        Board board = new SimpleBoard(3, 2, 2);
        Assert.assertEquals(2, board.drop(Disc.GREEN, 0));
        Assert.assertEquals(1, board.drop(Disc.GREEN, 0));
        Assert.assertEquals(0, board.drop(Disc.GREEN, 0));
        Assert.assertEquals(2, board.drop(Disc.RED, 1));
        Assert.assertEquals(1, board.drop(Disc.RED, 1));
        Assert.assertEquals(0, board.drop(Disc.RED, 1));
    }

    @Test
    public void drop2() throws Exception {
        Board board = new SimpleBoard(2, 1, 2);

        thrown.expect(IllegalArgumentException.class);
        board.drop(Disc.GREEN, 1);
    }

    @Test
    public void drop3() throws Exception {
        Board board = new SimpleBoard(2, 1, 2);
        Assert.assertTrue(board.isAvailable());

        board.drop(Disc.GREEN, 0);
        board.drop(Disc.GREEN, 0);
        Assert.assertFalse(board.isAvailable());

        thrown.expect(IllegalArgumentException.class);
        board.drop(Disc.GREEN, 0);
    }

    @Test
    public void undo() throws Exception {
        Board board = new SimpleBoard(3, 1, 3);
        board.drop(Disc.GREEN, 0);
        board.drop(Disc.RED, 0);
        board.drop(Disc.GREEN, 0);
        Assert.assertFalse(board.isAvailable());

        Assert.assertEquals(0, board.undo(Disc.GREEN, 0));
        Assert.assertTrue(board.getMatrix()[1][0] == Disc.RED.value);
        Assert.assertEquals(2, board.undo(Disc.GREEN, 0));
        Assert.assertTrue(board.getMatrix()[2][0] == Disc.RED.value);

        Assert.assertTrue(board.isAvailable());

        board.drop(Disc.RED, 0);
        board.drop(Disc.RED, 0);

        Assert.assertFalse(board.isAvailable());

        thrown.expect(IllegalArgumentException.class);
        board.undo(Disc.GREEN, 0);
    }

}