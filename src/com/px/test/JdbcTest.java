package com.px.test;

import com.px.bean.*;
import com.px.dao.BookDao;
import com.px.dao.Jdbc;
import com.px.dao.OrderDao;
import com.px.dao.RegisterDao;
import com.px.utils.Utils;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

public class JdbcTest {
    Jdbc jdbc = Jdbc.getInstance();
    RegisterDao register = RegisterDao.getInstance();
    BookDao bookDao = BookDao.getInstance();
    OrderDao orderDao = OrderDao.getInstance();

    @Test
    public void test(){
        Connection c = Utils.getConnection();
        System.out.println(c);
    }

    @Test
    public void test02(){
        String sql = "select name from b_class";
        List<BClass> query = jdbc.queryAll(BClass.class, sql);
        System.out.println(query);
    }

    @Test
    public void test03(){
        String sql = "insert into user(name,password,email) values(?,?,?)";
        int b = jdbc.update(sql, "px", "qwerasd", "11111@px.com");
        System.out.println(b);
    }

    @Test
    public void test04(){

        User px = new User("qwe","qweqwe","qwe@qq.com");
        boolean b = register.insertUser(px);
        System.out.println(b);
    }

    @Test
    public void test05(){
        String sql = "select * from `order`";
        List<Order> orders = jdbc.queryAll(Order.class, sql);
        System.out.println(orders);
    }

    //测试查询和分类
//    @Test
//    public void test06(){
//        List<Book> books = bookDao.getBooks("之", "", "", low, height);
//        System.out.println(books);
//    }

    @Test
    public void test07(){
        String sql = "update user set name= root,password=asdsad,email=12321@qq.com where id="+2;
        int i = jdbc.update(sql);
        System.out.println(i);
    }

    @Test
    public void test08(){
        Order order = new Order("qweqew",2,new BigDecimal(30.22),"asd","asd","asd","aasd");
        orderDao.addOrder(order);
    }
//
//    @Test
//    public void test10(){
//        Order order = new Order("qweqew",2,2,new BigDecimal(30.22),"asd");
//        orderDao.addOrder2(order);
//    }

    @Test
    public void test09(){
        Random random = new Random();
        SimpleDateFormat allTime = new SimpleDateFormat("YYYYMMddHHmmSSS");
        String subjectno = allTime.format(new Date())+random.nextInt(10);
        System.out.println(subjectno+random.nextInt(10));
        System.out.println(random.nextInt(10));

        List a = new ArrayList();

        for (Object item : a) {

        }
    }

    @Test
    public void test11(){
        String sql = "select b.* ,sum(o2.count) c from `order` o1,`order2` o2,`book` b where o1.id = o2.o_id and o2.b_id = b.id and YEARWEEK(date_format(o1.checked,'%Y-%m-%d') - INTERVAL 1 DAY) = YEARWEEK(now() - INTERVAL 1 DAY) group by o2.b_id order by sum(o2.count) DESC limit 0,2;";
        List<Book> books = jdbc.queryAll(Book.class, sql);
        System.out.println(books);
    }

    @Test
    public void test12(){
        String sql = "select b.* from `order` o1,`order2` o2,`book` b where o1.id = o2.o_id and o2.b_id = b.id and YEARWEEK(date_format(o1.checked,'%Y-%m-%d') - INTERVAL 1 DAY) = YEARWEEK(now() - INTERVAL 1 DAY) group by o2.b_id limit 0,2";
        List<Book> books = jdbc.queryAll(Book.class, sql);
        System.out.println(books);

        List<Long> longs = new ArrayList<>();
        longs.set()
    }
}
