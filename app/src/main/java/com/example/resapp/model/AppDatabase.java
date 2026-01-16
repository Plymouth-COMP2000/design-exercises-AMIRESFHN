package com.example.resapp.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MenuItemEntity.class, ReservationEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MenuItemDao menuItemDao();
    public abstract ReservationDao reservationDao();
}
