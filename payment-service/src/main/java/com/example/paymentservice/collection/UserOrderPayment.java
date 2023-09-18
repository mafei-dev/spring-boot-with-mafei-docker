package com.example.paymentservice.collection;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @param _id      pk
 * @param amount   order-amount
 * @param username username
 * @param dateTime added datetime
 * @param status   transaction status
 * @param orderId  unique id of the order
 */
@Document(value = "user_order_payment")
@Builder
public record UserOrderPayment(
        @Id String _id,
        Double amount,
        String username,
        LocalDateTime dateTime,
        Status status,
        String orderId,
        @Version
        List<History> paymentHistory
) {

    public enum Status {
        SUCCESS,
        FAILED,
        REFUNDED
    }

    @Builder
    public record History(
            LocalDateTime dateTime,
            Status status
    ) {

    }
}
