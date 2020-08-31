package edu.ptu.androidtest.di.daggerAndroid.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AAppModule {
    @Provides
    @Singleton
    public String provideAppName(){
        return "App name";
    }
}
