package com.example.orderservice.saga.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceOrderExecutors {

    /**
     * @see com.example.orderservice.saga.executor.place_order.query.CheckUserExecutor
     */
    public final static String CheckUserExecutor = "CheckUserExecutor";
    /**
     * @see com.example.orderservice.saga.executor.place_order.query.GetItemDetailExecutor
     */
    public final static String GetItemDetailExecutor = "GetItemDetailExecutor";

    /**
     * @see com.example.orderservice.saga.executor.place_order.command.OrderSaveExecutor
     */
    public final static String OrderSaveExecutor = "OrderSaveExecutor";

    /**
     * @see com.example.orderservice.saga.executor.place_order.command.UpdateStockExecutor
     */
    public final static String UpdateStockExecutor = "UpdateStockExecutor";

    /**
     * @see com.example.orderservice.saga.executor.place_order.command.SaveOrderItemExecutor
     */
    public final static String SaveOrderItemExecutor = "SaveOrderItemExecutor";

    /**
     * @see com.example.orderservice.saga.executor.place_order.command.MakePaymentExecutor
     */
    public final static String MakePaymentExecutor = "MakePaymentExecutor";

    /**
     * @see com.example.orderservice.saga.executor.place_order.command.UpdatePaymentStatusExecutor
     */
    public final static String UpdatePaymentStatusExecutor = "UpdatePaymentStatusExecutor";

    /**
     * @see com.example.orderservice.saga.executor.place_order.command.DispatchOrderExecutor
     */
    public final static String DispatchOrderExecutor = "DispatchOrderExecutor";

    /**
     * @see com.example.orderservice.saga.executor.place_order.command.UpdateDeliveryStatusExecutor
     */
    public final static String UpdateDeliveryStatusExecutor = "UpdateDeliveryStatusExecutor";

    /**
     * @see com.example.orderservice.saga.executor.place_order.command.IncreasePointExecutor
     */
    public final static String IncreasePointExecutor = "IncreasePointExecutor";

}
