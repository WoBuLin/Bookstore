package com.basara.web;

import com.basara.pojo.Book;
import com.basara.pojo.Page;
import com.basara.service.impl.BookServiceImpl;
import com.basara.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author com.basara
 * @create 2022-11-09 3:19
 */
public class ClientBookServlet extends BaseServlet{

    BookServiceImpl bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取pageNo,如果是首次，就默认访问第一页
        int pageNo = WebUtils.pareStrToInt(req.getParameter("pageNo"), 1);
        //获取pageSize如果用户没传，就用默认的
        int pageSize = WebUtils.pareStrToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用service返回page
        Page<Book> page = bookService.page(pageNo,pageSize);
        //设置url
        page.setUrl("client/bookServlet?action=page");
        //将page放到域中
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("pageByPrice执行了");
        //获取pageNo,如果是首次，就默认访问第一页
        int pageNo = WebUtils.pareStrToInt(req.getParameter("pageNo"), 1);
        //获取pageSize如果用户没传，就用默认的
        int pageSize = WebUtils.pareStrToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.pareStrToInt(req.getParameter("min"), 0);
        int max = WebUtils.pareStrToInt(req.getParameter("max"), Integer.MAX_VALUE);
        //调用service返回page
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min, max);

        //设置set前判断有没有min和max，有就追加到url中
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        if (req.getParameter("min") != null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        //设置url
        page.setUrl(sb.toString());
        //将page放到域中
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
