package com.example.paymentservice.controller;

import com.example.paymentservice.dto.req.MakePaymentRequestBody;
import com.example.paymentservice.dto.res.MakePaymentResponseBody;
import com.example.paymentservice.exception.BalanceNotSufficientException;
import com.example.paymentservice.exception.UserNotFound;
import com.example.paymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping("/saga")
@AllArgsConstructor
@Slf4j
public class SagaPaymentController {
    private final PaymentService paymentService;

    @PostMapping("/make-payment")
    public Mono<ResponseEntity<MakePaymentResponseBody>> makePayment(
            @RequestBody @Validated MakePaymentRequestBody requestBody
    ) {
        log.debug("requestBody : {}", requestBody);
        return
                this.paymentService
                        .makePayment(requestBody)
                        .map(ResponseEntity::ok)
                        .onErrorReturn(BalanceNotSufficientException.class, ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build())
                        .onErrorResume(throwable -> {
                            log.error(throwable.getMessage());
                            if (throwable instanceof BalanceNotSufficientException) {
                                //NOT_ACCEPTABLE
                                return Mono.just(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
                            } else if (throwable instanceof UserNotFound) {
                                //NOT_FOUND
                                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
                            } else {
                                //INTERNAL_SERVER_ERROR
                                return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                            }
                        });
    }

    @PostMapping("/make-payment/revert")
    public Mono<ResponseEntity<MakePaymentResponseBody>> makePaymentRevert(
            @RequestBody @Validated MakePaymentRequestBody requestBody
    ) {
        log.debug("requestBody : {}", requestBody);
        return
                this.paymentService
                        .makePayment(requestBody)
                        .map(ResponseEntity::ok)
                        .onErrorReturn(BalanceNotSufficientException.class, ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build())
                        .onErrorResume(throwable -> {
                            log.error(throwable.getMessage());
                            if (throwable instanceof BalanceNotSufficientException) {
                                //NOT_ACCEPTABLE
                                return Mono.just(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
                            } else if (throwable instanceof UserNotFound) {
                                //NOT_FOUND
                                return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
                            } else {
                                //INTERNAL_SERVER_ERROR
                                return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                            }
                        });
    }

}
