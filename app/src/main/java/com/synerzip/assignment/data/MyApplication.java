package com.synerzip.assignment.data;

import android.app.Application;

import com.synerzip.assignment.data.DaggerWheatherDataCompnent;
import com.synerzip.assignment.data.WheatherDataCompnent;

public class MyApplication extends Application {

    public static WheatherDataCompnent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerWheatherDataCompnent.builder().build();

    }
}