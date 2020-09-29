package com.synerzip.assignment;



import com.synerzip.assignment.di.components.AppComponent;
import com.synerzip.assignment.di.components.DaggerAppComponent;

import android.annotation.SuppressLint;
import android.app.Application;



public class BaseApplication extends Application {

    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        appComponent= DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}