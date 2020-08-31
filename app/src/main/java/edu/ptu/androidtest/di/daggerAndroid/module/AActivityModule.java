package edu.ptu.androidtest.di.daggerAndroid.module;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import edu.ptu.androidtest.di.dagger2._00_app.MvpActivity;
import edu.ptu.androidtest.di.daggerAndroid._00_app.AActivity;
import edu.ptu.androidtest.di.daggerAndroid.component.AAndroidComponent;

@Module(subcomponents = AAndroidComponent.class)
public abstract class AActivityModule {
    @Binds
    @IntoMap
    @ClassKey(AActivity.class)
    abstract AndroidInjector.Factory<?>
    bindAAndroidInjectorFactory(AAndroidComponent.Factory factory);
}