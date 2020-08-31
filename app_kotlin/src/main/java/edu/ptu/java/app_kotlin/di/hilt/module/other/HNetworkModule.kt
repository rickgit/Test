package edu.ptu.java.app_kotlin.di.hilt.module.other

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject

@Module
@InstallIn(ApplicationComponent::class)
class HNetworkModule{

    class NetworkRepository@Inject constructor() {

    }
    fun provideDbRepo():NetworkRepository{
        return NetworkRepository()
    }
}