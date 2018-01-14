package com.ubs.connectfour.core;

public enum Disc {
    NA(0), RED(1), GREEN(2);
    public final int value;

    Disc(int i) {
        this.value = i;
    }

    public static Disc valueOf(int value) {
        switch (value) {
            case 1:
                return RED;
            case 2:
                return GREEN;
            default:
                return NA;
        }
    }
}
