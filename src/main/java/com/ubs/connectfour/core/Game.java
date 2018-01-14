package com.ubs.connectfour.core;

import com.ubs.connectfour.core.impl.SimpleBoard;
import com.ubs.connectfour.core.impl.SimpleReferee;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a connect four game.
 */
public class Game {

    private final Board board;
    private final Referee referee;
    private final Map<Disc, Player> players = new LinkedHashMap<>();

    public Game(Player... players) {
        this(new SimpleBoard(), new SimpleReferee(), Arrays.asList(players));
    }

    private Game(Board board, Referee referee, List<Player> players) {
        this.board = board;
        this.referee = referee;
        for (Player player : players)
            this.players.put(player.getDisc(), player);
    }

    public void play() {
        board.display();
        int move = 0;
        List<Player> playerList = new ArrayList<>(players.values());
        while (board.isAvailable()) {
            // Players take alternate turns
            move = move % playerList.size();
            Player player = playerList.get(move);

            Disc winningDisc = player.choose(board, referee);

            if (Disc.NA != winningDisc) {
                // With a unadvisable undo, the other could win the game.
                // So need to find winner based on the winning disc
                System.out.println(String.format("%s - wins!", this.players.get(winningDisc).getDescription()));
                return;
            }
            move++;
        }
        // Board is full, the game finishes, and it is considered a draw
        List<String> descriptions = playerList.stream().map(Player::getDescription).collect(Collectors.toList());
        System.out.println(String.format("%s - draw!", String.join(", ", descriptions)));
    }

}
