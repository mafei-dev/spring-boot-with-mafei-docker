package com.example.orderservice.config;

import com.netflix.discovery.EurekaClient;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
public class UtilConfigWebClient {

    private final EurekaClient eurekaClient;

    /**
     * this is only for the payment-service
     *
     * @return {@link WebClient.Builder} with base url as http://payment-service
     */
    @Bean("paymentServiceWebClientBuilder")
    @LoadBalanced
    public WebClient.Builder paymentWebClientBuilder() {
        return WebClient.builder()
                .baseUrl("http://payment-service")
                .filter((request, next) -> {
                    System.out.println("[01]-UtilConfigWebClient.loadBalancedWebClientBuilder");
                    if (request.headers().containsKey("X-Instance-Id")) {
                        return next.exchange(request);
                    } else {
                        //modify the request
                        ClientRequest clientRequest = ClientRequest.from(request)
                                .header(
                                        "X-Instance-Id",
                                        this.eurekaClient.getApplicationInfoManager().getInfo().getInstanceId()
                                )
                                .build();
                        return next.exchange(clientRequest);
                    }
                });
    }

    /**
     * this is only for the delivery-service
     *
     * @return {@link WebClient.Builder} with base url as http://delivery-service
     */
    @Bean("deliveryServiceWebClientBuilder")
    @LoadBalanced
    public WebClient.Builder deliveryWebClientBuilder() {
        return WebClient.builder()
                .baseUrl("http://delivery-service")
                .filter((request, next) -> {
                    System.out.println("[01]-UtilConfigWebClient.deliveryWebClientBuilder");
                    if (request.headers().containsKey("X-Instance-Id")) {
                        return next.exchange(request);
                    } else {
                        //modify the request
                        ClientRequest clientRequest = ClientRequest.from(request)
                                .header(
                                        "X-Instance-Id",
                                        this.eurekaClient.getApplicationInfoManager().getInfo().getInstanceId()
                                )
                                .build();
                        return next.exchange(clientRequest);
                    }
                });
    }

}
