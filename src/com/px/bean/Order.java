package com.px.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String id;
    private int u_id;
    private BigDecimal money;
    private String receiverAddress;
    private String receiverName;
    private String receiverPhone;
    private Date checked;
    private String type;

    public Order(String id, int u_id, BigDecimal money, String receiverAddress, String receiverName, String receiverPhone, String type) {
        this.id = id;
        this.u_id = u_id;
        this.money = money;
        this.receiverAddress = receiverAddress;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.type = type;
    }

    public Order() {
    }


    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }


    public Date getChecked() {
        return checked;
    }

    public void setChecked(Date checked) {
        this.checked = checked;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", u_id=" + u_id +
                ", money=" + money +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", checked=" + checked +
                ", type='" + type + '\'' +
                '}';
    }
}
