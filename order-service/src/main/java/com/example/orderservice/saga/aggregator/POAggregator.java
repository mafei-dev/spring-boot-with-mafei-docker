/*
package com.example.orderservice.saga.aggregator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.stacksaga.SagaAggregate;
import org.stacksaga.SagaSerializable;
import org.stacksaga.core.SagaAggregatorMapperProvider;
import org.stacksaga.core.SagaAggregatorMapper;
import org.stacksaga.core.annotation.Aggregator;
import org.stacksaga.core.annotation.AggregatorVersion;

@Component
class POMapper implements SagaAggregatorMapperProvider {
    private final SagaAggregatorMapper sagaAggregatorMapper = SagaAggregatorMapper.Builder.build(new ObjectMapper());
    @Override
    public SagaAggregatorMapper getSagaAggregatorMapper() {
        return sagaAggregatorMapper;
    }
}

@Aggregator(
        mapper = POMapper.class,
        version =
        @AggregatorVersion(
                major = 1,
                minor = 0,
                patch = 1
        ),
        idPrefix = "po",
        name = "POAggregator",
        sagaSerializable = POAggregatorSS.class
)
@Getter
@Setter
public class POAggregator extends SagaAggregate {

    public POAggregator() {
        super(POAggregator.class);
    }

    private String orderId;
    private String username;
    private int isActive;
    private Long userId;
    private String email;
    private String tel;
    private String address;
    //
    private Double total;
    private String cartId;

    //
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("transaction_status")
    private String transactionStatus;
    //
    private String deliveryId;
}

class POAggregatorSS implements SagaSerializable<POAggregator> {

    @Override
    public POAggregator[] serialize() {
        return new POAggregator[]{
                new POAggregator(),
                new POAggregator()
        };
    }
}*/
