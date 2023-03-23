package com.basara.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author com.basara
 * @create 2022-11-02 1:33
 */
public class JdbcUtils {

    private static DataSource dataSource;
    //用来确保是一个数据库连接
    public static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(is);
            dataSource = (DataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库连接池中的连接
     *
     * @return
     */
    public static Connection getConnection() {

        Connection connection = threadLocal.get();
        try {
            if (connection == null) {
                threadLocal.set(dataSource.getConnection());
                connection = threadLocal.get();
            }
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    /**
     * 提交事务和关闭连接
     */
    public static void commitAndClose() {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //将threadLocal绑定的数据删掉，因为tomcat底层是连接池的，如果不删掉，后续还能还会拿到这个连接就有可能出错
        threadLocal.remove();
    }

    /**
     * 回滚事务和关闭连接
     */
    public static void rollbackAndClose() {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //将threadLocal绑定的数据删掉，因为tomcat底层是连接池的，如果不删掉，后续还能还会拿到这个连接就有可能出错
        threadLocal.remove();
    }

}
