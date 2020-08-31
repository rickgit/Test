package edu.ptu.androidtest.di.dagger2.component;

import dagger.Component;
import edu.ptu.androidtest.di.dagger2.module.DFragmentModule;
import edu.ptu.androidtest.di.dagger2.scope.DFragmentScope;

@DFragmentScope
@Component(dependencies = DApplicationComponent.class,modules = DFragmentModule.class)
public interface DFragmentComponent {
}
