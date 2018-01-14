package com.ubs.connectfour;

import com.ubs.connectfour.core.Disc;
import com.ubs.connectfour.core.Game;
import com.ubs.connectfour.core.Player;
import com.ubs.connectfour.core.impl.PredefinedPlayer;

import java.util.Arrays;
import java.util.List;


class SampleRunWithUndoMain {

    public static void main(String[] args) {
        System.out.println("Sample Run With Undo\n");

        List<String> choices1 = Arrays.asList("2", "3", "3", "4", "7", "1", "1", "u", "u");
        List<String> choices2 = Arrays.asList("2", "4", "3", "4", "4", "7", "1");

        Player player1 = new PredefinedPlayer("Player 1", Disc.RED, choices1.iterator());
        Player player2 = new PredefinedPlayer("Player 2", Disc.GREEN, choices2.iterator());

        (new Game(player1, player2)).play();
    }
}
