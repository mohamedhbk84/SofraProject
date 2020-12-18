package com.example.sofra.ui.RoomDatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.sofra.data.model.itemFood.ItemFoodData;

import java.util.List;

@Dao
public interface RoomDao {

    @Insert
    void add(ItemFoodData... itemFoodData);

    @Update
    void onUpdate(ItemFoodData... itemFoodData);
    @Query("update ItemFoodData set quantity = :q  where itemId = :id")
    void update(int id,int q);

    @Delete
    void onDelete(ItemFoodData... itemFoodData);
    @Query("DELETE FROM ItemFoodData ")
    void deleteAll();

    @Query("DELETE FROM ItemFoodData where itemId = :itemId")
    void onDelete(int itemId);

    @Query("Select * from ItemFoodData")
    List<ItemFoodData> getAll();
}
