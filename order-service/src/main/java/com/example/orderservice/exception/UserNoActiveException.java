package com.example.orderservice.exception;

import lombok.Getter;

@Getter
public class UserNoActiveException extends RuntimeException {
    private final String username;

    public UserNoActiveException(String username) {
        this.username = username;
    }

    public UserNoActiveException(String message, String username) {
        super(message);
        this.username = username;
    }
}
