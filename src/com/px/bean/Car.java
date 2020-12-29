package com.px.bean;

public class Car {
    private Book book;
    private int count;

    public Car() {
    }

    public Car(Book book, int count) {
        this.book = book;
        this.count = count;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Car{" +
                "book=" + book +
                ", count=" + count +
                '}';
    }
}
