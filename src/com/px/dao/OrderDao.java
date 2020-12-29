package com.px.dao;

import com.px.bean.Order;
import com.px.bean.Order2;

import java.util.List;

public class OrderDao {
    private Jdbc jdbc = Jdbc.getInstance();
    private static OrderDao orderDao = new OrderDao();

    public static OrderDao getInstance(){
        return orderDao;
    }

    //添加详细订单
    public int addOrder2(Order2 order){
        String sql = "insert into `order2`(id,b_id,count,money,o_id)" +
                "values(?,?,?,?,?)";

        return jdbc.update(sql,order.getId(),order.getB_id(),order.getCount(),order.getMoney(),order.getO_id());
    }

    //添加大订单
    public int addOrder(Order order){
        String sql = "insert into `order`(id,u_id,money,checked,receiverAddress,receiverName,receiverPhone,type)" +
                "values(?,?,?,now(),?,?,?,?)";

        return jdbc.update(sql,order.getId(),order.getU_id(),order.getMoney(),order.getReceiverAddress(),order.getReceiverName(),order.getReceiverPhone(),order.getType());
    }


    //删除订单
    public int deleteOrder(String id){
        String sql = "delete from `order` where id = ?";
        String sql2 = "delete from `order2` where o_id = ?";

        int a = jdbc.update(sql, id);
        int b = jdbc.update(sql2, id);

        return a!=0 && b!=0 ? 1:0;

    }

    //查询订单
    public List<Order> selectOrder(int id, String search){
        String sql = "";

        if (search!= null && !search.trim().equals("")){
            sql = "select id,money,receiverAddress,receiverName,receiverPhone,checked,type from `order` where u_id = ?  and ( id = '"+search+"' or receiverAddress like '"+search+"' or receiverName like '"+search+"' or receiverPhone = '"+search+"') order by checked desc;";
        }else {
            sql = "select id,money,receiverAddress,receiverName,receiverPhone,checked,type from `order` where u_id = ? order by checked desc";
        }
        return jdbc.queryAll(Order.class,sql,id);
    }

    //查询详细订单
    public List<Order2> selectOrder2(String id){
        String sql = "select o.id,b.id id1,o.count,o.money from order2 o,book b where o_id = ? and o.b_id = b.id";
        return jdbc.queryAll(Order2.class,sql,id);
    }

    //购买
    public int payOrder(String id){
        String sql = "update `order` set type = '已付款' where id = ?";
        return jdbc.update(sql,id);
    }

}
