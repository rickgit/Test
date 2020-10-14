package edu.ptu.java.app_kotlin.di.hilt.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Named
import javax.inject.Qualifier

@InstallIn(ActivityComponent::class)
@Module
class HActModule{
    @Provides
    @Named(value = "act")
    fun provideActName(): String {
        return "注入Activity 應用名"
    }
}