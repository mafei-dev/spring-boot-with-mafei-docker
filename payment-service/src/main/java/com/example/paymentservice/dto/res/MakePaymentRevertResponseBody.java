package com.example.paymentservice.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record MakePaymentRevertResponseBody(
        @JsonProperty("transaction_id")
        String transactionId,
        @JsonProperty("transaction_status")
        String transactionStatus
) {

}
