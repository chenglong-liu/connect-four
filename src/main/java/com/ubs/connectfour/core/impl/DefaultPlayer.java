package com.ubs.connectfour.core.impl;

import com.ubs.connectfour.core.Board;
import com.ubs.connectfour.core.Disc;
import com.ubs.connectfour.core.Player;
import com.ubs.connectfour.core.Referee;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;

public class DefaultPlayer implements Player {

    private final Disc disc;
    private final String description;
    private final Iterator<String> choices;

    public DefaultPlayer(String name, Disc disc, Iterator<String> choices) {
        this.disc = disc;
        this.description = String.format("%s [%s]", name, disc);
        this.choices = choices;
    }

    String nextChoice() {
        return StringUtils.trim(choices.next());
    }


    @Override
    public boolean choose(Board board, Referee referee) {
        // Try until a successful move(one drop or multiple undo)
        for (; ; ) {
            System.out.print(String.format("%s - %s: ", this.description, board.showTips()));
            String choice = nextChoice();
            try {
                if (UNDO.equals(choice)) {
                    board.undo();
                    return false;
                } else {
                    int column;
                    try {
                        column = Integer.parseInt(choice) - 1;
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("invalid number");
                    }
                    int row = board.drop(this.disc, column);
                    // Referee checks if current player win the game
                    return referee.judge(board, row, column);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(String.format("%s - %s!", this.description, e.getMessage()));
            }
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
