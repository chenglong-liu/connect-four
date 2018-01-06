package com.ubs.connectfour.core;

import java.util.Iterator;

public class Player {
    private final Disc disc;
    private final String info;
    private final Iterator<String> choices;

    public Player(String name, Disc disc, Iterator<String> choices) {
        this.disc = disc;
        this.info = String.format("%s [%s]", name, disc);
        this.choices = choices;
    }

    /**
     * Chooses column for next drop.
     *
     * @throws IllegalArgumentException if input is not an integer.
     */
    public int choose() {
        try {
            return Integer.parseInt(choices.next());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid number");
        }
    }

    Disc getDisc() {
        return disc;
    }

    @Override
    public String toString() {
        return this.info;
    }


}
