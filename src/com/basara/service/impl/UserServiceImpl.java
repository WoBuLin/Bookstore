package com.basara.service.impl;

import com.basara.dao.UserDao;
import com.basara.dao.impl.UserDaoImpl;
import com.basara.pojo.User;
import com.basara.service.UserService;

/**
 * @author com.basara
 * @create 2022-11-02 3:57
 */
public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();


    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        User user = userDao.queryUserByUsername(username);
        if (user == null){
            return false;
        }
        return true;
    }
}
