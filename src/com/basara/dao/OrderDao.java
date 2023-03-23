package com.basara.dao;

import com.basara.pojo.Order;

import java.util.List;

/**
 * @author com.basara
 * @create 2022-11-13 2:04
 */
public interface OrderDao {

    /**
     * 保存订单
     * @param order
     * @return
     */
    int saveOrder(Order order);

    /**
     * 查询全部订单
     * @return
     */
    List<Order> queryOrders();

    /**
     * 查询某个id的全部订单
     * @return
     */
    List<Order> queryOrdersByUserId(Integer userId);

    /**
     * 修改订单状态
     * @return
     */
    int changeOrderStatus(Integer status,String orderId);


}
