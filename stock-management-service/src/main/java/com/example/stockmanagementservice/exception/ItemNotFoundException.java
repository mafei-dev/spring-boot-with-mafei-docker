package com.example.stockmanagementservice.exception;

import lombok.Getter;

@Getter
public class ItemNotFoundException extends RuntimeException {
    private final String itemCode;

    public ItemNotFoundException(String itemCode) {
        this.itemCode = itemCode;
    }

    public ItemNotFoundException(String message, String itemCode) {
        super(message);
        this.itemCode = itemCode;
    }
}
