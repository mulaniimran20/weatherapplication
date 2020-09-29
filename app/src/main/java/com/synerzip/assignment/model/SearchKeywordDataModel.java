package com.synerzip.assignment.model;

public class SearchKeywordDataModel {

   public String mCityName;
   public static String searchKeyword;

    public SearchKeywordDataModel() {
    }

    public SearchKeywordDataModel(String cityName) {
        this.mCityName = cityName;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        this.mCityName = cityName;
    }
}
