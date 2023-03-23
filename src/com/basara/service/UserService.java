package com.basara.service;

import com.basara.pojo.User;

/**
 * @author com.basara
 * @create 2022-11-02 3:53
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录用户
     * @param user
     * @return 如果返回的null说明登录失败
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，不可用，返回false表示可以用
     */
    public boolean existsUsername(String username);
}