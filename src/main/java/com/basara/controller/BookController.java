package com.basara.controller;

import com.basara.pojo.Book;
import com.basara.pojo.Page;
import com.basara.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author basara
 * @create 2022-12-10 1:51
 */
@Controller
@RequestMapping("manager")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value="/book/page",method = RequestMethod.GET)
    public String getBookPage(Integer pageNum, HttpServletRequest request){
        Page<Book> bookPage = bookService.getBookPage(pageNum);
        String url = "/manager/book/page?pageNum=";
        bookPage.setUrl(url);
        int managerPageNum = bookPage.getPageNum();
        //把当前页放到session域中
        request.getSession().setAttribute("managerPageNum",managerPageNum);
        request.setAttribute("page",bookPage);
        return "manager/book_manager";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String addBook(Book book,HttpSession session) {
        //调用service添加book到数据库中
        bookService.addBook(book);
        Integer pages = bookService.getPages(4);
        //记住要重定向到listServlet，再由Servlet请求转发到商品列表页面。
        // 而这里不能用请求转发，因为如果用请求转发到listServlet，那么每次刷新都会再执行一次添加操作，因为是一次请求
        return "redirect:/manager/book/page?pageNum=" + pages;
    }

    @RequestMapping(value = "/book/{id}",method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable("id") Integer id,@RequestHeader("referer") String referer){
        bookService.deleteBook(id);
        return "redirect:" + referer;
    }

    @RequestMapping(value = "/get/book/{id}", method = RequestMethod.GET)
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        Book book = bookService.queryBookById(id);
        model.addAttribute("book", book);
        return "manager/book_edit";
    }


    @RequestMapping(value = "book", method = RequestMethod.PUT)
    public String updateBook(Book book,HttpSession session) {
        //调用service的方法查进行商品的修改
        bookService.updateBook(book);
        //获取跳转来时的pageNum
        Integer PageNum = (Integer) session.getAttribute("managerPageNum");
        //重定向回去商品展示页面
        return "redirect:/manager/book/page?pageNum=" + PageNum;
    }
}
