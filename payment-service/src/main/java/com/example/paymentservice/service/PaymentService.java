package com.example.paymentservice.service;

import com.example.paymentservice.collection.UserOrderPayment;
import com.example.paymentservice.collection.UserWallet;
import com.example.paymentservice.dto.req.MakePaymentRequestBody;
import com.example.paymentservice.dto.req.MakePaymentRevertRequestBody;
import com.example.paymentservice.dto.res.MakePaymentResponseBody;
import com.example.paymentservice.dto.res.MakePaymentRevertResponseBody;
import com.example.paymentservice.exception.BalanceNotSufficientException;
import com.example.paymentservice.exception.UserNotFound;
import com.example.paymentservice.repository.UserOrderPaymentRepository;
import com.example.paymentservice.repository.WalletRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentService {

    private final WalletRepository walletRepository;
    private final UserOrderPaymentRepository userOrderPaymentRepository;

    @Transactional
    public Mono<MakePaymentResponseBody> makePayment(MakePaymentRequestBody requestBody) {
        //step-1: get the wallet detail by passing the username and decrement (deduct) the balance.
        return this.walletRepository
                .deductAmount(requestBody.username(), requestBody.orderAmount() * -1)
                .switchIfEmpty(Mono.error(new UserNotFound("The user not found!")))
                .flatMap(updatedCount -> {
                    //check if the record was updated or not.
                    if (updatedCount > 0) {
                        //updated.
                        //find the updated record again to check the amount.
                        return this.walletRepository.findFirstByUsername(requestBody.username());
                    } else {
                        //not updated.
                        return Mono.error(new RuntimeException("Something went wrong."));
                    }
                })
                .flatMap(userWallet -> {
                    //step-2: re-check the balance is less than zero. (if it is less than zero, throw an error)
                    if (userWallet.amount() > 0) {
                        //the payment can be processed.
                        //step-3: save the user order payment (if the balance is enough)
                        LocalDateTime now = LocalDateTime.now();
                        return this.userOrderPaymentRepository
                                .save(UserOrderPayment.builder()
                                        ._id(null)
                                        .amount(requestBody.orderAmount())
                                        .dateTime(now)
                                        .orderId(requestBody.orderId())
                                        .status(UserOrderPayment.Status.SUCCESS)
                                        .paymentHistory(List.of(
                                                UserOrderPayment
                                                        .History
                                                        .builder()
                                                        .dateTime(now)
                                                        .status(UserOrderPayment.Status.SUCCESS)
                                                        .build()
                                        ))
                                        .username(requestBody.username())
                                        .build()
                                );
                    } else {
                        //the payment cannot be processed.
                        return Mono.error(new BalanceNotSufficientException("The wallet balance is not sufficient to make the transaction."));
                    }
                })
                .map(userOrderPayment -> MakePaymentResponseBody.builder()
                        .transactionId(userOrderPayment._id())
                        .transactionStatus(userOrderPayment.status().name())
                        .build()
                );
    }

    @Transactional
    public Mono<MakePaymentRevertResponseBody> makePaymentRevert(MakePaymentRevertRequestBody requestBody) {
        return this.userOrderPaymentRepository
                .findById(requestBody.transactionId())
                .switchIfEmpty(Mono.error(new UserNotFound("The transaction not found!")))
                .flatMap(userOrderPayment ->
                        this.walletRepository
                                .deductAmount(userOrderPayment.username(), userOrderPayment.amount())
                                .zipWith(Mono.just(userOrderPayment))
                )
                .map(objects -> {
                    List<UserOrderPayment.History> histories = objects.getT2().paymentHistory();
                    histories.add(UserOrderPayment
                            .History
                            .builder()
                            .status(UserOrderPayment.Status.REFUNDED)
                            .dateTime(LocalDateTime.now())
                            .build()
                    );
                    return objects.getT2()._id();
                })
                .map(transactionID -> MakePaymentRevertResponseBody
                        .builder()
                        .transactionId(transactionID)
                        .transactionStatus("refund-success")
                        .build()
                );
    }

    public Mono<Void> initWallet(String username, Double initAmount) {
        return this.walletRepository
                .findFirstByUsername(username)
                .doOnNext(userWallet -> {
                    log.info("wallet already exist [{}].", userWallet.username());
                })
                .switchIfEmpty(this.walletRepository.save(new UserWallet(null, username, initAmount)))
                .then();

    }

    public Mono<Boolean> checkTheBalanceByUsernameAndAmount(String username, Double amount) {
        return this.walletRepository
                .findFirstByUsername(username)
                .map(userWallet -> userWallet.amount() >= amount);
    }
}
