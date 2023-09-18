/*
package com.example.orderservice.saga.executor.place_order.query;

import com.example.orderservice.saga.aggregator.PlaceOrderAggregator;
import com.example.orderservice.saga.executor.place_order.command.OrderSaveExecutor;
import com.example.orderservice.saga.util.PlaceOrderExecutors;
import com.example.orderservice.service.external.CartServiceGRPC;
import com.example.stockmanagementservice.grpc.GetCartItemDetailResponse;
import com.google.common.util.concurrent.AtomicDouble;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.AllArgsConstructor;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.QueryExecutor;
import org.stacksaga.executor.utils.ProcessStack;

import java.util.List;
import java.util.stream.Collectors;

@SagaExecutor(executeFor = "stock-management-service", liveCheck = true, value = PlaceOrderExecutors.GetItemDetailExecutor)
@AllArgsConstructor
public class GetItemDetailExecutor implements QueryExecutor<PlaceOrderAggregator> {

    private final CartServiceGRPC cartServiceGRPC;

    @Override
    public ProcessStepManager<PlaceOrderAggregator> doProcess(ProcessStack processStack, PlaceOrderAggregator aggregator, ProcessStepManagerUtil<PlaceOrderAggregator> stepManager)
            throws RetryableExecutorException, NonRetryableExecutorException {
        try {

            final GetCartItemDetailResponse itemDetails = this.cartServiceGRPC.getItemDetails(aggregator.getUsername());

            final AtomicDouble total = new AtomicDouble(0.0);

            List<PlaceOrderAggregator.CartItemDto> cartItemDtoList = itemDetails
                    .getCartItemsList()
                    .stream()
                    .map(cartItemDto -> {
                                total.set(total.get() + cartItemDto.getTotal());
                                return PlaceOrderAggregator
                                        .CartItemDto
                                        .builder()
                                        .itemName(cartItemDto.getItemName())
                                        .cartItemId(cartItemDto.getCartItemId())
                                        .itemCode(cartItemDto.getItemCode())
                                        .qty(cartItemDto.getQty())
                                        .total(cartItemDto.getTotal())
                                        .build();
                            }
                    )
                    .collect(Collectors.toList());

            aggregator.setItemDetail(cartItemDtoList);
            aggregator.setTotal(total.get());
            return stepManager.next(OrderSaveExecutor.class);
        } catch (StatusRuntimeException e) {
            Status status = e.getStatus().asException().getStatus();
            System.out.println("status = " + status);
            if (e.getStatus().getCode().equals(Status.UNAVAILABLE.getCode())) {
                throw RetryableExecutorException
                        .buildWith(e)
                        .build();
            } else {
                throw e;
            }
        }
    }
}
*/
