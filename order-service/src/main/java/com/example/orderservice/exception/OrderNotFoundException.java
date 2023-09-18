package com.example.orderservice.exception;

import lombok.Getter;

@Getter
public class OrderNotFoundException extends RuntimeException {
    private final String orderId;

    public OrderNotFoundException(String orderId) {
        this.orderId = orderId;
    }

    public OrderNotFoundException(String message, String orderId) {
        super(message);
        this.orderId = orderId;
    }
}
