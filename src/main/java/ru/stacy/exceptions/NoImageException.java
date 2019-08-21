package ru.stacy.exceptions;

public class NoImageException extends Exception {
    private String message;

    public NoImageException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
