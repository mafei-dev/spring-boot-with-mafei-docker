/*
package com.example.orderservice.saga.executor.po.command;

import com.example.orderservice.saga.aggregator.POAggregator;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.RevertBeforeExecutor;
import org.stacksaga.executor.utils.ProcessStack;
import org.stacksaga.executor.utils.RevertBeforeStepManager;
import org.stacksaga.executor.utils.RevertBeforeStepManagerUtil;

@SagaExecutor(executeFor = "revert-service-1", liveCheck = false, value = "PORevertBefore2OfTestExecutor1")
public class PORevertBefore2OfTestExecutor1 implements RevertBeforeExecutor<POAggregator,POTestExecutor1> {

    @Override
    public RevertBeforeStepManager<POAggregator, POTestExecutor1> doProcess(POAggregator aggregator, ProcessStack previousProcessStack, NonRetryableExecutorException processException, RevertHintStore revertHintStore, RevertBeforeStepManagerUtil<POAggregator, POTestExecutor1> revertStepManagerUtil) throws RetryableExecutorException {
        return revertStepManagerUtil.complete();
    }
}
*/
