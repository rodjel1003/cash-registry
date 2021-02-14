package com.rcuebillas.cashregistry.model;

public enum Command {
    SHOW, PUT, TAKE, CHANGE, QUIT, UNKNOWN;

    public static Command getByName(String name) {
        try {
            return Command.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }

}
