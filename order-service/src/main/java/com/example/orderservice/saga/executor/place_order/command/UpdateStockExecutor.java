/*
package com.example.orderservice.saga.executor.place_order.command;

import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import lombok.AllArgsConstructor;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.CommandExecutor;
import org.stacksaga.executor.utils.ProcessStack;

@SagaExecutor(
        executeFor = "stock-management-service",
        liveCheck = true,
        value = "UpdateStockExecutor"
)
@AllArgsConstructor
public class UpdateStockExecutor implements CommandExecutor<PlaceOrderAggregator> {


    @Override
    public ProcessStepManager<PlaceOrderAggregator> doProcess(ProcessStack processStack, PlaceOrderAggregator aggregator, ProcessStepManagerUtil<PlaceOrderAggregator> stepManager) throws RetryableExecutorException, NonRetryableExecutorException {
        return stepManager.compete();
    }

    @Override
    public void doRevert(
            ProcessStack previousProcessStack,
            NonRetryableExecutorException realException,
            PlaceOrderAggregator finalAggregateState,
            RevertHintStore revertHintStore
    ) throws RetryableExecutorException {

        realException.getRealSagaExceptionName().ifPresent(realSagaExceptionName -> {
            if (realSagaExceptionName.equals("UserInactiveException")) {

                realException.get("reason").ifPresent(reason -> {
                    if (reason.equalsIgnoreCase("BadRequest")) {
                        //do the revert process
                    }
                });
            }
        });

    }

*/
/*@Override
    public void doRevert(
            ProcessStack processStack,
            NonRetryableExecutorException nonRetryableExecutorException,
            PlaceOrderAggregator finalAggregator,
            RevertHintStore revertHintStore
    ) throws RetryableExecutorException {
        System.out.println("UpdateStockExecutor.doRevert");
        UpdateItemRequest.Builder updateItemRequestBuilder = UpdateItemRequest.newBuilder();
        //loop
        for (PlaceOrderAggregator.CartItemDto cartItemDto : aggregator.getItemDetail()) {
            updateItemRequestBuilder
                    .addItemList(ItemDto.newBuilder()
                            .setItemCode(cartItemDto.getItemCode())
                            .setQty(cartItemDto.getQty())
                            .build()
                    );
        }
        updateItemRequestBuilder.setStockUpdateType(StockUpdateType.INCREASED);
        try {
            this.stockManagementService.updateItem(updateItemRequestBuilder.build());
        } catch (StatusRuntimeException exception) {
            if (GRPCStatusExceptionUtil.checkEqualsTo(
                    exception,
                    Status.UNAVAILABLE,
                    Status.DEADLINE_EXCEEDED,
                    Status.UNKNOWN)) {
                throw RetryableExecutorException.buildWith(exception).build();
            } else {
                throw new RuntimeException(exception);
            }
        }
    }*//*

}
*/
