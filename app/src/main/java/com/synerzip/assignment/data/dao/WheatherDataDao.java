package com.synerzip.assignment.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.synerzip.assignment.model.LocalWeatherDataModel;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface WheatherDataDao {

    @Query("SELECT * FROM weather_data WHERE entry_date NOT LIKE :dateToday")
    List<LocalWeatherDataModel> getAll(String dateToday);


    @Query("SELECT * FROM weather_data WHERE city_name LIKE :cityName AND " +
            "entry_date LIKE :dateToday LIMIT 1")
    List<LocalWeatherDataModel> findByCityName(String cityName, String dateToday);

    @Insert
    void insertAll(LocalWeatherDataModel... weather_data);


    @Query("DELETE FROM weather_data WHERE entry_date NOT LIKE :dateToday")
    void delete(String dateToday);


}
