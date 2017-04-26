package com.kun.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by kun on 2017/4/21.
 */
public class JDBCUtil {
    /**
     * 获取connection
     * @return 获取到的connection
     */
    public static Connection getConnection() throws Exception{

        InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties=new Properties();
        properties.load(inputStream);

        String url=properties.getProperty("jdbc.url");
        String user=properties.getProperty("jdbc.user");
        String password=properties.getProperty("jdbc.password");
        String driverClass=properties.getProperty("jdbc.driverClass");

        Class.forName(driverClass);
        return DriverManager.getConnection(url,user,password);
    }

    /**
     * 释放关闭连接
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void release(ResultSet resultSet,
                               Statement statement,Connection connection){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
