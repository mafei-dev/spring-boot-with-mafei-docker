package com.example.orderservice.service.external;

import com.example.orderservice.dto.DeliveryDetailRequestBody;
import com.example.orderservice.dto.DeliveryDetailResponse;
import com.example.orderservice.dto.DeliveryDetailResponseBody;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Component
public class DeliveryService {

    private final WebClient.Builder deliveryWebClientBuilder;

    public DeliveryService(@Qualifier("deliveryServiceWebClientBuilder") WebClient.Builder deliveryWebClientBuilder) {
        this.deliveryWebClientBuilder = deliveryWebClientBuilder;
    }

    public DeliveryDetailResponseBody dispatchOrder(DeliveryDetailRequestBody requestBody) {
        ResponseEntity<DeliveryDetailResponseBody> response = this.deliveryWebClientBuilder
                .build()
                .post()
                .uri("/dispatch")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .toEntity(DeliveryDetailResponseBody.class)
                .block();
        if (Objects.requireNonNull(response).getStatusCode().equals(HttpStatus.OK)) {
            return response.getBody();
        } else {
            throw new RuntimeException("dispatching process is failed. " + response.getStatusCode());
        }
    }

    @CircuitBreaker(name = "getDeliveryDetails", fallbackMethod = "getDeliveryDetailsFallback")
    public DeliveryDetailResponse getDeliveryDetails(String orderId) {
        System.out.println("DeliveryService.getDeliveryDetails");
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<DeliveryDetailResponse> response = this.deliveryWebClientBuilder
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/detail")
                        .queryParam("order-id", orderId)
                        .build())
                .retrieve()
                .toEntity(DeliveryDetailResponse.class)
                .block();
        if (Objects.requireNonNull(response).getStatusCode().equals(HttpStatus.OK)) {
            return response.getBody();
        } else {
            throw new RuntimeException("dispatching process is failed. " + response.getStatusCode());
        }
    }

    public DeliveryDetailResponse getDeliveryDetailsFallback(String orderId, Exception exception) {
        System.out.println("DeliveryService.getDeliveryDetailsFallback");
        System.out.println("orderId = " + orderId);
        exception.printStackTrace();
        return null;
    }
}
