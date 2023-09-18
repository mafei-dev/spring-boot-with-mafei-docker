/*
package com.example.orderservice.saga.executor.test1;

import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.QueryExecutor;
import org.stacksaga.executor.utils.ProcessStack;

@SagaExecutor(executeFor = "test-service", liveCheck = false, value = "Test1QExecutor2")
public class Test1QExecutor2 implements QueryExecutor<Test1PlaceOrderAggregator> {
    @Override
    public ProcessStepManager<Test1PlaceOrderAggregator> doProcess(ProcessStack processStack, Test1PlaceOrderAggregator aggregate, ProcessStepManagerUtil<Test1PlaceOrderAggregator> stepManagerUtil) throws RetryableExecutorException, NonRetryableExecutorException {
        //changed
        return stepManagerUtil.compete();
    }
}
*/
