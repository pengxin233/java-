package com.px.dao;

import com.px.utils.Utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jdbc {
    private Connection conn;
    private static Jdbc instance = new Jdbc();

    public static Jdbc getInstance(){
        return instance;
    }

    //增删改
    public int update(String sql,Object...args) {
        PreparedStatement ps = null;
        try {
            conn = Utils.getConnection();
            ps = conn.prepareStatement(sql);
            //填充占位符
            for (int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Utils.resourseCloss(conn,ps);
        }
        return 0;
    }

    //查询所有
    public <T>List<T> queryAll(Class<T> clazz,String sql,Object...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            ps = conn.prepareStatement(sql);
            //填充占位符
            for (int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //得到结果集
            rs = ps.executeQuery();
            //得到元数据
            ResultSetMetaData metaData = rs.getMetaData();
            //得到列数
            int columnCount = metaData.getColumnCount();

            List<T> res = new ArrayList<>();
            //得到数据
            while (rs.next()){
                //得到实例对象
                T t = clazz.newInstance();
                //得到数据封装到T
                for (int i=0;i<columnCount;i++){
                    //暴力赋值
                    //得到别名
                    String label = metaData.getColumnLabel(i + 1);
                    //得到属性
                    Field field = clazz.getDeclaredField(label);
                    //设置可以暴力赋值
                    field.setAccessible(true);
                    field.set(t,rs.getObject(i+1));
                }
                res.add(t);
            }
            return res;

        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            Utils.resourseCloss(conn,ps,rs);
        }
        return null;
    }

    //通过名字查询
    public <T> T queryByName(Class<T> clazz, String sql, Object...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Utils.getConnection();
            ps = conn.prepareStatement(sql);
            //填充占位符
            for (int i=0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //得到结果集
            rs = ps.executeQuery();
            //得到元数据
            ResultSetMetaData metaData = rs.getMetaData();
            //得到列数
            int columnCount = metaData.getColumnCount();

            if (rs.next()){
                //得到实例对象
                T t = clazz.newInstance();
                //得到数据封装到T
                for (int i=0;i<columnCount;i++){
                    //暴力赋值
                    //得到别名
                    String label = metaData.getColumnLabel(i + 1);
                    //得到属性
                    Field field = clazz.getDeclaredField(label);
                    //设置可以暴力赋值
                    field.setAccessible(true);
                    field.set(t,rs.getObject(i+1));
                }
                return t;
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            Utils.resourseCloss(conn,ps,rs);
        }
        return null;
    }
}
