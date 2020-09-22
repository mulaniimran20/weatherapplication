package com.synerzip.assignment.data;

import android.content.Context;

import javax.inject.Inject;

public class WheatherData {

    private int id;
    String mCityName;
    String mDate;
    String mSunriseTime;
    String mSunsetTime;
    String mWheatherConditions;
    String mWheatherIconUrl;
    String mTemprature;
    String  mMinimumTemprature;
    String  mMaximumTemprature;

    Context mContext;

    public static final String TABLE_NAME = "weather_data";

    public static final String COLUMN_ID = "weather_data_id";
    public static final String COLUMN_CITYNAME = "city_name";
    public static final String COLUMN_DATE = "entry_date";
    public static final String COLUMN_SUNRISE_TIME = "sunrise_time";
    public static final String COLUMN_SUNSET_TIME = "sunset_time";
    public static final String COLUMN_WEATHER_CONDITIONS = "weather_conditions";
    public static final String COLUMN_WEATHER_CONDITIONS_ICON = "wheather_conditions_icon_url";
    public static final String COLUMN_TEMPRATURE = "temprature";
    public static final String COLUMN_MINIMUM_TEMPRATURE = "min_temprature";
    public static final String COLUMN_MAXIMUM_TEMPRATURE = "max_temprature";






    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_CITYNAME + " TEXT,"
                    + COLUMN_DATE + " TEXT,"
                    + COLUMN_SUNRISE_TIME + " TEXT ,"
                    + COLUMN_SUNSET_TIME + " TEXT,"
                    + COLUMN_WEATHER_CONDITIONS + " TEXT,"
                    + COLUMN_WEATHER_CONDITIONS_ICON + " TEXT,"
                    + COLUMN_TEMPRATURE + " TEXT,"
                    + COLUMN_MINIMUM_TEMPRATURE + " TEXT,"
                    + COLUMN_MAXIMUM_TEMPRATURE + " TEXT"
                    + ")";


    public WheatherData(int id, String mCityName, String mDate, String mSunriseTime, String mSunsetTime, String mWheatherConditions, String mWheatherIconUrl, String mTemprature, String mMinimumTemprature, String mMaximumTemprature) {
        this.id = id;
        this.mCityName = mCityName;
        this.mDate = mDate;
        this.mSunriseTime = mSunriseTime;
        this.mSunsetTime = mSunsetTime;
        this.mWheatherConditions = mWheatherConditions;
        this.mWheatherIconUrl = mWheatherIconUrl;
        this.mTemprature = mTemprature;
        this.mMinimumTemprature = mMinimumTemprature;
        this.mMaximumTemprature = mMaximumTemprature;
    }



    public WheatherData(Context context, String cityName, String date, String sunriseTime, String sunsetTime, String wheatherConditions, String wheatherIconUrl, String temprature, String minimumTemprature, String maximumTemprature) {
        this.mContext = context;
        this.mCityName = cityName;
        this.mDate = date;
        this.mSunriseTime = sunriseTime;
        this.mSunsetTime = sunsetTime;
        this.mWheatherConditions = wheatherConditions;
        this.mWheatherIconUrl = wheatherIconUrl;
        this.mTemprature = temprature;
        this.mMinimumTemprature = minimumTemprature;
        this.mMaximumTemprature = maximumTemprature;
    }

    @Inject
    public WheatherData() {

    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        this.mCityName = cityName;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getSunriseTime() {
        return mSunriseTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.mSunriseTime = sunriseTime;
    }

    public String getSunsetTime() {
        return mSunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.mSunsetTime = sunsetTime;
    }

    public String getWheatherConditions() {
        return mWheatherConditions;
    }

    public void setWheatherConditions(String wheatherConditions) {
        this.mWheatherConditions = wheatherConditions;
    }

    public String getWheatherIconUrl() {
        return mWheatherIconUrl;
    }

    public void setWheatherIconUrl(String wheatherIconUrl) {
        this.mWheatherIconUrl = wheatherIconUrl;
    }

    public String getTemprature() {
        return mTemprature;
    }

    public void setTemprature(String temprature) {
        this.mTemprature = temprature;
    }

    public String getMinimumTemprature() {
        return mMinimumTemprature;
    }

    public void setMinimumTemprature(String minimumTemprature) {
        this.mMinimumTemprature = minimumTemprature;
    }

    public String getMaximumTemprature() {
        return mMaximumTemprature;
    }

    public void setMaximumTemprature(String maximumTemprature) {
        this.mMaximumTemprature = maximumTemprature;
    }



}
