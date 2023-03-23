package com.basara.service.impl;

import com.basara.mapper.UserMapper;
import com.basara.pojo.User;
import com.basara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author com.basara
 * @create 2022-11-02 3:57
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public void registUser(User user) {
        userMapper.saveUser(user);
    }

    public User login(User user) {
        return userMapper.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    public boolean existsUsername(String username) {
        User user = userMapper.queryUserByUsername(username);
        if (user == null){
            return false;
        }
        return true;
    }
}
