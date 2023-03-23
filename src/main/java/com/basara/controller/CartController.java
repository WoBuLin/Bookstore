package com.basara.controller;

import com.basara.pojo.Book;
import com.basara.pojo.Cart;
import com.basara.pojo.CartItem;
import com.basara.service.BookService;
import com.basara.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author basara
 * @create 2022-12-16 1:18
 */
@Controller
public class CartController {

    @Autowired
    private BookService bookService;

    /**
     * 添加到购物车
     */
    @RequestMapping(value = "cart/{id}", method = RequestMethod.GET)
    protected String addItem(@PathVariable("id") Integer id,HttpServletRequest req){
        // 获取请求的参数 商品编号
        // 调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        // 最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        // 重定向回原来商品所在的地址页面
        return "redirect:" + req.getHeader("Referer");
    }

    /**
     * 删除购物车中的商品
     */
    @RequestMapping(value = "cart/delete", method = RequestMethod.GET)
    protected String deleteItem(Integer id,HttpServletRequest req) {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
        }
        //重定向回商品列表页面
        return "redirect:" + req.getHeader("referer");
    }

    /**
     * 修改商品的数量
     */
    @RequestMapping(value="cart/update")
    protected String updateItem(Integer id,Integer count,HttpServletRequest req){
        count = WebUtils.pareStrToInt(req.getParameter("count"), 1);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateItem(id, count);
        }
        return "redirect:" + req.getHeader("Referer");
    }


    /**
     * 清空购物车
     */
    @RequestMapping(value = "cart/clear")
    protected String clear(HttpServletRequest req) {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
        }
        //重定向回商品列表页面
        return "redirect:" + req.getHeader("referer");
    }
}

