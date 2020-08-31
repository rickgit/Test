package edu.ptu.androidtest.di.daggerAndroid._00_app;


import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import edu.ptu.androidtest.di.daggerAndroid.component.AApplicationComponent;
import edu.ptu.androidtest.di.daggerAndroid.component.DaggerAApplicationComponent;

public class AApplication extends Application implements HasAndroidInjector {
    @Inject DispatchingAndroidInjector<Object> dispatchingAndroidInjector;
    private AApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAApplicationComponent.create();
        appComponent
                .inject(this);
    }
    public AApplicationComponent getAppComponent(){
        return appComponent;
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
