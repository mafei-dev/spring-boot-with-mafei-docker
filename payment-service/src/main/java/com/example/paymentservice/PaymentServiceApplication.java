package com.example.paymentservice;

import com.example.paymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Hooks;

@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
@AllArgsConstructor
public class PaymentServiceApplication {

    private final PaymentService paymentService;

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
        Hooks.enableAutomaticContextPropagation();
    }

    @Bean
    public CommandLineRunner initDB() {
        return args -> {
            //db-init
            this.paymentService.initWallet(
                    "mafei",
                    100.00
            ).subscribe();

            this.paymentService.initWallet(
                    "jaki",
                    500.00
            ).subscribe();
        };
    }
}
