package com.basara.service.impl;

import com.basara.dao.BookDao;
import com.basara.dao.OrderDao;
import com.basara.dao.OrderItemDao;
import com.basara.dao.impl.BookDaoImpl;
import com.basara.dao.impl.OrderDaoImpl;
import com.basara.dao.impl.OrderItemDaoImpl;
import com.basara.pojo.*;
import com.basara.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author com.basara
 * @create 2022-11-13 2:39
 */
public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    @Override
    public Order createOrder(Integer userId, Cart cart) {
        String id = System.currentTimeMillis()+""+ userId;
        Order order = new Order(id,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);

//        int i = 1 / 0;
        //遍历当前购物车的商品，两个操作：
            //1、获取遍历的商品信息，创建并赋值给OrderItem类
            //2、遍历商品的，修改购买的商品的销售和库存
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            //获取每个商品组
            CartItem cartItem = entry.getValue();
            //1、获取遍历的商品信息，创建并赋值给OrderItem类
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),id);
            orderItemDao.saveOrderItem(orderItem);
            //2、遍历商品，修改购买的商品的销售和库存
            Integer itemId = cartItem.getId();
            Book book = bookDao.queryBookById(itemId);
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        //订单已经生成，清空购物车
        cart.clear();

        return order;
    }

    @Override
    //发货
    public int sendOrder(String orderId) {
        return orderDao.changeOrderStatus(1, orderId);
    }

    @Override
    //收货
    public int receiverOrder(String orderId) {
        return orderDao.changeOrderStatus(2, orderId);
    }

    @Override
    //查看我的订单
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    //查看所有订单
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    //查看详细信息
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }
}
