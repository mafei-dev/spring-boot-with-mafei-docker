/*
package com.example.orderservice.saga.executor.place_order.command;

import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.mapper.OrderStatusMapper;
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

@SagaExecutor(executeFor = "order-service", liveCheck = true, value = "OrderSaveExecutor")
@AllArgsConstructor
public class OrderSaveExecutor implements CommandExecutor<PlaceOrderAggregator> {

    private final OrderMapper orderMapper;
    private final OrderStatusMapper orderStatusMapper;

    @Override
    @Transactional
    public ProcessStepManager<PlaceOrderAggregator> doProcess(
            ProcessStack processStack,
            PlaceOrderAggregator aggregator,
            ProcessStepManagerUtil<PlaceOrderAggregator> stepManager
    ) throws RetryableExecutorException, NonRetryableExecutorException {

        if (aggregator.getRealVersionAsString().equals("1.0.0")) {
            String comment = aggregator.getMissingProperties().get("comment").toString();
            System.out.println("comment = " + comment);

            for (PlaceOrderAggregator.ItemDetail itemDetail : aggregator.getItemDetails()) {
                String note = itemDetail.getMissingProperties().get("note").toString();
                System.out.println("note = " + note);
            }
        }
        ...

        return stepManager.next(UpdateStockExecutor.class);
    }

    @Override
    public void doRevert(ProcessStack processStack,
                         NonRetryableExecutorException e,
                         PlaceOrderAggregator aggregator,
                         RevertHintStore revertHintStore
    ) throws RetryableExecutorException {
        ...
    }
}
*/
