package com.px.servlet;

import com.px.bean.Order;
import com.px.bean.Order2;
import com.px.bean.User;
import com.px.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowOrderServlet extends HttpServlet {
    private OrderDao orderDao = OrderDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决后台返回给前端的中文乱码
        resp.setContentType("text/html;charset" +
                "" +
                "" +
                "=utf-8");
        resp.setCharacterEncoding("utf-8");
        //解决从post返回的中文参数乱码
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();

        String info = req.getParameter("info");
        String id = req.getParameter("id");

        //删除订单
        if ("0".equals(info)){
            orderDao.deleteOrder(id);
        }else if ("1".equals(info)){
            //跳转到详情页
            List<Order2> order2s = orderDao.selectOrder2(id);
            req.setAttribute("order2s",order2s);
            req.getRequestDispatcher("/WEB-INF/static/page/showOrder2.jsp").forward(req,resp);
            return;
        }else if ("2".equals(info)){
            //购买
            orderDao.payOrder(id);
        }

        User user = (User)session.getAttribute("user");
        String search = req.getParameter("search");

        List<Order> orders = orderDao.selectOrder(user.getId(),search);
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/WEB-INF/static/page/showOrder.jsp").forward(req,resp);
    }
}
