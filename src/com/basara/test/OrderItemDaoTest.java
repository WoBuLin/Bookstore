package com.basara.test;

import com.basara.dao.OrderItemDao;
import com.basara.dao.impl.OrderItemDaoImpl;
import com.basara.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author com.basara
 * @create 2022-11-13 4:58
 */
public class OrderItemDaoTest {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        OrderItem orderItem = new OrderItem(1, "java入门", 1, new BigDecimal(32), new BigDecimal(32), "16682811701511");
        orderItemDao.saveOrderItem(orderItem);
    }

    @Test
    public void queryOrderItemByOrderId() {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId("16682811701511");
        System.out.println(orderItems);
    }
}