package com.px.servlet;

import com.px.bean.Book;
import com.px.bean.Car;
import com.px.bean.User;
import com.px.dao.BookDao;
import com.px.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CarServlet extends HttpServlet {
    private OrderDao orderDao = OrderDao.getInstance();
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

        String info = req.getParameter("info");
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        Map<String,Car> car = (HashMap<String, Car>) session.getAttribute("car");

        //跳转到购物车界面
        if ("0".equals(info)){
            req.getRequestDispatcher("/WEB-INF/static/page/car.jsp").forward(req,resp);
        }
        //添加购物车操作
        else if ("1".equals(info)){
            String bid = req.getParameter("bid");
            //如果购物车为空
            if (car == null){
                car = new HashMap<>();
            }

            Book book = bookDao.getBook(bid);

            //如果购物车存在该商品，则value+1
            car.put(bid,new Car(book,car.getOrDefault(bid,new Car(book,0)).getCount()+1));

            req.getRequestDispatcher("IndexServlet").forward(req,resp);
        }else if ("2".equals(info)){
            //修改购物车类商品的数量
            String bid = req.getParameter("bid");
            String value = req.getParameter("value");

            int v = Integer.parseInt(value);

            if (v>=1){
                Car car1 = car.get(bid);
                car1.setCount(v);
            }
            req.getRequestDispatcher("/WEB-INF/static/page/car.jsp").forward(req,resp);
        }else if ("3".equals(info)){
            //清空购物车
            car.clear();
            req.getRequestDispatcher("/WEB-INF/static/page/car.jsp").forward(req,resp);
        }else if ("4".equals(info)){
            //删除某个商品
            String bid = req.getParameter("bid");
            car.remove(bid);
            req.getRequestDispatcher("/WEB-INF/static/page/car.jsp").forward(req,resp);
        }else if ("5".equals(info)){
            //跳转到结算页面
            if (car.isEmpty()){ 
                req.setAttribute("msg","购物车为空");
                req.getRequestDispatcher("/WEB-INF/static/page/car.jsp").forward(req,resp);
                return;
            }
            req.getRequestDispatcher("/WEB-INF/static/page/order.jsp").forward(req,resp);
        }
    }
}
