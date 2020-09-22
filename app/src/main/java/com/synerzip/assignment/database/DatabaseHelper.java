package com.synerzip.assignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.synerzip.assignment.data.WheatherData;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_NAME = "weather_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WheatherData.CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WheatherData.TABLE_NAME);
        onCreate(db);
    }

    public long insertData(WheatherData wheatherData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(WheatherData.COLUMN_CITYNAME, wheatherData.getCityName().toUpperCase());
        values.put(WheatherData.COLUMN_DATE, wheatherData.getDate());
        values.put(WheatherData.COLUMN_SUNRISE_TIME, wheatherData.getSunriseTime());
        values.put(WheatherData.COLUMN_SUNSET_TIME, wheatherData.getSunsetTime());
        values.put(WheatherData.COLUMN_WEATHER_CONDITIONS, wheatherData.getWheatherConditions());
        values.put(WheatherData.COLUMN_WEATHER_CONDITIONS_ICON, wheatherData.getWheatherIconUrl());
        values.put(WheatherData.COLUMN_TEMPRATURE, wheatherData.getTemprature());
        values.put(WheatherData.COLUMN_MINIMUM_TEMPRATURE, wheatherData.getMinimumTemprature());
        values.put(WheatherData.COLUMN_MAXIMUM_TEMPRATURE, wheatherData.getMaximumTemprature());

        long id = db.insert(WheatherData.TABLE_NAME, null, values);


        db.close();


        return id;
    }

    public WheatherData getCityWiseWeatherData(String cityName, String currentDate) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        WheatherData wheatherData;

        String rawQuery = "select * from " +
                WheatherData.TABLE_NAME + " where " + WheatherData.COLUMN_CITYNAME + " = '"+ cityName.toUpperCase() +"' AND " + WheatherData.COLUMN_DATE + " =  '"+currentDate + "'";
        System.out.println(rawQuery);
        Cursor cursor = db.rawQuery(rawQuery, null);

        /*Cursor cursor = db.query(WheatherData.TABLE_NAME,
                new String[]{WheatherData.COLUMN_ID, WheatherData.COLUMN_CITYNAME, WheatherData.COLUMN_DATE, WheatherData.COLUMN_SUNRISE_TIME, WheatherData.COLUMN_SUNSET_TIME, WheatherData.COLUMN_WEATHER_CONDITIONS, WheatherData.COLUMN_WEATHER_CONDITIONS_ICON, WheatherData.COLUMN_TEMPRATURE, WheatherData.COLUMN_MINIMUM_TEMPRATURE, WheatherData.COLUMN_MAXIMUM_TEMPRATURE},
                WheatherData.COLUMN_CITYNAME + "=? AND "+WheatherData.COLUMN_DATE +" = "+currentDate,
                new String[]{cityName.toUpperCase()}, null, null, null, null);*/

        if (cursor != null && cursor.moveToFirst()) {
            // prepare note object
            wheatherData = new WheatherData(
                    cursor.getInt(cursor.getColumnIndex(WheatherData.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(WheatherData.COLUMN_CITYNAME)),
                    cursor.getString(cursor.getColumnIndex(WheatherData.COLUMN_DATE)),
                    cursor.getString(cursor.getColumnIndex(WheatherData.COLUMN_SUNRISE_TIME)),
                    cursor.getString(cursor.getColumnIndex(WheatherData.COLUMN_SUNSET_TIME)),
                    cursor.getString(cursor.getColumnIndex(WheatherData.COLUMN_WEATHER_CONDITIONS)),
                    cursor.getString(cursor.getColumnIndex(WheatherData.COLUMN_WEATHER_CONDITIONS_ICON)),
                    cursor.getString(cursor.getColumnIndex(WheatherData.COLUMN_TEMPRATURE)),
                    cursor.getString(cursor.getColumnIndex(WheatherData.COLUMN_MINIMUM_TEMPRATURE)),
                    cursor.getString(cursor.getColumnIndex(WheatherData.COLUMN_MAXIMUM_TEMPRATURE))
            );
        }
        else{
            wheatherData = new WheatherData();
        }
        // close the db connection
        cursor.close();

        return wheatherData;
    }

    public int getWeatherCount(String  currentDate) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(WheatherData.TABLE_NAME,
                new String[]{WheatherData.COLUMN_ID, WheatherData.COLUMN_CITYNAME, WheatherData.COLUMN_DATE, WheatherData.COLUMN_SUNRISE_TIME, WheatherData.COLUMN_SUNSET_TIME, WheatherData.COLUMN_WEATHER_CONDITIONS, WheatherData.COLUMN_WEATHER_CONDITIONS_ICON, WheatherData.COLUMN_TEMPRATURE, WheatherData.COLUMN_MINIMUM_TEMPRATURE, WheatherData.COLUMN_MAXIMUM_TEMPRATURE},
                WheatherData.COLUMN_DATE +" =?",
                new String[]{currentDate}, null, null, null, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }


    public void deleteWeatherData(String lastDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(WheatherData.TABLE_NAME, WheatherData.COLUMN_DATE + " = ?",
                new String[]{lastDate});
        db.close();
    }
}