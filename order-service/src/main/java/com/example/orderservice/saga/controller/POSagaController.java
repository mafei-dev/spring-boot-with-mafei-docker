/*
package com.example.orderservice.saga.controller;

import com.example.orderservice.saga.aggregator.POAggregator;
import com.example.orderservice.saga.executor.po.command.POTestExecutor1;
import com.example.orderservice.saga.executor.test1.Test1PlaceOrderAggregator;
import com.example.orderservice.saga.executor.test1.Test1QExecutor1;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stacksaga.SagaTemplate;
import org.stacksaga.exception.TransactionInitializationFailedException;

@RestController
@RequestMapping("/saga/po")
@AllArgsConstructor
public class POSagaController {

    private final SagaTemplate<POAggregator> sagaTemplate;
    private final SagaTemplate<Test1PlaceOrderAggregator> sagaTemplate01;

    @GetMapping("/0")
    public Object test() {

        POAggregator aggregator = new POAggregator();
        aggregator.setUsername("mafei");
        aggregator.setTotal(200.00);

        try {
            this.sagaTemplate.process(
                    aggregator,
                    POTestExecutor1.class
            );
        } catch (TransactionInitializationFailedException e) {
            System.out.println("e : " + e.getMessage());
            System.out.println("e:string : " + e);
        }
        return aggregator.getAggregateTransactionId();
    }

    @GetMapping("/01")
    public Object test01() {
        Test1PlaceOrderAggregator aggregator = new Test1PlaceOrderAggregator();
        aggregator.setValue1("1");
        this.sagaTemplate01.process(aggregator, Test1QExecutor1.class);
        return aggregator.getAggregateTransactionId();
    }
}
*/
