package com.example.orderservice.service.external;

import com.example.orderservice.dto.MakePaymentRequestBody;
import com.example.orderservice.dto.MakePaymentResponseBody;
import com.example.orderservice.dto.MakePaymentRevertRequestBody;
import com.example.orderservice.dto.MakePaymentRevertResponseBody;
import com.example.orderservice.exception.BalanceNotSufficientException;
import com.example.orderservice.exception.HttpStatusException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class PaymentService {

    private final WebClient.Builder paymentWebClientBuilder;

    public PaymentService(@Qualifier("paymentServiceWebClientBuilder") WebClient.Builder paymentWebClientBuilder) {
        this.paymentWebClientBuilder = paymentWebClientBuilder
                .filter((request, next) -> {
                    System.out.println("[02]-PaymentService.PaymentService");
                    return next.exchange(request);
                });
    }

    /**
     * exceptions [BalanceNotSufficientException|RuntimeException]
     *
     * @param username
     * @param amount
     */
    public void checkTheBalance(String username, Double amount) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("amount", amount);
        this.paymentWebClientBuilder
                .build()
                .get()
                .uri("/check-balance/{username}/{amount}", data)
                .retrieve()
                .toBodilessEntity()
                .map(voidResponseEntity -> {
                    if (voidResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
                        return true;
                    } else if (voidResponseEntity.getStatusCode().equals(HttpStatus.NOT_ACCEPTABLE)) {
                        throw new BalanceNotSufficientException();
                    } else {
                        throw new RuntimeException("status code : " + voidResponseEntity.getStatusCode());
                    }
                })
                .block();
    }

    public void doSomething() {
        ResponseEntity<Map> response = this.paymentWebClientBuilder
                .build()
                .get()
                .uri("/actuator/health")
                .retrieve()
                .toEntity(Map.class)
                .block();
        System.out.println("response = " + response);
    }

    public MakePaymentResponseBody makePayment(MakePaymentRequestBody requestBody) {
        ResponseEntity<MakePaymentResponseBody> paymentResponseBodyResponseEntity = this.paymentWebClientBuilder
                .build()
                .post()
                .uri("/make-payment")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .toEntity(MakePaymentResponseBody.class)
                .block();
        if (Objects.requireNonNull(paymentResponseBodyResponseEntity).getStatusCode().equals(HttpStatus.OK)) {
            return paymentResponseBodyResponseEntity.getBody();
        } else {
            throw new RuntimeException("make payment failed. "
                    +
                    Objects.requireNonNull(paymentResponseBodyResponseEntity).getStatusCode());
        }

    }

    public void makePaymentRevert(MakePaymentRevertRequestBody requestBody) throws HttpStatusException {
        ResponseEntity<MakePaymentRevertResponseBody> paymentResponseBodyResponseEntity = this.paymentWebClientBuilder
                .build()
                .put()
                .uri("/make-payment")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .toEntity(MakePaymentRevertResponseBody.class)
                .block();
        if (!Objects.requireNonNull(paymentResponseBodyResponseEntity).getStatusCode().equals(HttpStatus.OK)) {
            throw new HttpStatusException(paymentResponseBodyResponseEntity.getStatusCode());
        }
    }
}
