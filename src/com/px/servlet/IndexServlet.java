package com.px.servlet;

import com.px.bean.BClass;
import com.px.bean.Book;
import com.px.bean.Info;
import com.px.bean.Order;
import com.px.dao.BookDao;
import com.px.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends HttpServlet {
    private BookDao bookDao = BookDao.getInstance();

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

        //得到分类信息
        List<BClass> bClass = bookDao.getBClass();
        List<BClass> classes = bookDao.getBClass2();

        //得到热门商品
        List<Book> hot = bookDao.getHot();
        //得到公告板
        List<Info> info = bookDao.getInfo();

        req.getSession().setAttribute("hot",hot);
        req.getSession().setAttribute("info",info);

        //将分类数据传到前端
        req.setAttribute("classes",classes);
        req.setAttribute("bClasses",bClass);

        //得到参数
        String search = req.getParameter("search");

        String fenlei = req.getParameter("fenlei");
        //大分类
        String fenlei2 = req.getParameter("fenlei2");

        String low = req.getParameter("low");
        String height = req.getParameter("height");


        //得到在主页显示的商品
        List<Book> books = bookDao.getBooks(search, fenlei,fenlei2,low,height);

        req.setAttribute("books",books);

        req.getRequestDispatcher("/WEB-INF/static/page/index.jsp").forward(req,resp);
    }
}
