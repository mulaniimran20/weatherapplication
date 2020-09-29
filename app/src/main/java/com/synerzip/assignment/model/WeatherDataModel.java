package com.synerzip.assignment.model;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;


public class WeatherDataModel {


    private String name;


    private Coord coord;

    private Main main;

    private int visibility;

    private long dt;

    private Sys sys;

    private List<Weather> weather;

    String mDate;
    String patternDate = "dd/MM/yyyy";

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternDate);


    public WeatherDataModel(String name, Coord coord, Main main, int visibility, long dt, Sys sys, List<Weather> weather) {
        this.name = name;
        this.coord = coord;
        this.main = main;
        this.visibility = visibility;
        this.dt = dt;
        this.sys = sys;
        this.weather = weather;
    }


    @Inject
    public WeatherDataModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getDt() {
        Date d = new Date(TimeUnit.SECONDS.toMillis(dt));
        mDate = simpleDateFormat.format(d);
        return mDate;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }






}
