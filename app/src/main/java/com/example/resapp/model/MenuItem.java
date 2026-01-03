package com.example.resapp.model;

public class MenuItem {
    public String id;
    public String name;
    public double price;

    public MenuItem() {}

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
