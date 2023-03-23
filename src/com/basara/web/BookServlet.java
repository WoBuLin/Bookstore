package com.basara.web;

import com.basara.pojo.Book;
import com.basara.pojo.Page;
import com.basara.service.impl.BookServiceImpl;
import com.basara.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author com.basara
 * @create 2022-11-05 23:52
 */
public class BookServlet extends BaseServlet {
    BookServiceImpl bookService = new BookServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、调用service查询所有的商品信息
        List<Book> bookList = bookService.queryBooks();
        //2、将books添加到请求域中
        req.setAttribute("books", bookList);
        //3、请求转发到图书管理页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("add执行了");
        int pageNo = WebUtils.pareStrToInt(req.getParameter("pageNo"), 0);
        //获取请求参数以Map的形式返回
        Map<String, String[]> parameterMap = req.getParameterMap();
        //调用工具类，直接将map形式封装的请求参数封装成javaBean
        Book book = WebUtils.copyParamToBean(parameterMap, new Book());
        //调用service添加book到数据库中
        bookService.addBook(book);
        //添加完后返回商品列表页，并刷新展示刚添加的数据
        //记住要重定向到listServlet，再又Servlet请求转发到商品列表页面，而这里不能用请求转发，因为如果用请求转发到listServlet，那么每次刷新
        //都会再执行一次添加操作，因为是一次请求
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.pareStrToInt(req.getParameter("pageNo"), 1);
        System.out.println("delete执行了");
        //获取请求参数id
        String strId = req.getParameter("id");
        int id = WebUtils.pareStrToInt(strId, 0);
        //调用service的delete()方法
        bookService.deleteBook(id);
        //记住要重定向到listServlet，再又Servlet请求转发到商品列表页面，而这里不能用请求转发，因为如果用请求转发到listServlet，那么每次刷新
        //都会再执行一次添加操作，因为是一次请求
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("getBook执行了");
        //获取请求参数id
        String strId = req.getParameter("id");
        int id = WebUtils.pareStrToInt(strId, 0);
        //调用service的方法查询出这个商品信息
        Book book = bookService.queryBook(id);
        //将book添加到request域中
        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }


    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("update执行了");
        //以Map形式获取请求参数并注入javabean中
        Map<String, String[]> parameterMap = req.getParameterMap();
        Book book = WebUtils.copyParamToBean(parameterMap, new Book());
        //调用service的方法查进行商品的修改
        bookService.updateBook(book);
        //重定向回去商品展示页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取pageNo,如果是首次，就默认访问第一页
        int pageNo = WebUtils.pareStrToInt(req.getParameter("pageNo"), 1);
        //获取pageSize如果用户没传，就用默认的
        int pageSize = WebUtils.pareStrToInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用service返回page
        Page<Book> page = bookService.page(pageNo,pageSize);
        //设置url
        page.setUrl("manager/bookServlet?action=page");
        //将page放到域中
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
