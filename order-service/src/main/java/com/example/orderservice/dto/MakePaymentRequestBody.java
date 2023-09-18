package com.example.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MakePaymentRequestBody {
    private String username;
    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("order_amount")
    private Double orderAmount;
}
