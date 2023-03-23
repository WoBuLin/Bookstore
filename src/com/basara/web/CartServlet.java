package com.basara.web;

import com.basara.pojo.Book;
import com.basara.pojo.Cart;
import com.basara.pojo.CartItem;
import com.basara.service.BookService;
import com.basara.service.impl.BookServiceImpl;
import com.basara.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author com.basara
 * @create 2022-11-11 5:31
 */
public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    /**
     * 购物车的添加操作
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void AjaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取要添加的商品id
        int id = WebUtils.pareStrToInt(req.getParameter("id"), 0);
        //调用service查询出id的商品信息
        Book book = bookService.queryBook(id);
        //将商品转换成CartItem类
        CartItem cartItem = new CartItem(id, book.getName(), 1, book.getPrice(), book.getPrice());
        //将CartItem类加入到Cart中
        //如果Session域中还没有购物车就创建一个放进去
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println("添加成功");

        // 最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());

        //添加首页提示回显 商品总数 和最后添加购物车的商品名
        HashMap<String,Object> data = new HashMap<>();
        data.put("totalCount",cart.getTotalCount());
        data.put("lastName",book.getName());
        data.put("referer",req.getHeader("Referer"));
        Gson gson = new Gson();
        String json = gson.toJson(data);
        resp.getWriter().write(json);
    }

    /**
     * 添加到购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        int id = WebUtils.pareStrToInt(req.getParameter("id"), 0);
        // 调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBook(id);
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
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 删除购物车中的商品
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.pareStrToInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
        }
        //重定向回商品列表页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 修改商品的数量
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.pareStrToInt(req.getParameter("id"), 0);
        int count = WebUtils.pareStrToInt(req.getParameter("count"), 1);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateItem(id, count);
            //重定向回商品列表页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
        }
        //重定向回商品列表页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
