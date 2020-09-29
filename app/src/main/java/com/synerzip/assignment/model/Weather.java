package com.synerzip.assignment.model;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

public class Weather {

    private String main;
 private String description;

    private String icon;

    public Weather(String main, String description, String icon) {
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        String url;
        if(icon.contains("https"))
        {
             url = icon;
        }
        else {
             url = "https://openweathermap.org/img/wn/"+icon+"@4x.png";
        }

        return url;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
