/*
package com.example.orderservice.saga.executor.po.command;

import com.example.orderservice.saga.aggregator.POAggregator;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.RevertAfter;
import org.stacksaga.annotation.RevertBefore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.CommandExecutor;
import org.stacksaga.executor.utils.ProcessStack;

@SagaExecutor(executeFor = "test-service", liveCheck = true, value = "POTestExecutor1")
public class POTestExecutor1 implements CommandExecutor<POAggregator> {
    @Override
    public ProcessStepManager<POAggregator> doProcess(
            ProcessStack previousProcessStack, POAggregator currentAggregate,
            ProcessStepManagerUtil<POAggregator> stepManager) throws RetryableExecutorException, NonRetryableExecutorException {
        System.out.println("POTestExecutor1.doProcess");
        currentAggregate.setAddress("added updated in POTestExecutor1");
        return stepManager.next(POTestExecutor2.class);
    }

    @Override
    @RevertBefore(startFrom = PORevertBeforeOfTestExecutor1.class)
    @RevertAfter(startFrom = PORevertAfterOfTestExecutor1.class)
    public void doRevert(ProcessStack previousProcessStack, NonRetryableExecutorException nonRetryableExecutorException, POAggregator finalAggregateState, RevertHintStore revertHintStore) throws RetryableExecutorException {
        System.out.println("POTestExecutor1.doRevert");
    }
}
*/
