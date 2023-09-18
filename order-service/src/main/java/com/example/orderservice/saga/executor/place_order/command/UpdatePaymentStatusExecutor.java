/*
package com.example.orderservice.saga.executor.place_order.command;

import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import com.example.orderservice.saga.util.PlaceOrderExecutors;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.NoImplementation;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.CommandExecutor;
import org.stacksaga.executor.utils.ProcessStack;

@SagaExecutor(executeFor = "order-service", liveCheck = true, value = PlaceOrderExecutors.UpdatePaymentStatusExecutor)
@AllArgsConstructor
public class UpdatePaymentStatusExecutor implements CommandExecutor<PlaceOrderAggregator> {
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public ProcessStepManager<PlaceOrderAggregator> doProcess(ProcessStack processStack, PlaceOrderAggregator aggregator, ProcessStepManagerUtil<PlaceOrderAggregator> stepManager) throws RetryableExecutorException, NonRetryableExecutorException {
        this.orderMapper.updateTransaction(aggregator.getOrderId(), aggregator.getTransactionId());
        return stepManager.next(DispatchOrderExecutor.class);
    }

    @Override
    @NoImplementation
    public void doRevert(ProcessStack previousProcessStack, NonRetryableExecutorException nonRetryableExecutorException, PlaceOrderAggregator finalAggregateState, RevertHintStore revertHintStore) throws RetryableExecutorException {

    }

}
*/
