package org.example.exception;

public class UserPointUpdateFailedException extends RuntimeException {
    public UserPointUpdateFailedException() {
    }

    public UserPointUpdateFailedException(String message) {
        super(message);
    }
}
