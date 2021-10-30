package com.example.randomassignerkmm.di

import com.example.randomassignerkmm.datasource.cache.*

class CacheModule {

    private val driverFactory: DriverFactory by lazy {DriverFactory()}
    val appDatabase:AppDatabase by lazy{
        AppDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }

    val appCache: AppCache by lazy{
        AppCacheImpl(
            appDatabase = appDatabase
        )
    }
}