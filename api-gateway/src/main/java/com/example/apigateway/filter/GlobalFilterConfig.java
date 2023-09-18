package com.example.apigateway.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalFilterConfig {

    @Bean
    @Order(-1)
    public GlobalFilter a() {
        return (exchange, chain) -> {
            //first pre-filter
            System.out.println("first pre-filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                //third post-filter
                System.out.println("third post-filter");
            }));
        };
    }

    @Bean
    @Order(0)
    public GlobalFilter b() {
        return (exchange, chain) -> {
            //second pre-filter
            System.out.println("second pre-filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                //second post-filter
                System.out.println("second post-filter");
            }));
        };
    }

    @Bean
    @Order(1)
    public GlobalFilter c() {
        return (exchange, chain) -> {
            //third pre-filter
            System.out.println("third pre-filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                //first post-filter
                System.out.println("first post-filter");
            }));
        };
    }

}
