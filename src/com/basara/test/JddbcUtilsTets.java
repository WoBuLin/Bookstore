package com.basara.test;

import com.basara.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author com.basara
 * @create 2022-11-02 1:57
 */
public class JddbcUtilsTets {
    @Test
    public void testJdbcUtils(){
        Connection connection = JdbcUtils.getConnection();
        System.out.println(connection);
//        JdbcUtils.close(connection);
    }
}
