/*
package com.example.orderservice.saga.executor.place_order.command;

import com.example.orderservice.mapper.OrderItemMapper;
import com.example.orderservice.model.OrderItemModel;
import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import com.example.orderservice.saga.util.PlaceOrderExecutors;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.Meta;
import org.stacksaga.annotation.NoImplementation;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.CommandExecutor;
import org.stacksaga.executor.utils.ProcessStack;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SagaExecutor(
        executeFor = "order-service",
        liveCheck = true,
        value = PlaceOrderExecutors.SaveOrderItemExecutor,
        meta = {
                @Meta(key = "note", value = "")
        }
)
@AllArgsConstructor
public class SaveOrderItemExecutor implements CommandExecutor<PlaceOrderAggregator> {

    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public ProcessStepManager<PlaceOrderAggregator> doProcess(ProcessStack processStack,
                                                              PlaceOrderAggregator aggregator, ProcessStepManagerUtil<PlaceOrderAggregator> stepManager) throws RetryableExecutorException, NonRetryableExecutorException {
        List<OrderItemModel> itemModelList = aggregator
                .getItemDetail()
                .stream()
                .map(cartItemDto -> OrderItemModel.builder()
                        .orderItemId(UUID.randomUUID().toString())
                        .itemName(cartItemDto.getItemName())
                        .itemId(cartItemDto.getItemCode())
                        .total(cartItemDto.getTotal())
                        .qty(cartItemDto.getQty())
                        .orderId(aggregator.getOrderId())
                        .build()
                )
                .collect(Collectors.toList());
        this.orderItemMapper.saveAll(itemModelList);
        return stepManager.next(MakePaymentExecutor.class);
    }

    @Override
    @NoImplementation
    public void doRevert(ProcessStack previousProcessStack, NonRetryableExecutorException nonRetryableExecutorException, PlaceOrderAggregator finalAggregateState, RevertHintStore revertHintStore) throws RetryableExecutorException {

    }
}
*/
