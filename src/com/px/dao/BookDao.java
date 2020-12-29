package com.px.dao;

import com.px.bean.BClass;
import com.px.bean.Book;
import com.px.bean.Info;

import java.util.List;

public class BookDao {
    private static Jdbc jdbc = Jdbc.getInstance();
    private static BookDao bookDao = new BookDao();

    private static List<BClass> bClasses;
    private static List<BClass> bClasses2;
    private static List<Book> books;

    public static BookDao getInstance(){
        return bookDao;
    }

    static {
        String sql = "select b1.`name` ,b2.`name` name2, b2.`id` from b_class b1,b_class2 b2 where b1.id = b2.c_id";
        bClasses = jdbc.queryAll(BClass.class, sql);

        String sql1 = "select * from b_class";
        bClasses2 = jdbc.queryAll(BClass.class,sql1);

        String sql2 = "select * from book";
        books = jdbc.queryAll(Book.class,sql2);
    }

    //查询所有的书大类和二类
    public List<BClass> getBClass(){
        return bClasses;
    }

    //查询大类
    public List<BClass> getBClass2(){
        return bClasses2;
    }

    //查询
    public List<Book> getBooks(String search, String fenlei, String fenlei2, String low, String height){
        String sql = null;
        //如果查询条件不存在则显示全部内容

        if (search != null && !search.trim().equals("")){
            //查询书名和作者
            sql = "select * from book where name like '%"+search+"%' or auther like '%"+search+"%'";
        }else if (fenlei != null && !fenlei.trim().equals("")){
            //查询大分类
            sql = "select * from book where classid="+fenlei;
        }else if (fenlei2 != null && !fenlei2.trim().equals("")){
            //查询小分类
            sql = "select * from book where classid in(select id from b_class2 where c_id = "+fenlei2+")";
        }else if (low!=null && !low.trim().equals("") || height!=null && !height.trim().equals("")){
            if (low!=null && !low.trim().equals("") && height!=null && !height.trim().equals("")){
                //最小值和最大值都存在
                sql = "select * from book where price >= "+low+" and price <= "+height+"";
            }else if (low!=null && !low.trim().equals("")){
                //只存在最小值
                sql = "select * from book where price >= "+low;
            }else if (height!=null && !height.trim().equals("")){
                //只存在最大值
                sql = "select * from book where price <= "+height;
            }
        }
        if (sql == null){
            return books;
        }
        List<Book> books1 = jdbc.queryAll(Book.class, sql);

        return books1;
    }

    //得到一本书
    public Book getBook(String id){
        String sql = "select * from book where id = ?";
        return jdbc.queryByName(Book.class,sql,id);
    }

    //查询本周热门商品
    public List<Book> getHot(){
        String sql = "select b.* ,sum(o2.count) c from `order` o1,`order2` o2,`book` b where o1.id = o2.o_id and o2.b_id = b.id and YEARWEEK(date_format(o1.checked,'%Y-%m-%d') - INTERVAL 1 DAY) = YEARWEEK(now() - INTERVAL 1 DAY) group by o2.b_id order by sum(o2.count) DESC limit 0,2;";
        return jdbc.queryAll(Book.class,sql);
    }

    //查询公告板

    public List<Info> getInfo(){
        String sql = "select * from info";
        return jdbc.queryAll(Info.class,sql);
    }

}
