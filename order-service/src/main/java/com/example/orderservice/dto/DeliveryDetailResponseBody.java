package com.example.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryDetailResponseBody {
    @JsonProperty("delivery_detail_id")
    private Long deliveryDetailId;
}
