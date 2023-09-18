package com.example.deliveryservice.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(name = "delivery_detail")
public class DeliveryDetailEntity {
    @Id
    private Long deliveryDetailId;
    private String username;
    private String address;
    private String tel;
    private String email;
    private String orderId;
    private String transactionId;
}
