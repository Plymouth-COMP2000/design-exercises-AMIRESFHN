package com.example.resapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "menu_items")
public class MenuItemEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public double price;

    public MenuItemEntity(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
