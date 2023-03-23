package com.basara.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author com.basara
 * @create 2022-11-05 2:37
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理响应中文乱码
        resp.setContentType("text/html;charset=UTF-8");
        //处理请求的中文乱码
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        try {
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            //记住new是谁this就是谁
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            //这里也要抛出异常不然rollback时捕获不到
            throw new RuntimeException(e);
        }
    }
}
