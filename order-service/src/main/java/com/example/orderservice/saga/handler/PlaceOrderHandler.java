package com.example.orderservice.saga.handler;

import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import org.stacksaga.RevertHintStore;
import org.stacksaga.Aggregator;
import org.stacksaga.TransactionCompletedDetail;
import org.stacksaga.annotation.SagaExecutionEventListener;
import org.stacksaga.core.CompleteStatus;
import org.stacksaga.core.TrashFileDetail;
import org.stacksaga.core.listener.ExecutionEventListener;
import org.stacksaga.core.listener.TrashFileListener;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.exception.execution.TransactionTerminationDetail;
import org.stacksaga.executor.SagaExecutor;

@SagaExecutionEventListener
public class PlaceOrderHandler implements ExecutionEventListener<PlaceOrderAggregator>, TrashFileListener {
    @Override
    public void onEachProcessPerformed(Class<? extends SagaExecutor<? extends Aggregator>> processedExecutor, PlaceOrderAggregator currentAggregate) {
        System.out.println("PlaceOrderHandler.onEachProcessPerformed");
    }

    @Override
    public void onEachRevertPerformed(
            Class<? extends SagaExecutor<? extends Aggregator>> revertedExecutor,
            PlaceOrderAggregator finalAggregateState,
            NonRetryableExecutorException nonRetryableExecutorException,
            RevertHintStore revertHintStore
    ) {
        System.out.println("PlaceOrderHandler.onEachRevertPerformed");

    }

    @Override
    public void onProcessException(
            PlaceOrderAggregator finalAggregateState,
            NonRetryableExecutorException exception,
            Class<? extends SagaExecutor<? extends Aggregator>> executorClass
    ) {
        System.out.println("PlaceOrderHandler.onProcessException");

    }

    @Override
    public void onTransactionCompleted(
            TransactionCompletedDetail<PlaceOrderAggregator> transactionCompletedDetail,
            CompleteStatus completeStatus
    ) {
        System.out.println("PlaceOrderHandler.onTransactionCompleted");
    }

    @Override
    public void onTransactionTerminated(
            TransactionTerminationDetail<PlaceOrderAggregator> transactionTerminationDetail) {
        System.out.println("PlaceOrderHandler.onTransactionTerminated");
    }

    @Override
    public void onSaved(TrashFileDetail trashFileDetail) {
        System.out.println("PlaceOrderHandler.onSaved");
    }
}
