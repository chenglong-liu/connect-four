package com.ubs.connectfour;

import com.ubs.connectfour.core.Disc;
import com.ubs.connectfour.core.Game;
import com.ubs.connectfour.core.Player;
import com.ubs.connectfour.core.impl.DefaultPlayer;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Replicates sample run.
 * Player Red chooses "4", "5", "3", "6" sequential.
 * Player Green chooses "4", "5", "2" sequential.
 */
class SampleRunMain {

    public static void main(String[] args) {
        System.out.println("Sample Run\n");

        Player player1 = new PredefinedPlayer("Player 1", Disc.RED, Arrays.asList("4", "5", "3", "6").iterator());
        Player player2 = new PredefinedPlayer("Player 2", Disc.GREEN, Arrays.asList("4", "5", "2").iterator());

        (new Game(player1, player2)).play();
    }

    private static class PredefinedPlayer extends DefaultPlayer {

        PredefinedPlayer(String name, Disc disc, Iterator<String> choices) {
            super(name, disc, choices);
        }

        @Override
        public int choose() {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int choice = super.choose();
            System.out.println(choice);
            return choice;
        }
    }

}
