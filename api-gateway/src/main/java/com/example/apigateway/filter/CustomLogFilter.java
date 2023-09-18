package com.example.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

@Component
public class CustomLogFilter extends AbstractGatewayFilterFactory<CustomLogFilter.Config> {

    public CustomLogFilter() {
        super(CustomLogFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //logic:PostGateway Filter (IN) | PreGateway Filter (OUT)

        return (exchange, chain) -> {
            //PreGateway Filter.
            //insert the time of the request comes to the gateway.
            System.out.println("CustomLogFilter pre-filter");
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
            ServerHttpRequest request = builder.header(
                    "X-Gateway-In",
                    ZonedDateTime.now().toString()
            ).build();
            ServerWebExchange newWebExchange = exchange.mutate().request(request).build();
            return chain
                    .filter(newWebExchange)
                    .then(Mono.fromRunnable(() -> {
                        //PostGateway Filter.
                        //insert the time of the request goes from the gateway.
                        System.out.println("CustomLogFilter post-filter");
                        ServerHttpResponse response = newWebExchange.getResponse();
                        response.getHeaders().add("X-Gateway-Out",ZonedDateTime.now().toString());
                        response.getHeaders().add("X-Gateway-Message",config.getMessage());
                    }));
        };
    }

    public static class Config {
        //args
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
