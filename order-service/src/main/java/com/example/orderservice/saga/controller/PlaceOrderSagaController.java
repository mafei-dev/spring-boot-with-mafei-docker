package com.example.orderservice.saga.controller;

import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.stacksaga.SagaTemplate;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class PlaceOrderSagaController {

    private final SagaTemplate<PlaceOrderAggregator> sagaTemplate;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Object placeOrder() {
        PlaceOrderAggregator aggregator = new PlaceOrderAggregator();
        aggregator.setUsername("mafei");
        aggregator.setTotal(200.00);
        /*this.sagaTemplate.process(
                aggregator,
                CheckUserExecutor.class
        );*/
        return ImmutableMap.<String, String>builder()
                .put(
                        "transaction_id",
                        aggregator.getAggregatorTransactionId()
                )
                .put(
                        "msg",
                        "The order has been created successfully. please wait for updates."
                )
                .build();
    }
}
