package com.basara.mapper;

import com.basara.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author com.basara
 * @create 2022-11-13 2:04
 */
public interface OrderMapper {

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
    int changeOrderStatus(@Param("status") Integer status,@Param("orderId") String orderId);


    /**
     * 根据用户ID查询用户最后一个订单
     * @param userId
     * @return
     */
    String queryLastOrderId(Integer userId);
}
