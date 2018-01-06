package com.ubs.connectfour.core.impl;

import com.ubs.connectfour.core.Disc;
import com.ubs.connectfour.core.Player;

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

    @Override
    public int choose() {
        try {
            return Integer.parseInt(choices.next());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid number");
        }
    }

    @Override
    public Disc getDisc() {
        return disc;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
