package com.example.orderservice.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemModel {
    /**
     * PK
     */
    private String orderItemId;
    private String itemName;
    /**
     * this id coming from the stock management server
     */
    private String itemId;
    private Double total;
    private Integer qty;
    /**
     * the order id. FK from order table. {@link OrderModel}
     */
    private String orderId;
}
