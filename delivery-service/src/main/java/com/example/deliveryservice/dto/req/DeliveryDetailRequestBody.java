package com.example.deliveryservice.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.validation.constraints.NotNull;

@Builder
public record DeliveryDetailRequestBody(
        @NotNull(message = "username cannot be null.")
        String username,
        @NotNull(message = "address cannot be null.")
        String address,
        @NotNull(message = "tel cannot be null.")
        String tel,
        String email,
        @JsonProperty("order_id")
        @NotNull(message = "order_id cannot be null.")
        String orderId,
        @JsonProperty("transaction_id")
        @NotNull(message = "transaction_id cannot be null.")
        String transactionId
) {
}
