package edu.ptu.androidtest.di.daggerAndroid.component;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import edu.ptu.androidtest.di.daggerAndroid._00_app.AApplication;
import edu.ptu.androidtest.di.daggerAndroid.module.AAppModule;
import edu.ptu.androidtest.di.daggerAndroid.module.other.ImageModule;
import edu.ptu.androidtest.di.daggerAndroid.module.other.NetModule;

//不能再单元测试文件夹

@Component(modules = {AndroidInjectionModule.class, AAppModule.class, NetModule.class, ImageModule.class})
public interface AApplicationComponent {
    void inject(AApplication app);
    ImageModule.ImageUtils getImageLoader();
    NetModule.NetApi getNetApi();
}
