package com.example.resapp.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MenuItemEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MenuItemDao menuItemDao();
}
