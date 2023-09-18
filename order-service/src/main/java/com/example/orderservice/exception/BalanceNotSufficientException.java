package com.example.orderservice.exception;

public class BalanceNotSufficientException extends RuntimeException {
    public BalanceNotSufficientException() {
    }

    public BalanceNotSufficientException(String message) {
        super(message);
    }
}
