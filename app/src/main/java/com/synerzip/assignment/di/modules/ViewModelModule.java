package com.synerzip.assignment.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.synerzip.assignment.di.ViewModelKey;
import com.synerzip.assignment.viewmodel.ViewModelFactory;
import com.synerzip.assignment.viewmodel.WeatherDataViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherDataViewModel.class)
    abstract ViewModel bindViewModel(WeatherDataViewModel weatherDataViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindFactory(ViewModelFactory factory);

}
