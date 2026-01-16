package com.example.resapp.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface ReservationDao {
    @Insert
    void insert(ReservationEntity reservation);

    @Query("SELECT * FROM reservations")
    List<ReservationEntity> getAll();

    @Query("SELECT * FROM reservations WHERE customerName = :username")
    List<ReservationEntity> getReservationsForUser(String username);

    @Delete
    void delete(ReservationEntity reservation);

    @Query("DELETE FROM reservations")
    void deleteAll();

    @Query("SELECT * FROM reservations")
    List<ReservationEntity> getAllReservations();





}
