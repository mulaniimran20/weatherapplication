package com.synerzip.assignment.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "weather_data")
public class LocalWeatherDataModel {



    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "city_name")
    private String name;

    @ColumnInfo(name = "visibility")
    private int visibility;

    @ColumnInfo(name = "entry_date")
    private String dt;

    @ColumnInfo(name = "temprature")
    private String temp;

    @ColumnInfo(name = "min_temprature")
    private String temp_min;

    @ColumnInfo(name = "max_temprature")
    private String temp_max;

    @ColumnInfo(name = "pressure")
    private String pressure;

    @ColumnInfo(name = "humidity")
    private  String humidity;



    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "sunrise")
    private String sunrise;

    @ColumnInfo(name = "sunset")
    private String sunset;


    @ColumnInfo(name = "weather_conditions")
    private String description;

    @ColumnInfo(name = "wheather_conditions_icon_url")
    private String icon;

    @ColumnInfo(name = "long")
    private double lon;

    @ColumnInfo(name = "lat")
    private double lat;



    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getUid() {
        return uid;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
