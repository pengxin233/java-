package com.px.bean;

public class Info {
    private String head;
    private String p1;
    private String p2;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    @Override
    public String toString() {
        return "Info{" +
                "head='" + head + '\'' +
                ", p1='" + p1 + '\'' +
                ", p2='" + p2 + '\'' +
                '}';
    }
}
