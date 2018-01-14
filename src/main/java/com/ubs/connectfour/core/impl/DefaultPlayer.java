package com.ubs.connectfour.core.impl;

import com.ubs.connectfour.core.Board;
import com.ubs.connectfour.core.Disc;
import com.ubs.connectfour.core.Player;
import com.ubs.connectfour.core.Referee;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.LinkedList;

public class DefaultPlayer implements Player {

    private final Disc disc;
    private final String description;
    private final Iterator<String> choices;
    private final LinkedList<Integer> history = new LinkedList<>();

    public DefaultPlayer(String name, Disc disc, Iterator<String> choices) {
        this.disc = disc;
        this.description = String.format("%s [%s]", name, disc);
        this.choices = choices;
    }

    private String help(Board board) {
        if (this.history.isEmpty()) {
            return String.format("%s - %s: ", this.description, board.showTips());
        } else {
            return String.format("%s - %s or u to undo: ", this.description, board.showTips());
        }
    }

    String nextChoice() {
        return StringUtils.trim(choices.next());
    }


    @Override
    public Disc choose(Board board, Referee referee) {
        // Try until a successful move(one drop or multiple undo)
        for (; ; ) {
            System.out.print(this.help(board));
            String choice = nextChoice();
            try {
                if (UNDO.equals(choice)) {
                    Disc winner = this.undo(board, referee);
                    if (Disc.NA != winner || this.history.isEmpty()) {
                        // Already has a winner or no more discs to be undone, finishes this round.
                        return winner;
                    }
                    String message = this.description + " - type u to continue to undo, type others to complete your turn: ";
                    // Multiple moves can be undone.
                    System.out.print(message);
                    while (UNDO.equals(nextChoice())) {
                        winner = this.undo(board, referee);
                        if (Disc.NA != winner || this.history.isEmpty()) {
                            // Already has a winner or no more discs to be undone, finishes this round.
                            return winner;
                        }
                        System.out.print(message);
                    }
                    return winner;
                } else {
                    // Play can only make one drop in each turn.
                    return this.drop(board, referee, choice);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(String.format("%s - %s!", this.description, e.getMessage()));
            }
        }
    }

    /**
     * Undo last move.
     *
     * @return winning disc or NA if no winner yet
     * @throws IllegalArgumentException if player has no moves to be undone.
     */
    private Disc undo(Board board, Referee referee) {
        if (this.history.isEmpty()) {
            throw new IllegalArgumentException("no " + this.disc + " disc on board, you can't undo");
        }
        int column = this.history.removeLast();
        int row = board.undo(this.disc, column);
        board.display();
        // After a successful undo, discs above the removed one will shift down one row.
        // Needs to check if any of impacted discs win the game from bottom to top.
        for (int i = row; i > 0; i--) {
            int value = board.getMatrix()[i][column];
            if (value != Disc.NA.value && referee.judge(board, i, column))
                return Disc.valueOf(value);
        }
        return Disc.NA;
    }

    /**
     * Drops a disc to board.
     *
     * @return winning disc or NA if no winner yet
     * @throws IllegalArgumentException if input is not a number or an integer.
     */
    private Disc drop(Board board, Referee referee, String choice) {
        int column;
        try {
            column = Integer.parseInt(choice) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid number");
        }
        int row = board.drop(this.disc, column);
        board.display();

        // Add column to history only after a successfully drop.
        this.history.add(column);
        return referee.judge(board, row, column) ? this.disc : Disc.NA;
    }

    @Override
    public Disc getDisc() {
        return this.disc;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
