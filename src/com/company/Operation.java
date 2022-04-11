package com.company;

enum Operation {
    DIVIDE("/"),
    MULTIPLY("*"),
    ADD("+"),
    SUBTRACT("-");
    final String s;

    Operation(String s) {
        this.s = s;
    }

    public String value() {
        return s;
    }
}
