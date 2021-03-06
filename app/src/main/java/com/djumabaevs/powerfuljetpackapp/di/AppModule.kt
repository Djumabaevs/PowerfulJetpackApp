package com.djumabaevs.powerfuljetpackapp.di

import android.app.Application
import androidx.room.Room
import com.djumabaevs.powerfuljetpackapp.business.datasource.cache.AppDatabase
import com.djumabaevs.powerfuljetpackapp.business.datasource.cache.AppDatabase.Companion.DATABASE_NAME
import com.djumabaevs.powerfuljetpackapp.business.datasource.cache.account.AccountDao
import com.djumabaevs.powerfuljetpackapp.business.datasource.cache.auth.AuthTokenDao
import com.djumabaevs.powerfuljetpackapp.business.datasource.cache.blog.BlogPostDao
import com.djumabaevs.powerfuljetpackapp.business.datasource.datastore.AppDataStore
import com.djumabaevs.powerfuljetpackapp.business.datasource.datastore.AppDataStoreManager
import com.djumabaevs.powerfuljetpackapp.business.datasource.network.main.JetpackAppMainService
import com.djumabaevs.powerfuljetpackapp.business.domain.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatastoreManager(
        application: Application
    ): AppDataStore {
        return AppDataStoreManager(application)
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder: Gson): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    }

    @Singleton
    @Provides
    fun provideAppDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthTokenDao(db: AppDatabase): AuthTokenDao {
        return db.getAuthTokenDao()
    }

    @Singleton
    @Provides
    fun provideAccountPropertiesDao(db: AppDatabase): AccountDao {
        return db.getAccountPropertiesDao()
    }

    @Singleton
    @Provides
    fun provideJetpackAppMainService(retrofitBuilder: Retrofit.Builder): JetpackAppMainService {
        return retrofitBuilder
            .build()
            .create(JetpackAppMainService::class.java)
    }

    @Singleton
    @Provides
    fun provideBlogPostDao(db: AppDatabase): BlogPostDao {
        return db.getBlogPostDao()
    }
}