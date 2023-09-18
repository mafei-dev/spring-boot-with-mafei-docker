package com.example.paymentservice.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MakePaymentRequestBody(
        @NotNull(message = "username cannot be null.")
        String username,
        @NotNull(message = "order_id cannot be null.")
        @JsonProperty("order_id") String orderId,
        @NotNull(message = "order_amount cannot be null.")
        @Min(value = 0L, message = "order_amount should be greater than zero.")
        @JsonProperty("order_amount") Double orderAmount
) {
}
