package com.basara.web;

import com.basara.pojo.Order;
import com.basara.pojo.User;
import com.basara.service.OrderService;
import com.basara.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author com.basara
 * @create 2022-11-13 6:05
 */
public class OrderServlet extends BaseServlet{

    OrderService orderService = new OrderServiceImpl();

    //查看全部订单
    protected void showOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如果还没有登录，就重定向去登录
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }
        List<Order> orders = orderService.showAllOrders();
        req.setAttribute("order", orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    //发货
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.sendOrder(orderId);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
