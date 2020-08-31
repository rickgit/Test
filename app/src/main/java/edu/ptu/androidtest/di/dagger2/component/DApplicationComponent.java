package edu.ptu.androidtest.di.dagger2.component;

import javax.inject.Singleton;

import dagger.Component;
import edu.ptu.androidtest.di.dagger2.module.DAppModule;
import edu.ptu.androidtest.di.dagger2.module.other.ImageModule;
import edu.ptu.androidtest.di.dagger2.module.other.NetworkModule;

//不能再单元测试文件夹
@Singleton
@Component(modules = {DAppModule.class, ImageModule.class, NetworkModule.class})
public interface DApplicationComponent {
    ImageModule.ImageUtils getImageLoader();
    NetworkModule.NetApi getNetApi();
}
