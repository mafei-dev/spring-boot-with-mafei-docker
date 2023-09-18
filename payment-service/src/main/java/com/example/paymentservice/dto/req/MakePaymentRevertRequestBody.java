package com.example.paymentservice.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MakePaymentRevertRequestBody(
        @JsonProperty("transaction_id")
        @NotNull(message = "username cannot be null.")
        String transactionId
) {
}
