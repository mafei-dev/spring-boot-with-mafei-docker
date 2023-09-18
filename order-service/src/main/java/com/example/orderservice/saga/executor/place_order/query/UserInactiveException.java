package com.example.orderservice.saga.executor.place_order.query;

import org.stacksaga.core.annotation.SagaException;

@SagaException(name = "UserInactiveException")
public class UserInactiveException extends RuntimeException {
    public UserInactiveException() {
    }

    public UserInactiveException(String message) {
        super(message);
    }

    public UserInactiveException(String message, Throwable cause) {
        super(message, cause);
    }
}