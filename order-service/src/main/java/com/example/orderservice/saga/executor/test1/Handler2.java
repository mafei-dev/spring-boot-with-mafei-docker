/*
package com.example.orderservice.saga.executor.test1;

import lombok.extern.slf4j.Slf4j;
import org.stacksaga.RevertHintStore;
import org.stacksaga.SagaAggregate;
import org.stacksaga.TransactionCompletedDetail;
import org.stacksaga.annotation.SagaExecutionEventListener;
import org.stacksaga.core.CompleteStatus;
import org.stacksaga.core.TrashFileDetail;
import org.stacksaga.core.annotation.SagaAsyncExecutionEventListener;
import org.stacksaga.core.listener.ExecutionEventListener;
import org.stacksaga.core.listener.TrashFileListener;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.exception.execution.TransactionTerminationDetail;
import org.stacksaga.executor.SagaExecutor;

@SagaExecutionEventListener
@Slf4j
@SagaAsyncExecutionEventListener
public class Handler2 implements ExecutionEventListener<Test1PlaceOrderAggregator>,TrashFileListener {
    @Override
//    @SagaAsyncListener
    public void onEachProcessPerformed(Class<? extends SagaExecutor<? extends SagaAggregate>> processedExecutor, Test1PlaceOrderAggregator currentAggregate) {
        log.debug("onEachProcessPerformed");
    }

    @Override
//    @SagaAsyncListener
    public void onEachRevertPerformed(Class<? extends SagaExecutor<? extends SagaAggregate>> revertedExecutor, Test1PlaceOrderAggregator finalAggregateState, NonRetryableExecutorException nonRetryableExecutorException, RevertHintStore revertHintStore) {
        log.debug("onEachRevertPerformed");
    }

    @Override
//    @SagaAsyncListener
    public void onTransactionCompleted(TransactionCompletedDetail<Test1PlaceOrderAggregator> transactionCompletedDetail, CompleteStatus completeStatus) {
        log.debug("onTransactionCompleted");
    }

    @Override
//    @SagaAsyncListener
    public void onTransactionTerminated(TransactionTerminationDetail<Test1PlaceOrderAggregator> transactionTerminationDetail) {
        log.debug("onTransactionTerminated");
    }

    @Override
    public void onProcessException(Test1PlaceOrderAggregator finalAggregateState, NonRetryableExecutorException exception, Class<? extends SagaExecutor<? extends SagaAggregate>> executorClass) {
        log.debug("onProcessException");
    }

    @Override
    public void onSaved(TrashFileDetail trashFileDetail) {
        log.debug("onSaved {}",trashFileDetail);
    }
}
*/
