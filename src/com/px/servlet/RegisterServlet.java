package com.px.servlet;

import com.px.bean.User;
import com.px.dao.RegisterDao;
import com.px.utils.RegisterFormBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    //得到注册对象
    RegisterDao register = RegisterDao.getInstance();

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

        //获取用户注册时表单提供的数据
        String info = req.getParameter("info");

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        String email = req.getParameter("email");

        //第一次访问
        if ("0".equals(info)){
            req.getRequestDispatcher("/WEB-INF/static/page/register.jsp").forward(req,resp);
            return;
        }


        // 将信息进行校验
        RegisterFormBean rfb = new RegisterFormBean(name,password,password2,email);

        boolean validate = rfb.validate();
        //判断填写是否符合,否则跳转到requister.jsp
        if(!validate){
            req.setAttribute("rfb",rfb);
            req.getRequestDispatcher("/WEB-INF/static/page/register.jsp").forward(req,resp);
            return;
        }
        //如果符合则封装到user
        User user = new User(name,password,email);
        boolean b = register.insertUser(user);

        if (!b){
            //添加失败，用户已经存在,或者邮箱已经存在,跳转回注册页面
            rfb.setErrorMsg("DBMes","该用户已经存在");
            req.setAttribute("rfb",rfb);
            req.getRequestDispatcher("/WEB-INF/static/page/register.jsp").forward(req,resp);
            return;
        }
        //跳转到登录界面
        resp.getWriter().println("恭喜你注册成功，3秒钟自动跳转。");
        resp.setHeader("refresh","3;url=LoginServlet?info=0");
    }
}
