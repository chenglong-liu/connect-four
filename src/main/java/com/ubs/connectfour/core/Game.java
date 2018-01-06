package com.ubs.connectfour.core;

import com.ubs.connectfour.core.impl.SimpleBoard;
import com.ubs.connectfour.core.impl.SimpleReferee;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game {

    private final Board board;
    private final Referee referee;
    private final List<Player> players;

    public Game(Player... players) {
        this(new SimpleBoard(), new SimpleReferee(), Arrays.asList(players));
    }

    private Game(Board board, Referee referee, List<Player> players) {
        this.board = board;
        this.referee = referee;
        this.players = Collections.unmodifiableList(players);
    }

    public void play() {
        board.display();
        int move = 0;
        while (board.isAvailable()) {
            // Players take alternate turns
            Player player = players.get(move++ % players.size());
            System.out.print(String.format("%s - %s: ", player, board.showTips()));

            // Position of this drop
            int row, column;

            // Try until player chooses a column and drop to board successfully
            for (; ; ) {
                try {
                    column = player.choose() - 1;
                    row = board.drop(player.getDisc(), column);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.print(String.format("%s - %s, %s: ", player, e.getMessage(), board.showTips()));
                }
            }

            board.display();

            // Referee checks if current player win the game
            if (referee.judge(board, row, column)) {
                System.out.println(String.format("%s - wins!", player));
                return;
            }
        }
        // Board is full, the game finishes, and it is considered a draw
        System.out.println(String.format("%s - draw!", players));
    }

}
