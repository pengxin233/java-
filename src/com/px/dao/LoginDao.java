package com.px.dao;

import com.px.bean.Msg;
import com.px.bean.User;

public class LoginDao {
    private Jdbc jdbc = Jdbc.getInstance();
    private static LoginDao loginDao = new LoginDao();

    public static LoginDao getInstance(){
        return loginDao;
    }

    //查询用户的姓名
    public User getUser(String name){
        String sql = "select * from user where name=?";
        User user = jdbc.queryByName(User.class, sql, name);
        return user;
    }

    public User check(String name,String password){
        User user = getUser(name);
        if (user == null || !user.getPassword().equals(password)){
            return null;
        }
        return user;
    }
}
