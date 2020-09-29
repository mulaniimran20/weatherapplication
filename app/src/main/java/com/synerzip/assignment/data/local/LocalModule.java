package com.synerzip.assignment.data.local;

import android.content.Context;

import com.synerzip.assignment.data.dao.WheatherDataDao;
import com.synerzip.assignment.model.LocalWeatherDataModel;


import java.util.List;

import io.reactivex.Single;

public class LocalModule implements WheatherDataDao {

    WeatherDataRoomDatabase database;

    public LocalModule(WeatherDataRoomDatabase database) {
        this.database = database;
    }

    @Override
    public List<LocalWeatherDataModel> getAll(String dateTodat) {
        return database.wheatherDataDao().getAll(dateTodat);
    }

    @Override
    public List<LocalWeatherDataModel> findByCityName(String cityName, String dateToday) {
        System.out.println("DB Test ="+cityName);
        System.out.println("DB Test = "+dateToday);
        return database.wheatherDataDao().findByCityName(cityName, dateToday);
    }

    @Override
    public void insertAll(LocalWeatherDataModel... weather_data) {
        database.wheatherDataDao().insertAll(weather_data);
    }

    @Override
    public void delete(String dateToday) {
        database.wheatherDataDao().delete(dateToday);
    }
}