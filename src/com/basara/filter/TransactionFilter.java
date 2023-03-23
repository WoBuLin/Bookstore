package com.basara.filter;




import com.basara.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author com.basara
 * @create 2022-11-14 23:04
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            //执行servlet没问题就提交，有问题就回滚
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            //这里在把异常抛出给tomcat，下面讲错误页面要用到
            throw new RuntimeException(e);
        }

    }

    @Override
    public void destroy() {

    }
}
