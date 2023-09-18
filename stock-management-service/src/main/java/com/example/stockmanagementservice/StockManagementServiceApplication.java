package com.example.stockmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StockManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockManagementServiceApplication.class, args);
    }

}
