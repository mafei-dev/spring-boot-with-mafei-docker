/*
package com.example.orderservice.saga.executor.place_order.command;

import com.example.orderservice.exception.GRPCStatusExceptionUtil;
import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import com.example.orderservice.saga.util.PlaceOrderExecutors;
import com.example.orderservice.service.external.UserPointService;
import com.example.userpointservice.service.IncreaseUserPointRequest;
import com.example.userpointservice.service.IncreaseUserPointRevertRequest;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.AllArgsConstructor;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.CommandExecutor;
import org.stacksaga.executor.utils.ProcessStack;

import java.util.Objects;

@SagaExecutor(executeFor = "point-service", liveCheck = true, value = PlaceOrderExecutors.IncreasePointExecutor)
@AllArgsConstructor
public class IncreasePointExecutor implements CommandExecutor<PlaceOrderAggregator> {

    private final UserPointService userPointService;

    @Override
    public ProcessStepManager<PlaceOrderAggregator> doProcess(ProcessStack processStack,
                                                              PlaceOrderAggregator aggregator, ProcessStepManagerUtil<PlaceOrderAggregator> stepManager) throws RetryableExecutorException, NonRetryableExecutorException {
        //[point-service] [external]
        this.userPointService.increasePoint(IncreaseUserPointRequest.newBuilder()
                .setUsername(aggregator.getUsername())
                .setAmount(1)
                .setReason("buying")
                .build()
        );
        return stepManager.compete();
    }

    @Override
    public void doRevert(ProcessStack processStack, NonRetryableExecutorException e, PlaceOrderAggregator aggregator,
                         RevertHintStore revertHintStore) throws RetryableExecutorException {

        try {
            this.userPointService.increasePointRevert(
                    IncreaseUserPointRevertRequest
                            .newBuilder()
                            .setUsername(aggregator.getUsername())
                            .setAmount(1)
                            .setReason("REVERTED:" + e.getMessage())
                            .build()
            );

        } catch (StatusRuntimeException exception) {
            //catches the exception that can be caused to a retry the request again.
            if (GRPCStatusExceptionUtil.checkEqualsTo(
                    exception,
                    Status.UNAVAILABLE,
                    Status.DEADLINE_EXCEEDED,
                    Status.UNKNOWN)
            ) {
                throw RetryableExecutorException.buildWith(exception).build();
            } else {
                throw new RuntimeException(exception);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IncreasePointExecutor)) return false;
        IncreasePointExecutor that = (IncreasePointExecutor) o;
        return Objects.equals(userPointService, that.userPointService);
    }

}
*/
