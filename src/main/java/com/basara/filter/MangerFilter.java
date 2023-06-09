package com.basara.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author com.basara
 * @create 2022-11-14 23:16
 */
public class MangerFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //如果没有登录就跳转到登录页面
        if (request.getSession().getAttribute("user") == null) {
            request.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
        } else {
            //继续执行请求
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {

    }
}
