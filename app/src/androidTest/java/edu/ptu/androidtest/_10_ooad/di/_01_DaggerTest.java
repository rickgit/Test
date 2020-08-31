package edu.ptu.androidtest._10_ooad.di;

import org.junit.Assert;
import org.junit.Test;

import edu.ptu.androidtest.di.dagger2._00_app.MvpActivity;
import edu.ptu.androidtest.di.dagger2._00_app.MvpApplication;
import edu.ptu.androidtest.di.dagger2.component.DApplicationComponent;
import edu.ptu.androidtest.di.dagger2.module.other.ImageModule;
import edu.ptu.androidtest.di.dagger2.module.other.NetworkModule;

public class _01_DaggerTest {
    @Test
    public void testDagger2(){
        MvpApplication mvpApplication = new MvpApplication();
        mvpApplication.initAppConponent();
        DApplicationComponent appComponent = mvpApplication.getAppComponent();
        ImageModule.ImageUtils imageLoader = appComponent.getImageLoader();
        Assert.assertNotNull(imageLoader);

        NetworkModule.NetApi netApi = appComponent.getNetApi();
        Assert.assertNotNull(netApi);


        MvpActivity mvpActivity = new MvpActivity ();
        mvpApplication.getActivityComponent(mvpActivity).injectActivity(mvpActivity);
        Assert.assertNotNull(mvpActivity.presenter);

        System.out.println();
    }
}
