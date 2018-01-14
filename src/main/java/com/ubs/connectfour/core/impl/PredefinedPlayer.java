package com.ubs.connectfour.core.impl;

import com.ubs.connectfour.core.Disc;

import java.util.Iterator;

public class PredefinedPlayer extends DefaultPlayer {

    public PredefinedPlayer(String name, Disc disc, Iterator<String> choices) {
        super(name, disc, choices);
    }

    @Override
    public String nextChoice() {
        try {
            // Sleep a while for thinking
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String choice = super.nextChoice();
        System.out.println(choice);
        return choice;
    }
}
