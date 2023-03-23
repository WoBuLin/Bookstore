package com.basara.service;

import com.basara.pojo.Cart;
import com.basara.pojo.Order;
import com.basara.pojo.OrderItem;

import java.util.List;

/**
 * @author com.basara
 * @create 2022-11-13 2:36
 */
public interface OrderService {

    /**
     * 创建订单
     * @return
     */
    Order createOrder(Integer userId, Cart cart);

    /**
     * 发货
     * @return
     */
    int sendOrder(String orderId);

    /**
     * 确认收货(用户使用）
     * @param orderId
     * @return
     */
    int receiverOrder(String orderId);

    /**
     * 根据用户id查询订单
     * @param userId
     * @return
     */
    List<Order> showMyOrders(Integer userId);

    /**
     * 查看全部订单(管理员用)
     * @return
     */
    List<Order> showAllOrders();

    /**
     * 查看订单详情
     * @param orderId
     * @return
     */
    List<OrderItem> showOrderDetail(String orderId);

    /**
     * 查询用户最后的一个订单
     * @param userId
     * @return
     */
    String showLastOrderId(Integer userId);
}
