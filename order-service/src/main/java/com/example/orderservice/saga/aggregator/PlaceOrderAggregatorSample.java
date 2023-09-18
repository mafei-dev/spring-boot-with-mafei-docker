package com.example.orderservice.saga.aggregator;

import org.stacksaga.SagaSerializable;

import java.util.UUID;

public class PlaceOrderAggregatorSample extends SagaSerializable<PlaceOrderAggregator> {
    public PlaceOrderAggregatorSample() {
        //sample-1
        PlaceOrderAggregator aggregator1 = new PlaceOrderAggregator();
        aggregator1.setOrderId(UUID.randomUUID().toString());
        this.put("1", aggregator1);

        //sample-2
        PlaceOrderAggregator aggregator2 = new PlaceOrderAggregator();
        aggregator2.setOrderId(UUID.randomUUID().toString());
        aggregator2.setUsername("mafei");
        this.put("2", aggregator2);
    }
}