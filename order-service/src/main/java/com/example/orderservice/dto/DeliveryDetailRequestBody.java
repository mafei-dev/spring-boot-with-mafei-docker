package com.example.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryDetailRequestBody {
    private String username;
    private String address;
    private String tel;
    private String email;
    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("transaction_id")
    private String transactionId;
}
