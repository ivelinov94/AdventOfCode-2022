package com.wankish.day2;

public class UnknownTypeException extends RuntimeException {
    public UnknownTypeException(char arg) {
        super("Unknown type: " + arg);
    }
}
