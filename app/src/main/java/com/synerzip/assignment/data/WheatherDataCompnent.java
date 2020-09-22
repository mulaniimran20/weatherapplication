package com.synerzip.assignment.data;

import com.synerzip.assignment.data.WheatherDataModule;
import com.synerzip.assignment.data.WheatherData;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {WheatherDataModule.class})
public interface WheatherDataCompnent {

    // Declare Objects To Use
    // LoginViewModel Object
    WheatherData wheatherData();

}