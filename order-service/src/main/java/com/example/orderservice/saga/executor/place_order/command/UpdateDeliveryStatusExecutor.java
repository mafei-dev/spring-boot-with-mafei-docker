/*
package com.example.orderservice.saga.executor.place_order.command;

import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.mapper.OrderStatusMapper;
import com.example.orderservice.model.OrderStatusModel;
import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import com.example.orderservice.saga.util.PlaceOrderExecutors;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.NoImplementation;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.CommandExecutor;
import org.stacksaga.executor.utils.ProcessStack;

import java.time.LocalDateTime;
import java.util.UUID;

@SagaExecutor(
        executeFor = "order-service", liveCheck = true,
        value = PlaceOrderExecutors.UpdateDeliveryStatusExecutor
)
@AllArgsConstructor
public class UpdateDeliveryStatusExecutor implements CommandExecutor<PlaceOrderAggregator> {
    private final OrderMapper orderMapper;
    private final OrderStatusMapper orderStatusMapper;

    @Override
    @Transactional
    public ProcessStepManager<PlaceOrderAggregator> doProcess(ProcessStack processStack, PlaceOrderAggregator aggregator, ProcessStepManagerUtil<PlaceOrderAggregator> stepManager) throws RetryableExecutorException, NonRetryableExecutorException {
        this.orderMapper.updateDeliveryId(
                aggregator.getOrderId(),
                "aggregator.getDeliveryId()"
        );
        this.orderStatusMapper.save(
                OrderStatusModel
                        .builder()
                        .orderStatusId(UUID.randomUUID().toString())
                        .orderId(aggregator.getOrderId())
                        .updatedDatetime(LocalDateTime.now())
                        .orderStatus(OrderStatusModel.OrderStatus.DELIVERED)
                        .build()
        );
        return stepManager.next(IncreasePointExecutor.class);
    }

    @Override
    @NoImplementation
    public void doRevert(ProcessStack processStack, NonRetryableExecutorException e, PlaceOrderAggregator aggregator, RevertHintStore revertHintStore) throws RetryableExecutorException {
        System.out.println("UpdateDeliveryStatusExecutor.doRevert");
    }
}
*/
