package com.basara.controller;

import com.basara.pojo.User;
import com.basara.service.UserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author basara
 * @create 2022-12-14 8:45
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String userLogin(String username, String password, Model model, HttpSession session) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User loginUser = userService.login(user);
        if (loginUser == null) {
            model.addAttribute("username", username);
            model.addAttribute("error", "用户名或密码错误！");
            return "user/login";
        }
        session.setAttribute("user", loginUser);
        return "user/login_success";
    }


    @RequestMapping(value = "/user/regist", method = RequestMethod.POST)
    public String userRegist(User user, String code, HttpSession session, Model model) {
        //1、取出session域中的验证码
        String token = (String) session.getAttribute("kaptcha");
        //2、取出后就删除session域中的验证码。防止表单重复提交
        session.removeAttribute("kaptcha");
        //3、检查验证码是否正确
        if (code.equals(token)) {
//            //3、检查用户名是否可用
            //这一步已经使用ajax实现了，这里就没必要再验证了
            String username = user.getUsername();
            if (userService.existsUsername(username)) {
                // 不可用就添加请求域回显的数据并转发回注册页面
                model.addAttribute("user", user);
                model.addAttribute("error", "用户名不可用");
                return "user/regist";//跳转回注册页面
            } else { //可用就调用Service保存到数据库
                userService.registUser(user);
                //把用户信息放到session域
                session.setAttribute("user", user);
                return "redirect:/regist/success";
            }
        } else {
            //验证码不对就添加回显数据并转发回注册页面
            model.addAttribute("error", "验证码输入错误!");
            return "user/regist";
        }

    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public String userLogout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @RequestMapping(value = "/ajaxExistsUsername", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> ajaxExistsUsername(HttpServletResponse response, String username) {
        HashMap<String, Object> msg = new HashMap();
        if (userService.existsUsername(username)) {
            msg.put("errorMsg", "用户名已存在！");
        } else {
            msg.put("errorMsg", "用户名可用");
        }
        return msg;
    }
}