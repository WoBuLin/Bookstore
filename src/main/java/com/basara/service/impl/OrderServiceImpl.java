package com.basara.service.impl;

import com.basara.mapper.BookMapper;
import com.basara.mapper.OrderItemMapper;
import com.basara.mapper.OrderMapper;
import com.basara.pojo.*;
import com.basara.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author com.basara
 * @create 2022-11-13 2:39
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BookMapper bookMapper;


    public Order createOrder(Integer userId, Cart cart) {
        String id = System.currentTimeMillis() + "" + userId;
        Order order = new Order(id, new Date(), cart.getTotalPrice(), 0, userId);
        //保存订单
        orderMapper.saveOrder(order);

//        int i = 1 / 0;//模拟出错回滚事务
        //遍历当前购物车的商品，两个操作：
        //1、获取遍历的商品信息，创建并赋值给OrderItem类
        //2、遍历商品的，修改购买的商品的销售和库存
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            //获取每个商品组
            CartItem cartItem = entry.getValue();
            //1、获取遍历的商品信息，创建并赋值给OrderItem类
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), id);
            orderItemMapper.saveOrderItem(orderItem);
            //2、遍历商品，修改购买的商品的销售和库存
            Integer itemId = cartItem.getId();
            Book book = bookMapper.queryBookById(itemId);
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookMapper.updateBook(book);
        }
        //订单已经生成，清空购物车
        cart.clear();

        return order;
    }

    //发货
    public int sendOrder(String orderId) {
        return orderMapper.changeOrderStatus(1, orderId);
    }

    //收货
    public int receiverOrder(String orderId) {
        return orderMapper.changeOrderStatus(2, orderId);
    }

    //查看我的订单
    public List<Order> showMyOrders(Integer userId) {
        return orderMapper.queryOrdersByUserId(userId);
    }

    //查看所有订单
    public List<Order> showAllOrders() {
        return orderMapper.queryOrders();
    }

    //查看详细信息
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemMapper.queryOrderItemByOrderId(orderId);
    }

    //查询用户最近的一个订单号
    public String showLastOrderId(Integer userId) {
        return orderMapper.queryLastOrderId(userId);
    }
}
