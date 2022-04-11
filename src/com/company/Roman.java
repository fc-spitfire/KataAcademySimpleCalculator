package com.company;

enum Roman {
    ONE("I", 1),
    TWO("II", 2),
    THREE("III", 3),
    FOUR("IV", 4),
    FIVE("V", 5),
    SIX("VI", 6),
    SEVEN("VII", 7),
    EIGHT("VII", 8),
    NINE("IX", 9),
    TEN("X", 10);
    private final int value;
    private final String romanNumeral;

    Roman(String romanNumeral, int value) {
        this.value = value;
        this.romanNumeral = romanNumeral;
    }

    public int value() {
        return value;
    }

    public String romanNumeral() {
        return romanNumeral;
    }
}
