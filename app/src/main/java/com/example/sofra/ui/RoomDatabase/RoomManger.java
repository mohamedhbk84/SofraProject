package com.example.sofra.ui.RoomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.sofra.data.model.itemFood.ItemFoodData;

@Database(entities = {ItemFoodData.class}, version = 1, exportSchema = false)
@TypeConverters({DataTypeConvert.class})
public abstract class RoomManger extends RoomDatabase {
    private static RoomManger roomManger = null;

    public abstract RoomDao roomDao();

    public static synchronized RoomManger getInstance(Context context) {
        if (roomManger == null) {

            roomManger = Room.databaseBuilder(context.getApplicationContext(), RoomManger.class,
                    "Sofra_Database_v2").fallbackToDestructiveMigration().build();

        }

        return roomManger;
    }
}
