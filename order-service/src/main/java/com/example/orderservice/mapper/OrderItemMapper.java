package com.example.orderservice.mapper;

import com.example.orderservice.model.OrderItemModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    /**
     * save the items
     *
     * @param itemList
     */
    void saveAll(@Param("itemList") List<OrderItemModel> itemList);

    /**
     * get the items by orderId
     *
     * @param orderId
     * @return all the item for given order.
     */
    List<OrderItemModel> getItemsByOrderId(@Param("orderId") String orderId);
}
