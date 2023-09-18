package com.example.paymentservice.controller;

import com.example.paymentservice.dto.req.MakePaymentRequestBody;
import com.example.paymentservice.dto.req.MakePaymentRevertRequestBody;
import com.example.paymentservice.dto.res.MakePaymentResponseBody;
import com.example.paymentservice.dto.res.MakePaymentRevertResponseBody;
import com.example.paymentservice.exception.BalanceNotSufficientException;
import com.example.paymentservice.exception.UserNotFound;
import com.example.paymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/make-payment")
    public Mono<ResponseEntity<MakePaymentResponseBody>> makePayment(
            @RequestBody @Validated MakePaymentRequestBody requestBody
    ) {
        log.debug("requestBody : {}", requestBody);
        return this.paymentService
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

    /**
     * @param requestBody
     * @return
     * @see PaymentController#makePayment(MakePaymentRequestBody)
     */
    @PutMapping("/make-payment")
    public Mono<ResponseEntity<MakePaymentRevertResponseBody>> makePaymentRefund(
            @RequestBody @Validated MakePaymentRevertRequestBody requestBody
    ) {
        log.debug("MakePaymentRevertRequestBody : {}", requestBody);
        return this.paymentService
                .makePaymentRevert(requestBody)
                .map(ResponseEntity::ok)
                .onErrorReturn(SQLException.class, ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build())
                .onErrorReturn(Exception.class, ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/check-balance/{username}/{amount}")
    public Mono<ResponseEntity<?>> checkTheBalance(@PathVariable String username, @PathVariable Double amount) {
        log.debug("username : {}, amount:{}", username, amount);
        return this.paymentService.checkTheBalanceByUsernameAndAmount(username, amount)
                .map(aBoolean -> {
                    if (aBoolean) {
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
                    }
                });
    }
}
