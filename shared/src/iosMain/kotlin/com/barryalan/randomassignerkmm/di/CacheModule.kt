package com.barryalan.randomassignerkmm.di

import com.barryalan.randomassignerkmm.datasource.cache.*
import com.barryalan.randomassignerkmm.datasource.cache.AppDatabase

class CacheModule {

    private val driverFactory: DriverFactory by lazy {DriverFactory()}
    val appDatabase: AppDatabase by lazy{
        AppDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }

    val appCache: AppCache by lazy{
        AppCacheImpl(
            appDatabase = appDatabase
        )
    }
}