package com.ubs.connectfour;

import com.ubs.connectfour.core.Disc;
import com.ubs.connectfour.core.Game;
import com.ubs.connectfour.core.Player;
import com.ubs.connectfour.core.impl.DefaultPlayer;

import java.util.Scanner;

class ConnectFourMain {

    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            // 2 players read choices from console.
            Player player1 = new DefaultPlayer("Player 1", Disc.RED, scanner);
            Player player2 = new DefaultPlayer("Player 2", Disc.GREEN, scanner);

            (new Game(player1, player2)).play();
        } finally {
            if (scanner != null)
                scanner.close();
        }
    }
}
