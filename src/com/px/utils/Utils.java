package com.px.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Random;
import java.util.Date;

public class Utils {
    private static ComboPooledDataSource dataSource;
    static {
        InputStream is = null;
        Properties properties = null;
        try {
            is = Utils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties = new Properties();
            properties.load(is);
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(properties.getProperty("driverClass"));
            dataSource.setJdbcUrl(properties.getProperty("url"));
            dataSource.setUser(properties.getProperty("user"));
            dataSource.setPassword(properties.getProperty("password"));
            dataSource.setInitialPoolSize(5);
            dataSource.setMaxPoolSize(15);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    //释放资源
    public static void resourseCloss(Connection connection, Statement statement){
        try {
            if (connection!=null){
                connection.close();
            }
            if (statement!=null){
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void resourseCloss(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection!=null){
                connection.close();
            }
            if (statement!=null){
                statement.close();
            }
            if (resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //生成订单号
    public static String doOrderNum() {
        Random random = new Random();
        SimpleDateFormat allTime = new SimpleDateFormat("YYYYMMddHHmmSSS");
        String subjectno = allTime.format(new Date())+random.nextInt(10);
        return subjectno+random.nextInt(10);
    }
}
