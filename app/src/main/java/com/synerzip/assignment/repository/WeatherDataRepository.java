package com.synerzip.assignment.repository;


import com.synerzip.assignment.data.local.LocalModule;
import com.synerzip.assignment.data.local.WeatherDataRoomDatabase;
import com.synerzip.assignment.model.WeatherDataModel;
import com.synerzip.assignment.remote.WeatherDataService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class WeatherDataRepository {

    private WeatherDataService weatherDataService;

    @Inject
    public WeatherDataRepository(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }


    public Single<WeatherDataModel> weatherDataModelSingle(String cityName) {
        return weatherDataService.getWeatherDataModel(cityName, "metric", "8350ce00fd241edebaac8403c3e389d8");
    }









}
