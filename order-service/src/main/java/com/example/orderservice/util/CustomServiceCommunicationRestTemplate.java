package com.example.orderservice.util;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.stacksaga.cloud.config.SagaServiceCommunicationRestTemplateProvider;

@Component
@AllArgsConstructor
public class CustomServiceCommunicationRestTemplate /*implements SagaServiceCommunicationRestTemplateProvider*/ {
/*
    private final RestTemplateBuilder restTemplateBuilder;
    @Override
    public RestTemplate getRestTemplate() {
        //provide your custom configurations
        RestTemplate restTemplate = this.restTemplateBuilder
                .rootUri()
                .build();
        return restTemplate;
    }*/
}
