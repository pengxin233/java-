package com.px.bean;

import java.math.BigDecimal;

public class Order2 {
    private String id;
    private String name;
    private int b_id;
    private int count;
    private BigDecimal money;
    private String o_id;
    private int id1;

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public Order2() {
    }

    public Order2(String id, String name, int b_id, int count, BigDecimal money, String o_id) {
        this.id = id;
        this.name = name;
        this.b_id = b_id;
        this.count = count;
        this.money = money;
        this.o_id = o_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    @Override
    public String toString() {
        return "Order2{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", b_id=" + b_id +
                ", count=" + count +
                ", money=" + money +
                ", o_id='" + o_id + '\'' +
                '}';
    }
}
