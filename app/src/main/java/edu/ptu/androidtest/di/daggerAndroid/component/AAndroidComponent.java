package edu.ptu.androidtest.di.daggerAndroid.component;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import edu.ptu.androidtest.di.daggerAndroid._00_app.AActivity;
import edu.ptu.androidtest.di.daggerAndroid.module.AActivityModule;
import edu.ptu.androidtest.di.daggerAndroid.module.AFragmentModule;


@Subcomponent(modules = {AActivityModule.class})
public interface AAndroidComponent extends AndroidInjector<AActivity> {
    @Subcomponent.Factory
    public interface Factory extends AndroidInjector.Factory<AActivity> {}
}
