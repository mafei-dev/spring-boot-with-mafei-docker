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

@SagaExecutor(executeFor = "test-service-19", liveCheck = false, value = "POTestExecutor3")
public class POTestExecutor3 implements CommandExecutor<POAggregator> {
    @Override
    public ProcessStepManager<POAggregator> doProcess(ProcessStack previousProcessStack, POAggregator currentAggregate, ProcessStepManagerUtil<POAggregator> stepManager) throws RetryableExecutorException, NonRetryableExecutorException {
        currentAggregate.setAddress("test-service");
//        return stepManager.compete();
        throw NonRetryableExecutorException.buildWith(new RuntimeException("test")).build();

    }

    @Override
    public void doRevert(ProcessStack previousProcessStack, NonRetryableExecutorException nonRetryableExecutorException, POAggregator finalAggregateState, RevertHintStore revertHintStore) throws RetryableExecutorException {

    }
}
*/
