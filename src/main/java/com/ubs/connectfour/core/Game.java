package com.ubs.connectfour.core;

import com.ubs.connectfour.core.impl.SimpleBoard;
import com.ubs.connectfour.core.impl.SimpleReferee;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a connect four game.
 */
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
            System.out.print(String.format("%s - %s: ", player.getDescription(), board.showTips()));

            // Position of this drop
            int row, column;

            // Try until player chooses a column and drop to board successfully
            for (; ; ) {
                try {
                    column = player.choose() - 1;
                    row = board.drop(player.getDisc(), column);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.print(String.format("%s - %s, %s: ",
                            player.getDescription(), e.getMessage(), board.showTips()));
                }
            }

            board.display();

            // Referee checks if current player win the game
            if (referee.judge(board, row, column)) {
                System.out.println(String.format("%s - wins!", player.getDescription()));
                return;
            }
        }
        // Board is full, the game finishes, and it is considered a draw
        List<String> playersDescription = players.stream().map(Player::getDescription).collect(Collectors.toList());
        System.out.println(String.format("%s - draw!", String.join(", ", playersDescription)));
    }

}
