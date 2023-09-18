package com.example.apigateway.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotNull;
import java.util.function.Predicate;

@Component
public class Counter extends AbstractRoutePredicateFactory<Counter.Config> {

    public Counter() {
        super(Counter.Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        //logic
        return serverWebExchange -> {
            if (serverWebExchange.getRequest().getHeaders().containsKey("X-Count")) {
                int count = Integer.parseInt(serverWebExchange.getRequest().getHeaders().get("X-Count").get(0));
                return config.maxValue>count;
            } else {
                return false;
            }
        };
    }


    @Validated
    public static class Config {
        //args
        @NotNull(message = "max-value is required.")
        private Integer maxValue;

        public Integer getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(Integer maxValue) {
            this.maxValue = maxValue;
        }
    }
}
