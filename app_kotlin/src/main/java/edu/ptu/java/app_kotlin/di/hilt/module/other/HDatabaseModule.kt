package edu.ptu.java.app_kotlin.di.hilt.module.other

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject

@Module
@InstallIn(ApplicationComponent::class)
class HDatabaseModule{

    class RoomRepository@Inject constructor() {

    }
    fun provideDbRepo():RoomRepository{
        return RoomRepository()
    }
}