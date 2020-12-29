package com.px.bean;

import java.math.BigDecimal;

public class Book {
    private int id;
    private String name;
    private String auther;
    private BigDecimal price;
    private int classid;
    private BigDecimal c;

    public BigDecimal getC() {
        return c;
    }

    public void setC(BigDecimal c) {
        this.c = c;
    }

    public Book(int id, String name, String auther, BigDecimal price, int classid) {
        this.id = id;
        this.name = name;
        this.auther = auther;
        this.price = price;
        this.classid = classid;
    }

    public Book() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }


    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", auther='" + auther + '\'' +
                ", price=" + price +
                ", classid=" + classid +
                '}';
    }
}
