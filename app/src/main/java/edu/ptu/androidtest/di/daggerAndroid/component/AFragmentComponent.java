package edu.ptu.androidtest.di.daggerAndroid.component;


import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import edu.ptu.androidtest.di.daggerAndroid._00_app.AFragment;
import edu.ptu.androidtest.di.daggerAndroid.module.AFragmentModule;

@Subcomponent(modules = AFragmentModule.class)
public interface AFragmentComponent extends AndroidInjector<AFragment> {
    @Subcomponent.Factory
    public interface Factory extends AndroidInjector.Factory<AFragment> {}
}
