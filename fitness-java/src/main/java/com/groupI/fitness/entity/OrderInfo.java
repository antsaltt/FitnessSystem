package com.groupI.fitness.entity;

public class OrderInfo {
    private String name;
    private Integer number;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderInfo() {
    }

    public OrderInfo(String name, String size, Integer number, double price) {
        this.name = name;
        this.number = number;
        this.price = price;
    }
}
