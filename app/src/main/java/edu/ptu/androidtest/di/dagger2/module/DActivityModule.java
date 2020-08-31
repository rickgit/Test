package edu.ptu.androidtest.di.dagger2.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import edu.ptu.androidtest.di.dagger2.scope.DActivityScope;

@Module
public class DActivityModule {
    private final Activity activity;

    DActivityModule(Activity activity){
        this.activity=activity;
    }
    @DActivityScope
    @Provides
    public Activity provideActivity(){
        return activity;
    }
}
