package com.blackbear.ray;

public class Counter {
    private int value = 0;

    public int increment() {
        value += 1;
        return this.value;
    }

    public int getValue() {
        return this.value;
    }

}
