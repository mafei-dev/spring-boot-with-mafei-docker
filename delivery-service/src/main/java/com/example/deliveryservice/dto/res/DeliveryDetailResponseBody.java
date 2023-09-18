package com.example.deliveryservice.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeliveryDetailResponseBody(@JsonProperty("delivery_detail_id") Long deliveryDetailId) {
}
