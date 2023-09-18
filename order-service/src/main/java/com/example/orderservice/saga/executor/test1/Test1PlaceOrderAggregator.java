/*
package com.example.orderservice.saga.executor.test1;

import lombok.Getter;
import lombok.Setter;
import org.stacksaga.SagaAggregate;
import org.stacksaga.SagaSerializable;
import org.stacksaga.core.annotation.Aggregator;
import org.stacksaga.core.annotation.AggregatorVersion;

import java.io.Serializable;

@Aggregator(
        version = @AggregatorVersion(major = 1, minor = 0, patch =4),
        idPrefix = "t1a",
        name = "Test1PlaceOrderAggregator",
        sagaSerializable = SagaSerializableTest1PlaceOrderAggregator.class
)
@Getter
@Setter
public class Test1PlaceOrderAggregator extends SagaAggregate {
    public Test1PlaceOrderAggregator() {
        super(Test1PlaceOrderAggregator.class);
    }

    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private String value5;
    private String value6;
    private String value7;
    private String value8;
    private TestObject testObject = new TestObject();
}

@Getter
@Setter
class TestObject implements Serializable {
    private String value8;
}

class SagaSerializableTest1PlaceOrderAggregator implements SagaSerializable<Test1PlaceOrderAggregator> {
    @Override
    public Test1PlaceOrderAggregator[] serialize() {
        return new Test1PlaceOrderAggregator[]{};
    }
}*/
