package com.synerzip.assignment.data.local;


import android.content.Context;

import androidx.databinding.adapters.Converters;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.synerzip.assignment.data.dao.WheatherDataDao;
import com.synerzip.assignment.model.LocalWeatherDataModel;


@Database(entities = LocalWeatherDataModel.class, version = 1, exportSchema = false)
public abstract class WeatherDataRoomDatabase extends RoomDatabase {

    public abstract WheatherDataDao wheatherDataDao();

    private static WeatherDataRoomDatabase instance;

    public static WeatherDataRoomDatabase getDatabase(Context context) {
        if (instance == null) {
            synchronized (WeatherDataRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            WeatherDataRoomDatabase.class, "weather_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }
}