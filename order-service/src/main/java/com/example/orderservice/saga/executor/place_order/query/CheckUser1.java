/*
package com.example.orderservice.saga.executor.place_order.query;

import com.example.orderservice.dto.UserDetailDto;
import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import com.example.orderservice.service.external.UserServiceOF;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.QueryExecutor;
import org.stacksaga.executor.utils.ProcessStack;

import java.util.Objects;

@SagaExecutor(executeFor = "user-service", liveCheck = true, value = "CheckUser1")
@AllArgsConstructor
public class CheckUser1 implements QueryExecutor<PlaceOrderAggregator> {

    private final UserServiceOF userServiceOF;

    @Override
    public ProcessStepManager<PlaceOrderAggregator> doProcess(ProcessStack processStack, PlaceOrderAggregator aggregate, ProcessStepManagerUtil<PlaceOrderAggregator> stepManagerUtil) throws RetryableExecutorException, NonRetryableExecutorException {

        ResponseEntity<UserDetailDto> userDetail = userServiceOF.getUserDetail(aggregate.getUsername());
        if (Objects.requireNonNull(userDetail.getBody()).getIsActive() == 1) {


        } else {

        }
        return stepManagerUtil.compete();
    }
}
*/
