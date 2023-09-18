/*
package com.example.orderservice.saga.executor.po.command;

import com.example.orderservice.saga.aggregator.POAggregator;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.CommandExecutor;
import org.stacksaga.executor.utils.ProcessStack;

@SagaExecutor(executeFor = "test-service", liveCheck = true, value = "POTestExecutor2")
public class POTestExecutor2 implements CommandExecutor<POAggregator> {
    @Override
    public ProcessStepManager<POAggregator> doProcess(ProcessStack previousProcessStack, POAggregator currentAggregate, ProcessStepManagerUtil<POAggregator> stepManager) throws RetryableExecutorException, NonRetryableExecutorException {
        return stepManager.next(POTestExecutor3.class);
    }

    @Override
    public void doRevert(ProcessStack previousProcessStack, NonRetryableExecutorException nonRetryableExecutorException, POAggregator finalAggregateState, RevertHintStore revertHintStore) throws RetryableExecutorException {
        revertHintStore.get("key1").ifPresent(o -> {
            System.out.println("o = " + o);
        });

    }
}
*/
