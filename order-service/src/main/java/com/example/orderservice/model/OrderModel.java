package com.example.orderservice.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel {
    private String orderId;
    private String username;
    private String deliveryId;
    private String transactionId;
    private LocalDateTime invoiceDatetime;
}
