package com.example.stockmanagementservice.exception;

import lombok.Getter;

@Getter
public class NoCartItemException extends RuntimeException {
    private final String username;

    public NoCartItemException(String username) {
        this.username = username;
    }

    public NoCartItemException(String message, String username) {
        super(message);
        this.username = username;
    }
}
