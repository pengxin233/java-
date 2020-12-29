package com.px.dao;

import com.px.bean.User;

import java.util.List;

public class RegisterDao {
    private static RegisterDao instance = new RegisterDao();
    private Jdbc jdbc = Jdbc.getInstance();

    //单例模式
    public static RegisterDao getInstance(){
        return instance;
    }

    //得到用户
    public List<User> getUser(String name,String email){
        String sql = "select * from user where name = ? or email= ?";
        return jdbc.queryAll(User.class, sql, name , email);
    }

    //判断是否存在该用户,如果不存在则加入
    public boolean insertUser(User user){
        if (user == null) {
            return false;
        }

        //如果用户已经存在该用户则返回false；
        String name = user.getName();
        String email = user.getEmail();
        if (!getUser(name,email).isEmpty()){
            return false;
        }

        //否则注册到数据库
        String sql = "insert into user(name,password,email) values(?,?,?)";
        jdbc.update(sql,user.getName(),user.getPassword(),user.getEmail());
        return true;
    }

}
