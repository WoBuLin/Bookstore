package com.basara.test;

import com.basara.dao.impl.UserDaoImpl;
import com.basara.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author com.basara
 * @create 2022-11-02 3:14
 */
public class UserDaoImplTest {
    UserDaoImpl userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {

        User admin = userDao.queryUserByUsername("admin");
        System.out.println(admin);
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        User user = userDao.queryUserByUsernameAndPassword("admin", "admin");
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        User user = new User(null, "com/basara","123","com.basara@qq.com");
        System.out.println(userDao.saveUser(user));
    }
}