package com.basara.dao;

import com.basara.pojo.User;

/**
 * @author com.basara
 * @create 2022-11-02 2:42
 */
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 如果返回null，说明没有这个用户
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param name
     * @param password
     * @return 返回null，说明用户名或密码错误
     */
    public User queryUserByUsernameAndPassword(String name,String password);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);
}
