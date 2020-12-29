package com.px.bean;

public class BClass {
    private String name;
    private String name2;
    private int id;

    public BClass() {
    }

    public BClass(String name, String name2, int id) {
        this.name = name;
        this.name2 = name2;
        this.id = id;
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

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @Override
    public String toString() {
        return "BClass{" +
                "name='" + name + '\'' +
                ", name2='" + name2 + '\'' +
                ", id=" + id +
                '}';
    }
}
