package com;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BulkheadTester {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                ResponseEntity<Object> response = new RestTemplate().getForEntity(
                        "http://localhost:8080/order-service?order_id=c7129c5d-ea71-4dff-9956-e84a889d0781", Object.class
                );
                System.out.println("response:" + response);
            }).start();
        }
        Thread.sleep(50_000);
    }
}
