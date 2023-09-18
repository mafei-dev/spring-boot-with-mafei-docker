package org.example.exception;

public class PointNotSufficientException extends RuntimeException {
    public PointNotSufficientException() {
    }

    public PointNotSufficientException(String message) {
        super(message);
    }
}
