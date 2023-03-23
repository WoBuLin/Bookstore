package com.basara.test;

import com.basara.pojo.User;
import com.basara.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author com.basara
 * @create 2022-11-02 4:08
 */
public class UserServiceImplTest {

    UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void registUser() {
        User user = new User(null, "kks", "123", "xx@qq.com");
        userService.registUser(user);
    }

    @Test
    public void login() {
        User user = new User(null, "kks", "33", "xx@qq.com");
        System.out.println(userService.login(user));
    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("com/basara"));
    }
}