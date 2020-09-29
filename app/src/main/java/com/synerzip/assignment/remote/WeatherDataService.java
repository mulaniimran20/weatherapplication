package com.synerzip.assignment.remote;

import com.synerzip.assignment.model.WeatherDataModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherDataService {

    String mAppId = "8350ce00fd241edebaac8403c3e389d8";




    @GET("/data/2.5/weather")
    Single<WeatherDataModel> getWeatherDataModel(@Query("q") String searchString,
                                                     @Query("units") String unitsType,
                                                     @Query("appid") String appId
    );


}
