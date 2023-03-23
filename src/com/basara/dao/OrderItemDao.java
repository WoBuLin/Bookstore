package com.basara.dao;

import com.basara.pojo.OrderItem;

import java.util.List;

/**
 * @author com.basara
 * @create 2022-11-13 4:48
 */
public interface OrderItemDao {

    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    int saveOrderItem(OrderItem orderItem);

    /**
     * 根据订单查询订单项
     * @param orderId
     * @return
     */
    List<OrderItem> queryOrderItemByOrderId(String orderId);

}
