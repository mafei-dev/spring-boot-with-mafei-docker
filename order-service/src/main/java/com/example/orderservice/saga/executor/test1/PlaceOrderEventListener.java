/*
package com.example.orderservice.saga.executor.test1;

import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import org.stacksaga.RevertHintStore;
import org.stacksaga.SagaAggregate;
import org.stacksaga.TransactionCompletedDetail;
import org.stacksaga.annotation.SagaExecutionEventListener;
import org.stacksaga.core.CompleteStatus;
import org.stacksaga.core.listener.ExecutionEventListener;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.exception.execution.TransactionTerminationDetail;
import org.stacksaga.executor.SagaExecutor;

@SagaExecutionEventListener
public class PlaceOrderEventListener implements ExecutionEventListener<PlaceOrderAggregator> {
    @Override
    public void onEachProcessPerformed(Class<? extends SagaExecutor<? extends SagaAggregate>> processedExecutor, PlaceOrderAggregator currentAggregate) {
        //do whatever you want
    }

    @Override
    public void onEachRevertPerformed(Class<? extends SagaExecutor<? extends SagaAggregate>> revertedExecutor, PlaceOrderAggregator finalAggregateState, NonRetryableExecutorException nonRetryableExecutorException, RevertHintStore revertHintStore) {
        //do whatever you want
    }

    @Override
    public void onTransactionCompleted(TransactionCompletedDetail<PlaceOrderAggregator> transactionCompletedDetail, CompleteStatus completeStatus) {
        //do whatever you want
    }

    @Override
    public void onTransactionTerminated(TransactionTerminationDetail<PlaceOrderAggregator> transactionTerminationDetail) {
        //do whatever you want
    }

    @Override
    public void onProcessException(PlaceOrderAggregator finalAggregateState, NonRetryableExecutorException exception, Class<? extends SagaExecutor<? extends SagaAggregate>> executorClass) {
        //do whatever you want
    }
}
*/
