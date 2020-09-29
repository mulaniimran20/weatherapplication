package com.synerzip.assignment.model;

import androidx.room.ColumnInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Sys {

    private String country;

    private long sunrise;

    private long sunset;

    private String mSunRiseTime;
    private String mSunSetTime;



    String pattern = "hh:mm:ss a";
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(pattern);





    public Sys(String country, long sunrise, long sunset) {
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSunrise() {
        System.out.println("Called Sunrise time display....");
        Date d = new Date(TimeUnit.SECONDS.toMillis(sunrise));
        mSunRiseTime = simpleTimeFormat.format(d);
        return mSunRiseTime;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }


    public Sys() {
    }

    public String getSunset() {
        Date d1 = new Date(TimeUnit.SECONDS.toMillis(sunset));
        mSunSetTime = simpleTimeFormat.format(d1);
        return mSunSetTime;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }



}
