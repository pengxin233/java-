package com.px.servlet;

import com.px.bean.Msg;
import com.px.bean.User;
import com.px.dao.Jdbc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//判断用户的登录并判断跳转到订单或者登录
public class UserServlet extends HttpServlet {
    private Jdbc jdbc = Jdbc.getInstance();
    private Msg msg = new Msg();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决后台返回给前端的中文乱码
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        //解决从post返回的中文参数乱码
        req.setCharacterEncoding("utf-8");

        String info = req.getParameter("info");

        //用户已经登录，跳转到订单和用户信息
        if ("0".equals(info)){
            req.getRequestDispatcher("/WEB-INF/static/page/userInfo.jsp").forward(req,resp);
            return;
        }else if("1".equals(info)) {
            //用户没有登录则跳转去登录
            req.getRequestDispatcher("/WEB-INF/static/page/login.jsp").forward(req,resp);
            return;
        }else if ("2".equals(info)){
            //退出
            req.getSession().setAttribute("user",null);
            req.getSession().setAttribute("car",null);
            //退出后回到首页
            req.getRequestDispatcher("IndexServlet").forward(req,resp);
            return;
        }

        //修改个人信息
        //得到id
        HttpSession session = req.getSession();
        User user1 = (User) session.getAttribute("user");

        String id = user1.getId()+"";
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        System.out.println(id+name+password+email);

        boolean check = check(name, password, email, id);
        //如果校验失败

        if (!check){
            req.setAttribute("msg",msg.getMap());
            req.getRequestDispatcher("/WEB-INF/static/page/userInfo.jsp").forward(req,resp);
            return;
        }

        String sql = "update user set name= ?,password=?,email=? where id="+id;
        jdbc.update(sql, name, password, email);

        User user = new User(Integer.parseInt(id),name,password,email);
        session.setAttribute("user",user);
        req.setAttribute("success","修改成功");
        req.getRequestDispatcher("/WEB-INF/static/page/userInfo.jsp").forward(req,resp);
    }

    //得到用户
    public boolean getUser(String name, String email, String id){
        //查询所有姓名和email相同的用户
        String sql = "select * from user where (name = ? or email=?)and id != ?";
        List<User> list = jdbc.queryAll(User.class, sql, name,email,id);

        boolean flag = true;

        for (User user : list) {
            if (user.getName().equals(name)){
                msg.add("name","name已经存在");
                flag = false;
            }
            if (user.getEmail().equals(email)){
                msg.add("email","email已经存在");
                flag = false;
            }
        }
        return flag;

    }

    public boolean check(String name,String password,String email,String id){
        boolean flag = true;

        if (name == null || name.trim().equals("")) {
            msg.add("name", "请输入姓名.");
            flag = false;
        }

        if (password == null || password.trim().equals("")) {
            msg.add("password", "请输入密码.");
            flag = false;
        } else if (password.length() > 12 || password.length() < 6) {
            msg.add("password", "请输入6-12个字符.");
            flag = false;
        }

        // 对email格式的校验采用了正则表达式
        if (email == null || email.trim().equals("")) {
            msg.add("email", "请输入邮箱.");
            flag = false;
        } else if (!email.matches("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+")) {
            msg.add("email", "邮箱格式错误.");
            flag = false;
        }

        //判断姓名或者邮箱是否重复
        if (!getUser(name,email,id)){
            flag = false;
        }

        if (flag == false){
            return false;
        }
        return true;
    }
}
