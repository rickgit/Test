package edu.ptu.androidtest.di.daggerAndroid.module;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import edu.ptu.androidtest.di.daggerAndroid._00_app.AFragment;
import edu.ptu.androidtest.di.daggerAndroid.component.AFragmentComponent;

@Module
public abstract class AFragmentModule {
    @Binds
    @IntoMap
    @ClassKey(AFragment.class)
    abstract AndroidInjector.Factory<?>
    bindAFragmentInjectorFactory(AFragmentComponent.Factory factory);
}
