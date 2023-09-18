/*
package com.example.orderservice.saga.executor.place_order.revert;

import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import com.example.orderservice.saga.executor.place_order.command.DispatchOrderExecutor;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.RevertAfterExecutor;
import org.stacksaga.executor.utils.ProcessStack;
import org.stacksaga.executor.utils.RevertAfterStepManager;
import org.stacksaga.executor.utils.RevertAfterStepManagerUtil;

@SagaExecutor(
        executeFor = "delivery-service",//<2>
        liveCheck = true,//<3>
        value = "DispatchRevertNotifierExecutor" //<4>
)
public class DispatchRevertCompleteLogExecutor implements RevertAfterExecutor<PlaceOrderAggregator, DispatchOrderExecutor> {
    @Override
    public RevertAfterStepManager<PlaceOrderAggregator, DispatchOrderExecutor> doProcess(PlaceOrderAggregator aggregator, ProcessStack previousProcessStack, NonRetryableExecutorException processException, RevertHintStore revertHintStore, RevertAfterStepManagerUtil<PlaceOrderAggregator, DispatchOrderExecutor> revertStepManagerUtil) throws RetryableExecutorException {
        return revertStepManagerUtil.complete();
    }
}
*/
