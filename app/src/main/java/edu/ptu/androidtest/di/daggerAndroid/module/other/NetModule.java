package edu.ptu.androidtest.di.daggerAndroid.module.other;

import dagger.Module;
import dagger.Provides;

@Module
public class NetModule {
    public static class NetApi {

    }
    @Provides
    public NetApi provideBusinessApi(){
        return new NetApi();
    }
}
