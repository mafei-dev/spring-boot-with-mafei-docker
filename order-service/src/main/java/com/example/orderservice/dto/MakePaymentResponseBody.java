package com.example.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MakePaymentResponseBody {
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("transaction_status")
    private String transactionStatus;
}
