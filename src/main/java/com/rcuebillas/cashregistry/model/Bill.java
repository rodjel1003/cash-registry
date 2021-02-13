package com.rcuebillas.cashregistry.model;

public enum Bill {
    TWENTY(20),
    TEN(10),
    FIVE(5),
    TWO(2),
    ONE(1);

    private final int value;

    Bill(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static int getIndexByValue(int value) {
        switch (value) {
            case 20:
                return 0;
            case 10:
                return 1;
            case 5:
                return 2;
            case 2:
                return 3;
            case 1:
                return 4;
            default:
                return -1;
        }
    }
}
