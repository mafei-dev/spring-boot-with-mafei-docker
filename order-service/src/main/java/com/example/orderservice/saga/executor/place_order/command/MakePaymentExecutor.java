/*
package com.example.orderservice.saga.executor.place_order.command;

import com.example.orderservice.dto.MakePaymentRequestBody;
import com.example.orderservice.dto.MakePaymentResponseBody;
import com.example.orderservice.dto.MakePaymentRevertRequestBody;
import com.example.orderservice.exception.HttpStatusException;
import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import com.example.orderservice.saga.util.PlaceOrderExecutors;
import com.example.orderservice.service.external.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.CommandExecutor;
import org.stacksaga.executor.utils.ProcessStack;

@SagaExecutor(
        executeFor = "payment-service",
        liveCheck = true,
        value = PlaceOrderExecutors.MakePaymentExecutor
)
@AllArgsConstructor
public class MakePaymentExecutor implements CommandExecutor<PlaceOrderAggregator> {

    private final PaymentService paymentService;

    @Override
    public ProcessStepManager<PlaceOrderAggregator> doProcess(ProcessStack processStack, PlaceOrderAggregator aggregator, ProcessStepManagerUtil<PlaceOrderAggregator> stepManager) throws RetryableExecutorException, NonRetryableExecutorException {


        if (aggregator.getRealVersionAsString().equals("1.0.0")) {

        }

        final MakePaymentResponseBody responseBody = this.paymentService.makePayment(
                MakePaymentRequestBody
                        .builder()
                        .username(aggregator.getUsername())
                        .orderId(aggregator.getOrderId())
                        .orderAmount(aggregator.getTotal())
                        .build()
        );

        aggregator.setTransactionId(responseBody.getTransactionId());
        aggregator.setTransactionStatus(responseBody.getTransactionStatus());
        return stepManager.next(UpdatePaymentStatusExecutor.class);
    }

    @Override
    public void doRevert(ProcessStack processStack, NonRetryableExecutorException e, PlaceOrderAggregator aggregator, RevertHintStore revertHintStore) throws RetryableExecutorException {
        try {
            this.paymentService.makePaymentRevert(MakePaymentRevertRequestBody
                    .builder()
                    .transactionId(aggregator.getTransactionId())
                    .build()
            );
        } catch (HttpStatusException ex) {
            if (ex.checkEqualsTo(HttpStatus.GATEWAY_TIMEOUT, HttpStatus.REQUEST_TIMEOUT)) {
                throw RetryableExecutorException.buildWith(ex).build();
            } else {
                throw new RuntimeException(ex);
            }
        }
    }
}
*/
