package edu.ptu.java.app_kotlin.di.hilt.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class HAppModule{
    @Provides
    fun provideAppName(): String {
        return "注入 應用名"
    }
}