package edu.ptu.androidtest.di.dagger2.component;

import dagger.Component;
import edu.ptu.androidtest.di.dagger2._00_app.MvpActivity;
import edu.ptu.androidtest.di.dagger2.module.DActivityModule;
import edu.ptu.androidtest.di.dagger2.scope.DActivityScope;

@DActivityScope
@Component(dependencies = DApplicationComponent.class,modules = DActivityModule.class)
public interface DActivityComponent {
    void injectActivity(MvpActivity activity);
}
