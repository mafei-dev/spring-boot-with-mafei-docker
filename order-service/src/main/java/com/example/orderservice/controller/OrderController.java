package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderViewDto;
import com.example.orderservice.dto.PlaceOrderDto;
import com.example.orderservice.service.OrderService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderDto placeOrderDto) {
        String orderId = orderService.placeOrder(placeOrderDto.getUsername());
        return ResponseEntity.ok(
                Collections.singletonMap("order_id", orderId)
        );
    }

    @GetMapping
    @Bulkhead(name = "getOrderDetail", type = Bulkhead.Type.THREADPOOL, fallbackMethod = "getOrderDetailFallback")
    public CompletableFuture<ResponseEntity<Object>> getOrderDetail(@RequestParam("order_id") String orderId) {
        System.out.println("Thread:" + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(
                ResponseEntity.ok(
                        this.orderService.getOrderDetail(orderId)
                )
        );
    }


    public CompletableFuture<ResponseEntity<Object>> getOrderDetailFallback(String orderId, Exception exception) {
        System.out.println("Thread:" + Thread.currentThread().getName());
        exception.printStackTrace();
        return CompletableFuture
                .completedFuture(
                        ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                                .body(Collections.singletonMap("msg", "not permitted:" + Thread.currentThread().getName()))
                );

    }
}
