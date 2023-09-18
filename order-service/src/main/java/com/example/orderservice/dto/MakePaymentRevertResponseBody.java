package com.example.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MakePaymentRevertResponseBody {
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("transaction_status")
    private String transactionStatus;
}
