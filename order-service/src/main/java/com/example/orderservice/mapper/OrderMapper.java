package com.example.orderservice.mapper;

import com.example.orderservice.model.OrderModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface OrderMapper {
    //java implementation
    @Select("SELECT * FROM `order` WHERE `order`.order_id = #{order_id}")
    OrderModel findById(@Param("order_id") String orderId);

    //Xml implementation
    OrderModel findByIdByXml(@Param("order_id") String orderId);


    void save(@Param("orderModel") OrderModel orderModel);

    /**
     * update the transactionId for existing order record.
     *
     * @param orderId
     * @param transactionId
     */
    void updateTransaction(@Param("orderId") String orderId, @Param("transactionId") String transactionId);

    /**
     * update the deliveryId for existing record.
     *
     * @param orderId
     * @param deliveryId
     */
    void updateDeliveryId(@Param("orderId") String orderId, @Param("deliveryId") String deliveryId);

    /**
     * get order detail by the orderId
     *
     * @param orderId
     * @return {@link Optional<OrderModel>} return an empty one of the order is not existing at the database.
     */
    Optional<OrderModel> getOrderDetail(@Param("orderId") String orderId);
}
