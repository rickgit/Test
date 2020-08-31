package edu.ptu.androidtest._10_ooad.di;

import org.junit.Assert;
import org.junit.Test;

import edu.ptu.androidtest.di.dagger2._00_app.MvpActivity;
import edu.ptu.androidtest.di.daggerAndroid._00_app.AActivity;
import edu.ptu.androidtest.di.daggerAndroid._00_app.AApplication;
import edu.ptu.androidtest.di.daggerAndroid.component.AApplicationComponent;
import edu.ptu.androidtest.di.daggerAndroid.module.other.ImageModule;
import edu.ptu.androidtest.di.daggerAndroid.module.other.NetModule;

public class _02_DaggerAndroidTest {
    @Test
    public void testDaggerAndroid() {
        AApplication mvpApplication = new AApplication();
        mvpApplication.onCreate();
        AApplicationComponent appComponent = mvpApplication.getAppComponent();
        ImageModule.ImageUtils imageLoader = appComponent.getImageLoader();
        Assert.assertNotNull(imageLoader);

        NetModule.NetApi netApi = appComponent.getNetApi();
        Assert.assertNotNull(netApi);


        AActivity mvpActivity = new AActivity();
        mvpActivity.initInjectBeforeOncreate(mvpActivity);
//        mvpApplication.getActivityComponent(mvpActivity).injectActivity(mvpActivity);
//        Assert.assertNotNull(mvpActivity.presenter);

        System.out.println();
    }
}
