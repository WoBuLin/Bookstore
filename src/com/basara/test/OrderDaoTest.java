package com.basara.test;

import com.basara.dao.impl.OrderDaoImpl;
import com.basara.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author com.basara
 * @create 2022-11-13 2:27
 */
public class OrderDaoTest {

    OrderDaoImpl orderDao = new OrderDaoImpl();

    @Test
    public void addOrder() {
//        Order order = new Order("123456789",new Date(1992),new BigDecimal(99),1,1);
//        Order order1 = new Order("1234567895",new Date(1992),new BigDecimal(99),1,1);
        Order order2 = new Order("23232323",new Date(),new BigDecimal(99),0,8);
        orderDao.saveOrder(order2);
    }

    @Test
    public void queryOrderList() {
        List<Order> orders = orderDao.queryOrders();
        System.out.println(orders);
    }

    @Test
    public void queryOrderListById() {
        System.out.println(orderDao.queryOrdersByUserId(5));
    }

    @Test
    public void updateOrder() {
        orderDao.changeOrderStatus(3,"12345678339");

    }
}