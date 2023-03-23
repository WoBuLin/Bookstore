package com.basara.web;

import com.basara.pojo.*;
import com.basara.service.OrderService;
import com.basara.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author com.basara
 * @create 2022-11-13 2:59
 */
public class ClientOrderServlet extends BaseServlet {

    OrderService orderService = new OrderServiceImpl();

    //创建订单
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        //如果没有登录就让用户去登录
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }
        Integer userId = user.getId();
        Order order = orderService.createOrder(userId, cart);
        req.getSession().setAttribute("order", order);

        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    //查看订单详情
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = (String) req.getParameter("orderId");
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        req.setAttribute("orderItem",orderItems);
        req.getRequestDispatcher("/pages/order/orderItems.jsp").forward(req,resp);
    }
    //查看我的订单
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        //如果没有登录就让用户去登录
        if (user == null){
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }
        Integer id = user.getId();
        List<Order> orders = orderService.showMyOrders(id);
        req.setAttribute("order",orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);

    }
    //确认收货
    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.receiverOrder(orderId);
        resp.sendRedirect(req.getHeader("Referer"));

    }

}
