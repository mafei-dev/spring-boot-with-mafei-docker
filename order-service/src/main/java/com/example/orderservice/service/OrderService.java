package com.example.orderservice.service;

import com.example.orderservice.dto.*;
import com.example.orderservice.exception.OrderNotFoundException;
import com.example.orderservice.exception.UserNoActiveException;
import com.example.orderservice.mapper.OrderItemMapper;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.mapper.OrderStatusMapper;
import com.example.orderservice.model.OrderItemModel;
import com.example.orderservice.model.OrderModel;
import com.example.orderservice.model.OrderStatusModel;
import com.example.orderservice.service.external.*;
import com.example.orderservice.service.external.wrapper.UserServiceWrapper;
import com.example.stockmanagementservice.grpc.*;
import com.example.userpointservice.service.IncreaseUserPointRequest;
import com.google.common.util.concurrent.AtomicDouble;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {
    private final UserServiceWrapper userServiceOF;
    private final CartServiceGRPC cartServiceGRPC;
    private final PaymentService paymentService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderStatusMapper orderStatusMapper;
    private final StockManagementServiceGRPC stockManagementService;
    private final DeliveryService deliveryService;
    private final UserPointService userPointService;

    /**
     * @param username
     * @return orderId
     */
    //command method
    @CircuitBreaker(name = "placeOrder", fallbackMethod = "placeOrderFallbackMethod")
    @Transactional
    public String placeOrder(String username) {
        LocalDateTime now = LocalDateTime.now();
        //this is the place all the services are accessed. [the method will talk to other services to place the order.]
        UserDetailDto userDetail;
        {
            //01
            //get the user details to check the user is active or not.
            //[user-service] [external] [http]
            ResponseEntity<UserDetailDto> userDetailDtoResponseEntity = this.userServiceOF.getUserDetail(username);
            log.debug("X-server-port:{}", userDetailDtoResponseEntity.getHeaders().get("X-server-port"));
            userDetail = userDetailDtoResponseEntity.getBody();
            assert userDetail != null;
            if (userDetail.getIsActive() != 1) {
                //user is not active.
                String msg = String.format("The user %s is not active at this moment.", username);
                throw new UserNoActiveException(msg, username);
            }
        }
        GetCartItemDetailResponse cartItemDetailResponse;
        AtomicDouble total = new AtomicDouble(0.0);
        {
            //02
            //get the cart item details for the user.
            //[stock-management-service] [external] [GRPC]
            cartItemDetailResponse = cartServiceGRPC.getItemDetails(username);
            //calculate the total of the cart.
            cartItemDetailResponse
                    .getCartItemsList()
                    .forEach(cartItemDto -> total.set(total.get() + cartItemDto.getTotal()));
        }
        {
            //03
            //check the user's balance is enough tp process the order.
            //[payment-service] [external]
            this.paymentService.checkTheBalance(username, total.get());
        }
        String orderID = UUID.randomUUID().toString();
        {
            //04
            //add new order (initialize the order)
            //[order-service] [internal]

            this.orderMapper.save(
                    OrderModel.builder()
                            .orderId(orderID)
                            .username(username)
                            .deliveryId(null)
                            .transactionId(null)
                            .invoiceDatetime(now)
                            .build()
            );

        }

        {
            //05
            //update the current stock.
            //[stock-management-service] [external]

            UpdateItemRequest.Builder updateItemRequestBuilder = UpdateItemRequest.newBuilder();
            //loop
            for (CartItemDto cartItemDto : cartItemDetailResponse.getCartItemsList()) {
                updateItemRequestBuilder
                        .addItemList(ItemDto.newBuilder()
                                .setItemCode(cartItemDto.getItemCode())
                                .setQty(cartItemDto.getQty())
                                .build()
                        );
            }
            updateItemRequestBuilder.setStockUpdateType(StockUpdateType.DECREASED);
            this.stockManagementService.updateItem(updateItemRequestBuilder.build());

        }

        {
            //06
            //add the items into the order_item table.
            //[order-service] [internal]
            List<OrderItemModel> itemModelList = cartItemDetailResponse
                    .getCartItemsList()
                    .stream()
                    .map(cartItemDto -> OrderItemModel.builder()
                            .orderItemId(UUID.randomUUID().toString())
                            .itemName(cartItemDto.getItemName())
                            .itemId(cartItemDto.getItemCode())
                            .total(cartItemDto.getTotal())
                            .qty(cartItemDto.getQty())
                            .orderId(orderID)
                            .build()
                    )
                    .collect(Collectors.toList());

            this.orderItemMapper.saveAll(itemModelList);

        }
        MakePaymentResponseBody paymentResponseBody;
        {
            //07.0
            //make the payment
            //[payment-service] [external]
            paymentResponseBody = this.paymentService.makePayment(
                    MakePaymentRequestBody
                            .builder()
                            .username(username)
                            .orderId(orderID)
                            .orderAmount(total.get())
                            .build()
            );
            //07.1
            //update the transaction id of the order.
            //[order-service] [internal]
            this.orderMapper.updateTransaction(orderID, paymentResponseBody.getTransactionId());

        }

        {
            //08.0
            //dispatch the order.
            //[delivery-service] [external]
            DeliveryDetailResponseBody deliveryDetailResponseBody = this.deliveryService.dispatchOrder(DeliveryDetailRequestBody.builder()
                    .username(username)
                    .address(userDetail.getAddress())
                    .tel(userDetail.getTel())
                    .email(userDetail.getEmail())
                    .orderId(orderID)
                    .transactionId(paymentResponseBody.getTransactionId())
                    .build());

            //08.1
            //update the delivery id on the order table.
            //[order-service] [internal]
            this.orderMapper.updateDeliveryId(orderID, String.valueOf(deliveryDetailResponseBody.getDeliveryDetailId()));
        }

        {
            //09
            //update the status of the order. (add new record to the order status table.)
            //[order-service] [internal]
            this.orderStatusMapper.save(OrderStatusModel.builder()
                    .orderStatusId(UUID.randomUUID().toString())
                    .orderId(orderID)
                    .updatedDatetime(now)
                    .orderStatus(OrderStatusModel.OrderStatus.DELIVERED)
                    .build()
            );
        }
        {
            //10
            //increase the user's point.
            //[point-service] [external]
            this.userPointService.increasePoint(IncreaseUserPointRequest.newBuilder()
                    .setUsername(username)
                    .setAmount(1)
                    .setReason("buying")
                    .build()
            );
        }

        {
            //11
            //assigment
            //remove all the cart details of the user.

        }

        return orderID;
    }

    public String placeOrderFallbackMethod(String username, Exception exception) {
        exception.printStackTrace();
        throw new RuntimeException("there is an internal server error repeatedly.");
    }

    //query method
    public OrderViewDto getOrderDetail(String orderId) {
        //01-get the basic details regarding the order.[order-service| internal service]

        return this
                .orderMapper
                .getOrderDetail(orderId)
                .map(orderModel -> {
//                    ResponseEntity<UserDetailDto> userDetail = this.userServiceOF.getUserDetail(orderModel.getUsername());
                    this.userServiceOF.checkUserIsActive(orderModel.getUsername());
                    return orderModel;
                })
                .map(orderModel -> OrderViewDto.builder()
                        .orderId(orderModel.getOrderId())
                        .username(orderModel.getUsername())
                        .deliveryId(orderModel.getDeliveryId())
                        .transactionId(orderModel.getTransactionId())
                        .invoiceDatetime(orderModel.getInvoiceDatetime())
                )
                .map(orderViewDtoBuilder -> this.orderStatusMapper.getStatusListByOrderId(orderId)
                        .stream().findFirst()
                        .map(orderStatusModel -> {
                            orderViewDtoBuilder.orderStatus(orderStatusModel.getOrderStatus().toString());
                            return orderViewDtoBuilder;
                        })
                        .orElseThrow(RuntimeException::new)
                )
                .map(orderViewDtoBuilder -> {
                    List<OrderViewDto.OrderItem> orderItemList = new ArrayList<>();
                    this.orderItemMapper
                            .getItemsByOrderId(orderId)
                            .forEach(orderItemModel -> {
                                orderItemList.add(OrderViewDto.OrderItem.builder()
                                        .orderItemId(orderItemModel.getOrderItemId())
                                        .itemId(orderItemModel.getItemId())
                                        .itemName(orderItemModel.getItemName())
                                        .total(orderItemModel.getTotal())
                                        .qty(orderItemModel.getQty())
                                        .build());
                            });
                    orderViewDtoBuilder.orderItemList(orderItemList);
                    return orderViewDtoBuilder;
                })
                .map(orderViewDtoBuilder -> {
                    //02-get the delivery details regarding the order.[delivery-service | external service]
                    DeliveryDetailResponse deliveryDetails = this.deliveryService.getDeliveryDetails(orderId);
                    if (deliveryDetails == null) {
                        orderViewDtoBuilder.deliveryDetail(null);
                    } else {
                        OrderViewDto.DeliveryDetail deliveryDetail = OrderViewDto.DeliveryDetail.builder()
                                .deliveryDetailId(deliveryDetails.getDeliveryDetailId())
                                .address(deliveryDetails.getAddress())
                                .tel(deliveryDetails.getTel())
                                .email(deliveryDetails.getEmail())
                                .build();
                        orderViewDtoBuilder.deliveryDetail(deliveryDetail);
                    }
                    return orderViewDtoBuilder;
                })
                .map(OrderViewDto.OrderViewDtoBuilder::build)
                .orElseThrow(() ->
                        new OrderNotFoundException("There is no order for given order-id.", orderId)
                );
    }
}
