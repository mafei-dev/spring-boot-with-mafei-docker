package com.example.stockmanagementservice.exception;

import lombok.Getter;

@Getter
public class StockNotSufficientException extends RuntimeException {
    private final String itemCode;

    public StockNotSufficientException(String itemCode) {
        this.itemCode = itemCode;
    }

    public StockNotSufficientException(String message, String itemCode) {
        super(message);
        this.itemCode = itemCode;
    }
}
