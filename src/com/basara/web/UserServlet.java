package com.basara.web;

import com.basara.pojo.User;
import com.basara.service.UserService;
import com.basara.service.impl.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author com.basara
 * @create 2022-11-05 2:56
 */
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();


    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2、调用service检查用户名与密码是否匹配
        User loginUser = userService.login(new User(null, username, password, null));
        if (loginUser == null) {
            System.out.println("用户名或密码错误");
            //3、不匹配就跳转回原登录页面
            //回显username和报错，设置到域中在jsp页面调用
            req.setAttribute("username", username);
            req.setAttribute("error", "用户名或密码错误！");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            //4、匹配就登录成功，跳转到登录成功页面
            //保存用户登录信息到session域中
            req.getSession().setAttribute("user",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        req.setAttribute("username",username);
        req.setAttribute("email",email);
        //2、检查验证码是否正确
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        if (code.equals(token)){
            //3、检查用户名是否可用
            if(userService.existsUsername(username)){
                System.out.println("用户名"+ username + "已存在");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);//跳转回注册页面
            } else { //可用就调用Service保存到数据库
                userService.registUser(new User(null,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            System.out.println("验证码输入错误");
            req.setAttribute("error","验证码输入错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、销毁Session中用户登录的信息（或者销毁Session）
        req.getSession().invalidate();
        //2、重定向到首页（或登录页面）。
        resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("测试用户名");
        String username = req.getParameter("username");
        HashMap<String,Object> msg = new HashMap();
        if(userService.existsUsername(username)){
            msg.put("errorMsg","用户名已存在！");
        } else {
            msg.put("errorMsg","用户名可用");
        }
        Gson gson = new Gson();
        String json = gson.toJson(msg);
        resp.getWriter().write(json);
    }
}
