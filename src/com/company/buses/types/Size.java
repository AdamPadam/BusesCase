package com.company.buses.types;

public enum Size {
    SMALL(20), MIDDLE(40), LARGE(60); // Три вида автобуса по вместительности

    private int value; // Вместительность автобуса

    Size(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
