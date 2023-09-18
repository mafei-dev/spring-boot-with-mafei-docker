package com.example.deliveryservice.config;

import com.example.deliveryservice.dto.req.DeliveryDetailRequestBody;
import com.example.deliveryservice.dto.res.DeliveryDetailResponseBody;
import com.example.deliveryservice.service.DeliveryDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
@AllArgsConstructor
public class Routing {

    private final DeliveryDetailService deliveryDetailService;

    @Bean
    public RouterFunction<ServerResponse> doRoute() {
        return RouterFunctions.route()
                .POST(
                        "/dispatch",
                        RequestPredicates.accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML),
                        this::handlerDispatch
                )
                .GET(
                        "/detail",
                        RequestPredicates.accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML),
                        this::handlerGetOrderDetail
                )

                .build();
    }

    private Mono<ServerResponse> handlerDispatch(ServerRequest request) {
        //pass the data in to the service.
        return request
                .body(BodyExtractors.toMono(DeliveryDetailRequestBody.class))
                .doOnNext(deliveryDetailRequestBody -> log.info("DeliveryDetailRequestBody : {} ", deliveryDetailRequestBody))
                .flatMap(this.deliveryDetailService::addNewDetail)
                .flatMap(deliveryDetailId -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(
                                new DeliveryDetailResponseBody(deliveryDetailId)
                        ))
                );
    }

    private Mono<ServerResponse> handlerGetOrderDetail(ServerRequest request) {
        return request.queryParam("order-id")
                .map(this.deliveryDetailService::getOrderDetail)
                .map(deliveryDetailResponseMono -> deliveryDetailResponseMono
                        .flatMap(deliveryDetailResponse -> ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(deliveryDetailResponse))
                        )
                )
                .orElseThrow(() -> new RuntimeException("order-id is required."));
    }

}
