package com.synerzip.assignment.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.synerzip.assignment.data.dao.WheatherDataDao;
import com.synerzip.assignment.model.LocalWeatherDataModel;


@Database(entities = {LocalWeatherDataModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WheatherDataDao wheatherDataDao();
}