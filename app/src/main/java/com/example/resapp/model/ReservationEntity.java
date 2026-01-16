package com.example.resapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reservations")

public class ReservationEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String customerName;
    public String date;
    public String time;

    public ReservationEntity(String customerName, String date, String time) {
        this.customerName = customerName;
        this.date = date;
        this.time = time;
    }

}
