package com.synerzip.assignment.model;

import androidx.room.ColumnInfo;

public class Main {

    private double temp;

    private double temp_min;
    private double temp_max;

    private String pressure;

    private  double humidity;



    public Main(double temp, double temp_min, double temp_max, String pressure, double humidity) {
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.pressure = pressure;
        this.humidity = humidity;
    }


    public String getTemp() {
        return temp+"";
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getTemp_min() {
        return temp_min+"";
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max+"";
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public String getPressure() {
        return pressure+"  mBar";
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String  getHumidity() {
        return humidity+"";
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
