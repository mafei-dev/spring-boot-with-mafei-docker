/*
package com.example.orderservice.saga.executor.place_order.query;

import com.example.orderservice.dto.UserDetailDto;
import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import com.example.orderservice.saga.executor.place_order.command.DispatchOrderExecutor;
import com.example.orderservice.service.external.UserServiceOF;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.core.annotation.SagaException;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.QueryExecutor;
import org.stacksaga.executor.utils.ProcessStack;

import java.time.LocalDateTime;


@SagaExecutor(
        executeFor = "user-service",
        liveCheck = true,
        value = "CheckUserExecutor"
)
@AllArgsConstructor
public class CheckUserExecutor implements QueryExecutor<PlaceOrderAggregator> {

    private final UserServiceOF userService;

    @Override
    public ProcessStepManager<PlaceOrderAggregator> doProcess(
            ProcessStack processStack,
            PlaceOrderAggregator aggregator,
            ProcessStepManagerUtil<PlaceOrderAggregator> stepManagerUtil
    ) throws RetryableExecutorException, NonRetryableExecutorException {

        try {
            ResponseEntity<UserDetailDto> userDetail = this.userService.getUserDetail(aggregator.getUsername());
            if (userDetail.getBody().getIsActive() == 1) {
                return stepManagerUtil.next(DispatchOrderExecutor.class);
            } else {
                UserInactiveException inactiveException = new UserInactiveException("User is not active!");
                throw NonRetryableExecutorException
                        .buildWith(inactiveException)
                        .build();
            }
        } catch (FeignException.ServiceUnavailable unavailableException) {
            throw RetryableExecutorException
                    .buildWith(unavailableException)
                    .build();
        } catch (FeignException.BadRequest badRequestException) {
            throw NonRetryableExecutorException
                    .buildWith(badRequestException)
                    .put("test", String.valueOf(LocalDateTime.now()))
                    .put("reason", "BadRequest")
                    .build();
        }
    }
}


*/
