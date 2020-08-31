package edu.ptu.androidtest.di.dagger2._00_app;


import android.app.Application;

import edu.ptu.androidtest.di.dagger2._00_app.mvpview.MvpActivityView;
import edu.ptu.androidtest.di.dagger2._00_app.mvpview.MvpFragmentView;
import edu.ptu.androidtest.di.dagger2.component.DActivityComponent;
import edu.ptu.androidtest.di.dagger2.component.DApplicationComponent;
import edu.ptu.androidtest.di.dagger2.component.DFragmentComponent;
import edu.ptu.androidtest.di.dagger2.component.DaggerDActivityComponent;
import edu.ptu.androidtest.di.dagger2.component.DaggerDApplicationComponent;
import edu.ptu.androidtest.di.dagger2.component.DaggerDFragmentComponent;
import edu.ptu.androidtest.di.dagger2.module.DAppModule;

public class MvpApplication extends Application {

    private DApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppConponent();
    }

    public DApplicationComponent getAppComponent() {
        return appComponent;
    }

    public void initAppConponent() {
        appComponent = DaggerDApplicationComponent.builder().dAppModule(new DAppModule()).build();
    }

    public DFragmentComponent getFragmentComponent(MvpFragmentView fragment) {
        DFragmentComponent fragmentComponent = DaggerDFragmentComponent.builder()
                .dApplicationComponent(appComponent)//DApplicationComponent must be set ，因为是dependence appcomponent，必须要设置，否则注入build会出错
                .build();
        return fragmentComponent;
    }

    public DActivityComponent getActivityComponent(MvpActivityView activity) {
        DActivityComponent activityComponent = DaggerDActivityComponent.builder()
                .dApplicationComponent(appComponent)//DApplicationComponent must be set ，因为是dependence appcomponent，必须要设置，否则注入build会出错
                .build();
        return activityComponent;
    }

}
