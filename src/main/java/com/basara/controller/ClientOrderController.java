package com.basara.controller;

import com.basara.pojo.Cart;
import com.basara.pojo.Order;
import com.basara.pojo.OrderItem;
import com.basara.pojo.User;
import com.basara.service.OrderService;
import com.basara.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author basara
 * @create 2022-12-17 2:30
 */
@Controller
public class ClientOrderController {

    @Autowired
    private OrderService orderService;

    //创建订单
    @RequestMapping("order/create")
    protected String createOrder(HttpServletRequest req) {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        //如果没有登录就让用户去登录
        if (user == null) {
            return "redirect:/login";
        }
        Integer userId = user.getId();
        Order order = orderService.createOrder(userId, cart);
        return "redirect:/order/checkout";
    }

    //结算成功返回到成功页
    @RequestMapping("order/checkout")
    public String checkoutOrder(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();
        String orderId = orderService.showLastOrderId(userId);
        req.setAttribute("orderId", orderId);
        return "cart/checkout";
    }

    //查看订单详情
    @RequestMapping(value = "order/details/{orderId}", method = RequestMethod.GET)
    public String showOrderDetail(@PathVariable("orderId") String orderId,HttpServletRequest req){
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        req.setAttribute("orderItems", orderItems);
        return "order/orderItems";
    }

    //查看我的订单
    @RequestMapping("order/get")
    protected String showMyOrders(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        //如果没有登录就让用户去登录
        if (user == null) {
            return "redirect:/login";
        }
        Integer id = user.getId();
        List<Order> orders = orderService.showMyOrders(id);
        System.out.println(orders);
        req.setAttribute("orders", orders);
        return "order/order";
    }

    //确认收货
    @RequestMapping("order/confirm/{orderId}")
    protected String receiverOrder(@PathVariable("orderId") String orderId,HttpServletRequest req){
        orderService.receiverOrder(orderId);
        return "redirect:" + req.getHeader("Referer");

    }
}
