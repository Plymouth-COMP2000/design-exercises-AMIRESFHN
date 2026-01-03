package com.example.resapp.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;
import java.util.List;

@Dao
public interface MenuItemDao {
    @Query("SELECT * FROM menu_items")
    List<MenuItemEntity> getAll();

    @Insert
    void insert(MenuItemEntity... items);

    @Update
    void update(MenuItemEntity... items);

    @Delete
    void delete(MenuItemEntity item);

    @Query("DELETE FROM menu_items")
    void deleteAll();
}
