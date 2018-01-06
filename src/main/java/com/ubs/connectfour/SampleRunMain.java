package com.ubs.connectfour;

import com.ubs.connectfour.core.Disc;
import com.ubs.connectfour.core.Game;
import com.ubs.connectfour.core.Player;

import java.util.Arrays;
import java.util.Iterator;

class SampleRunMain {

    public static void main(String[] args) {
        System.out.println("Sample Run\n");

        // Sample players have predefined choices
        Player player1 = new SamplePlayer("Player 1", Disc.RED, Arrays.asList("4", "5", "3", "6").iterator());
        Player player2 = new SamplePlayer("Player 2", Disc.GREEN, Arrays.asList("4", "5", "2").iterator());

        (new Game(player1, player2)).play();
    }

    private static class SamplePlayer extends Player {
        private SamplePlayer(String name, Disc disc, Iterator<String> choices) {
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
