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
        for (int move = 0; board.isAvailable(); move++) {
            // Players take alternate turns
            Player player = players.get(move % players.size());

            boolean isWinner = player.choose(board, referee);

            board.display();
            if (isWinner) {
                System.out.println(String.format("%s - wins!", player.getDescription()));
                return;
            }
        }
        // Board is full, the game finishes, and it is considered a draw
        List<String> descriptions = players.stream().map(Player::getDescription).collect(Collectors.toList());
        System.out.println(String.format("%s - draw!", String.join(", ", descriptions)));
    }

}
