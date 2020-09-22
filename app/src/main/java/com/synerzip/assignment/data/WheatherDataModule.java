package com.synerzip.assignment.data;

import com.synerzip.assignment.data.WheatherData;

import javax.inject.Singleton;

import dagger.Provides;
import dagger.Module;

@Singleton
@Module
public class WheatherDataModule {

    @Singleton
    @Provides
    public WheatherData wheatherData() {
        return new WheatherData();
    }



}
