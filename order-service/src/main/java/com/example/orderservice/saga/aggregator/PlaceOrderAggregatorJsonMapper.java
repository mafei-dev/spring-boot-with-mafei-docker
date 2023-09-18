package com.example.orderservice.saga.aggregator;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;
import org.stacksaga.core.SagaAggregatorMapper;
import org.stacksaga.core.SagaAggregatorMapperProvider;

@Component
public class PlaceOrderAggregatorJsonMapper implements SagaAggregatorMapperProvider {

    private final ObjectMapper objectMapper;

    public PlaceOrderAggregatorJsonMapper() {
        this.objectMapper = new ObjectMapper();
        //your custom object mapper configurations
    }

    @Override
    public SagaAggregatorMapper getSagaAggregatorMapper() {
        this.objectMapper.setConfig(
                this.objectMapper.getSerializationConfig()
                        .with(SerializationFeature.INDENT_OUTPUT)
        );
        /*this.objectMapper.setConfig(
                this.objectMapper.getDeserializationConfig()
                        .without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        );
*/        return SagaAggregatorMapper.Builder.build(
                this.objectMapper
        );
    }
}
