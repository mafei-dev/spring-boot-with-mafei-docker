package com.example.orderservice.saga.executor.place_order.command;

import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.CommandExecutor;
import org.stacksaga.executor.utils.ProcessStack;

import java.time.LocalDateTime;

@SagaExecutor(
        executeFor = "delivery-service",
        liveCheck = true,
        value = "DispatchOrderExecutor"
)
public class DispatchOrderExecutor implements CommandExecutor<PlaceOrderAggregator> {


    @Override
    public ProcessStepManager<PlaceOrderAggregator> doProcess(
            ProcessStack processStack,
            PlaceOrderAggregator aggregator,
            ProcessStepManagerUtil<PlaceOrderAggregator> stepManager
    ) throws RetryableExecutorException, NonRetryableExecutorException {
        //

        return stepManager.compete();
    }

    @Override
//    @RevertBefore(startFrom = DispatchRevertNotifierExecutor.class)
//    @RevertAfter(startFrom = DispatchRevertCompleteLogExecutor.class)
    public void doRevert(ProcessStack processStack,
                         NonRetryableExecutorException realException,
                         PlaceOrderAggregator aggregator,
                         RevertHintStore revertHintStore
    ) throws RetryableExecutorException {

        revertHintStore
                .get("MakePaymentExecutor")
                .ifPresent(makePaymentExecutor -> {
                    if (makePaymentExecutor.equalsIgnoreCase("done")) {
                        revertHintStore.put("last_updated_time", LocalDateTime.now().toString());
                        revertHintStore.put("DispatchOrderExecutor", "success");

                    }
                });
    }
}
