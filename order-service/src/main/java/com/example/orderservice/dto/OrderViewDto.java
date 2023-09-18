package com.example.orderservice.dto;

import com.example.orderservice.model.OrderModel;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderViewDto {
    private String orderId;
    private String username;
    private String deliveryId;
    private String transactionId;
    private LocalDateTime invoiceDatetime;

    private List<OrderItem> orderItemList;

    private String orderStatus;

    private DeliveryDetail deliveryDetail;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItem {
        private String orderItemId;
        private String itemName;
        /**
         * this id coming from the stock management server
         */
        private String itemId;
        private Double total;
        private Integer qty;

    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeliveryDetail {
        private Long deliveryDetailId;
        private String address;
        private String tel;
        private String email;
    }

}
