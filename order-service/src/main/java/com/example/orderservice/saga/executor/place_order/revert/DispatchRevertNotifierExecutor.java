/*
package com.example.orderservice.saga.executor.place_order.revert;

import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import com.example.orderservice.saga.executor.place_order.command.DispatchOrderExecutor;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.RevertBeforeExecutor;
import org.stacksaga.executor.utils.ProcessStack;
import org.stacksaga.executor.utils.RevertBeforeStepManager;
import org.stacksaga.executor.utils.RevertBeforeStepManagerUtil;

@SagaExecutor(
        executeFor = "delivery-service",
        liveCheck = true,
        value = "DispatchRevertNotifierExecutor"
)
public class DispatchRevertNotifierExecutor implements RevertBeforeExecutor<PlaceOrderAggregator, DispatchOrderExecutor> {

    @Override
    public RevertBeforeStepManager<PlaceOrderAggregator, DispatchOrderExecutor> doProcess(
            PlaceOrderAggregator aggregator,
            ProcessStack previousProcessStack,
            NonRetryableExecutorException processException,
            RevertHintStore revertHintStore,
            RevertBeforeStepManagerUtil<PlaceOrderAggregator, DispatchOrderExecutor> revertStepManagerUtil
    ) throws RetryableExecutorException {
//        return revertStepManagerUtil.next(NextBeforExecutor.class);
        //or
        return revertStepManagerUtil.complete();
    }
}
*/
