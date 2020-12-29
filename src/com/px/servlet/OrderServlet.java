package com.px.servlet;

import com.px.bean.Car;
import com.px.bean.Order;
import com.px.bean.Order2;
import com.px.bean.User;
import com.px.dao.OrderDao;
import com.px.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServlet extends HttpServlet {
    private OrderDao orderDao = OrderDao.getInstance();
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

        //将购物车的数据添加到数据库

        String receiverAddress = req.getParameter("receiverAddress");
        String receiverName = req.getParameter("receiverName");
        String receiverPhone = req.getParameter("receiverPhone");

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        Map<String, Car> car = (HashMap<String, Car>)session.getAttribute("car");


        //得到大订单id
        String id = Utils.doOrderNum();

        BigDecimal money = new BigDecimal(0);
        List<Order2> order2List = new ArrayList<>();

        int i = 0;//小订单id

        //详细订单
        for (String bid : car.keySet()) {

            if (car.get(bid).getCount() == 0){
                continue;
            }

            Order2 order = new Order2();

            order.setId(id+i++);

            order.setB_id(Integer.parseInt(bid));

            order.setCount(car.get(bid).getCount());

            BigDecimal count = BigDecimal.valueOf(car.get(bid).getCount());
            BigDecimal price = count.multiply(car.get(bid).getBook().getPrice());
            order.setMoney(price);

            order.setO_id(id);

            //叠加总金额
            money = money.add(price);

            order2List.add(order);
        }

        //添加大订单
        Order a = new Order();

        a.setId(id);
        a.setU_id(user.getId());
        a.setMoney(money);
        a.setReceiverPhone(receiverPhone);
        a.setReceiverName(receiverName);
        a.setReceiverAddress(receiverAddress);
        a.setType("未付款");

        orderDao.addOrder(a);

        //添加小订单
        for (Order2 order2 : order2List) {
            orderDao.addOrder2(order2);
        }

        //订单添加完成后清空购物车
        session.setAttribute("car",new HashMap<String, Car>());

        req.getRequestDispatcher("ShowOrderServlet").forward(req,resp);
    }
}
