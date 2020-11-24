package ru.org.sbrf.exception;

public class ObjectException extends Exception{
    private String message;

    public ObjectException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

