package com.basara.controller;

import com.basara.pojo.Book;
import com.basara.pojo.Page;
import com.basara.service.BookService;
import com.basara.utils.WebUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author basara
 * @create 2022-12-15 3:28
 */
@Controller
public class ClientBookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView clienIndex() {
        return new ModelAndView("forward:page?pageNum=1");
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String clientBookPage(Integer pageNum, HttpServletRequest request) {
        Page<Book> bookPage = bookService.getBookPage(pageNum);
        bookPage.setUrl("/page?pageNum=");
        request.setAttribute("page", bookPage);
        return "client/index";
    }

    @RequestMapping("/price")
    protected String pageByPrice(Integer pageNum, HttpServletRequest req) {
        //效验最大价格和最小价格范围
        int min = WebUtils.pareStrToInt(req.getParameter("min"), 0);
        int max = WebUtils.pareStrToInt(req.getParameter("max"), 9999);
        //调用service返回page
        Page<Book> page = bookService.pageByPrice(pageNum, min, max);
        //设置set前判断有没有min和max，有就追加到url中
        StringBuilder sb = new StringBuilder("/price");
        if (req.getParameter("min") != null){
            sb.append("?min=").append(min);
        }
        if (req.getParameter("max") != null){
            sb.append("&max=").append(max);
        }
        sb.append("&pageNum=");
        page.setUrl(sb.toString());
        req.setAttribute("page", page);
        req.setAttribute("min",min);
        req.setAttribute("max",max);
        return "client/index";
    }
}
