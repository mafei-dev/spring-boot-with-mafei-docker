package com.example.orderservice.mapper;

import com.example.orderservice.model.OrderStatusModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderStatusMapper {

    /**
     * save status
     *
     * @param orderStatusModel
     */
    void save(@Param("orderStatusModel") OrderStatusModel orderStatusModel);

    /**
     * get status-list by orderId
     *
     * @param orderId
     * @return list of item for the given orderId
     */
    List<OrderStatusModel> getStatusListByOrderId(@Param("orderId") String orderId);

}
