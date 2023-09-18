/*
package com.example.orderservice.saga.executor.test1;

import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.QueryExecutor;
import org.stacksaga.executor.utils.ProcessStack;

@SagaExecutor(executeFor = "test-service", liveCheck = false, value = "Test1QExecutor")
public class Test1QExecutor1 implements QueryExecutor<Test1PlaceOrderAggregator> {
    @Override
    public ProcessStepManager<Test1PlaceOrderAggregator> doProcess(
            ProcessStack processStack,
            Test1PlaceOrderAggregator aggregate,
            ProcessStepManagerUtil<Test1PlaceOrderAggregator> stepManagerUtil
    ) throws RetryableExecutorException, NonRetryableExecutorException {
        aggregate.setValue1("the value was changed in side of the Test1QExecutor1.");
        aggregate.getTestObject().setValue8("the value was changed in side of the Test1QExecutor1.");
        return stepManagerUtil.next(Test1QExecutor2.class);
    }
}
*/
