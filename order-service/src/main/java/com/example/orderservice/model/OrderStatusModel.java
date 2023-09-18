package com.example.orderservice.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatusModel {
    private String orderStatusId;
    /**
     * which is the order. FK {@link OrderModel}
     */
    private String orderId;
    private LocalDateTime updatedDatetime;
    private OrderStatus orderStatus;

    public enum OrderStatus {
        INITIALIZED,
        PROCESSING,
        PAYMENT_PROCESSING,
        PAYMENT_FAILED,
        DELIVERED
    }
}
