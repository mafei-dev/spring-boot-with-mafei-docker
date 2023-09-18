/*
package com.example.orderservice.saga.executor.po.command;

import com.example.orderservice.saga.aggregator.POAggregator;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.RevertAfterExecutor;
import org.stacksaga.executor.utils.ProcessStack;
import org.stacksaga.executor.utils.RevertAfterStepManager;
import org.stacksaga.executor.utils.RevertAfterStepManagerUtil;

@SagaExecutor(executeFor = "revert-service-1", liveCheck = false, value = "PORevertAfterOfTestExecutor2")
public class PORevertAfter2OfTestExecutor1 implements RevertAfterExecutor<POAggregator, POTestExecutor1> {

    @Override
    public RevertAfterStepManager<POAggregator, POTestExecutor1> doProcess(
            POAggregator aggregator,
            ProcessStack previousProcessStack,
            NonRetryableExecutorException processException,
            RevertHintStore revertHintStore,
            RevertAfterStepManagerUtil<POAggregator, POTestExecutor1> revertStepManagerUtil
    ) throws RetryableExecutorException {

        return revertStepManagerUtil.complete();
    }
}
*/
