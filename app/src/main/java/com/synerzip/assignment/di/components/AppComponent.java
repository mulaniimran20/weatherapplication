package com.synerzip.assignment.di.components;



import com.synerzip.assignment.MainActivity;
import com.synerzip.assignment.di.modules.ContextModule;
import com.synerzip.assignment.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ContextModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
}
