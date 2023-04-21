package org.nsnow.itools.calculator;

public class Pair {

    private String value;
    private int position;

    public Pair(String value, int position) {
        this.value = value;
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }
}
