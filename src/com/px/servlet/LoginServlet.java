package com.px.servlet;

import com.px.bean.Book;
import com.px.bean.Car;
import com.px.bean.Msg;
import com.px.bean.User;
import com.px.dao.LoginDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private Msg msg = new Msg();
    private LoginDao loginDao = LoginDao.getInstance();
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

//        如果info为0则直接进入login.jsp
        if ("0".equals(info)){
            req.getRequestDispatcher("/WEB-INF/static/page/login.jsp").forward(req,resp);
            return;
        }


        String name = req.getParameter("name");
        String password = req.getParameter("password");


        boolean flag = true;
        if (name==null ||name.trim().equals("")){
            msg.add("name","用户名为空");
            flag=false;
        }
        if (password==null || name.trim().equals("")){
            msg.add("password","密码为空");
            flag=false;
        }

        if (!flag){
            req.setAttribute("msg",msg.getMap());
            req.getRequestDispatcher("/WEB-INF/static/page/login.jsp").forward(req,resp);
            return;
        }

        User user = loginDao.check(name, password);
        if (user==null){
            msg.add("name","账号密码不正确");
            req.setAttribute("msg",msg.getMap());
            req.getRequestDispatcher("/WEB-INF/static/page/login.jsp").forward(req,resp);
            return;
        }
        //登陆成功后创建user用户和购物车
        HttpSession session = req.getSession();

        session.setAttribute("user",user);
        //购物车
        session.setAttribute("car",new HashMap<String, Car>());

        req.getRequestDispatcher("IndexServlet").forward(req,resp);
    }
}
