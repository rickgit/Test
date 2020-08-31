package edu.ptu.androidtest.di.dagger2.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DAppModule {
    @Provides
    @Singleton
    public String provideAppName(){
        return "App name";
    }
}
