package com.basara.test;

import com.basara.pojo.Cart;
import com.basara.pojo.Order;
import com.basara.service.OrderService;
import com.basara.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author com.basara
 * @create 2022-11-13 2:53
 */
public class OrderServiceTest {

    OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        orderService.createOrder(1,new Cart());
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("16682792888441");
    }

    @Test
    public void showMyOrders() {
        List<Order> orders = orderService.showMyOrders(1);
        System.out.println(orders);
    }

    @Test
    public void showAllOrders() {
        System.out.println(orderService.showAllOrders());
    }
}