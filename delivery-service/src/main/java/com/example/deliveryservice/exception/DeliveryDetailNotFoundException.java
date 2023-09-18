package com.example.deliveryservice.exception;

import lombok.Getter;

@Getter
public class DeliveryDetailNotFoundException extends RuntimeException {
    private final String orderId;

    public DeliveryDetailNotFoundException(String orderId) {
        this.orderId = orderId;
    }

    public DeliveryDetailNotFoundException(String message, String orderId) {
        super(message);
        this.orderId = orderId;
    }
}
