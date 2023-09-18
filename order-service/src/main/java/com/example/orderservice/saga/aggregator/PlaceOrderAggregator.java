package com.example.orderservice.saga.aggregator;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.stacksaga.Aggregator;
import org.stacksaga.core.annotation.SagaAggregator;
import org.stacksaga.core.annotation.SagaAggregatorVersion;

@SagaAggregator(
        version = @SagaAggregatorVersion(major = 1, minor = 1, patch = 0),
        idPrefix = "po",
        name = "PlaceOrderAggregator",
        sagaSerializable = PlaceOrderAggregatorSample.class
)
@Getter
@Setter
public class PlaceOrderAggregator extends Aggregator {

    public PlaceOrderAggregator() {
        super(PlaceOrderAggregator.class);
    }

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("total")
    private Double total;

    @JsonProperty("is_active")
    private Integer isActive;
}
