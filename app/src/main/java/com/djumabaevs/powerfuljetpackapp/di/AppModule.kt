package com.djumabaevs.powerfuljetpackapp.di

import android.app.Application
import com.djumabaevs.powerfuljetpackapp.business.datasource.datastore.AppDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatastoreManager(
        application: Application
    ): AppDataStore {
        return App
    }
}