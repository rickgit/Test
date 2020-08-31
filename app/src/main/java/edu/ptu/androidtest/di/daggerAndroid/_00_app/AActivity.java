package edu.ptu.androidtest.di.daggerAndroid._00_app;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class AActivity extends Activity  implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initInjectBeforeOncreate(this);
        super.onCreate(savedInstanceState);
    }

    public void initInjectBeforeOncreate(Activity activity) {
        AndroidInjection.inject(activity);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}
