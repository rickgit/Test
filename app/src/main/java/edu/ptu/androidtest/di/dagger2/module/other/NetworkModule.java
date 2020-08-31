package edu.ptu.androidtest.di.dagger2.module.other;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {
    public static class NetApi {

    }
    @Provides
    public NetApi provideBusinessApi(){
        return new NetApi();
    }
}
