package com.barryalan.randomassignerkmm.android.di

 import com.barryalan.randomassignerkmm.android.BaseApplication
 import com.barryalan.randomassignerkmm.datasource.cache.AppCache
 import com.barryalan.randomassignerkmm.datasource.cache.AppCacheImpl
 import com.barryalan.randomassignerkmm.datasource.cache.AppDatabaseFactory
 import com.barryalan.randomassignerkmm.datasource.cache.DriverFactory
 import com.barryalan.randomassignerkmm.datasource.cache.*
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
    fun provideAppDatabase(context: BaseApplication):AppDatabase{
        return AppDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }

    @Singleton
    @Provides
    fun provideAppCache(
        appDatabase:AppDatabase
    ): AppCache {
        return AppCacheImpl(
            appDatabase = appDatabase
        )
    }
}