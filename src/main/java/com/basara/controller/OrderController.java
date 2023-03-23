package com.basara.controller;

import com.basara.mapper.OrderMapper;
import com.basara.pojo.Order;
import com.basara.pojo.User;
import com.basara.service.OrderService;
import com.basara.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author com.basara
 * @create 2022-11-13 6:05
 */
@Controller
@RequestMapping("manager")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //查看全部订单
    @RequestMapping(value = "order/all")
    protected String showOrders(HttpServletRequest req) {
        //如果还没有登录，就重定向去登录
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Order> orders = orderService.showAllOrders();
        req.setAttribute("orders", orders);
        return "manager/order_manager";
    }

    //发货
    @RequestMapping(value="order/send/{orderId}")
    protected String sendOrder(@PathVariable("orderId") String orderId, HttpServletRequest req) {
        orderService.sendOrder(orderId);
        return "redirect:" + req.getHeader("Referer");
    }
}
