package com.example.randomassignerkmm.android.di

import com.example.randomassignerkmm.android.BaseApplication
import com.example.randomassignerkmm.datasource.cache.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context:BaseApplication):AppDatabase{
        return AppDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }

    @Singleton
    @Provides
    fun provideAppCache(
        appDatabase:AppDatabase
    ):AppCache{
        return AppCacheImpl(
            appDatabase = appDatabase
        )
    }
}