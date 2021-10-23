package com.example.randomassignerkmm.android.di

import com.example.randomassignerkmm.datasource.cache.AppCache
import com.example.randomassignerkmm.interactors.employee_edit.DeleteEmployee
import com.example.randomassignerkmm.interactors.employee_edit.EditEmployee
import com.example.randomassignerkmm.interactors.employee_edit.GetEmployees
import com.example.randomassignerkmm.interactors.sidework_edit.DeleteSidework
import com.example.randomassignerkmm.interactors.sidework_edit.EditSidework
import com.example.randomassignerkmm.interactors.sidework_edit.GetSideworks
import com.example.randomassignerkmm.interactors.sidework_list.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideShuffleSideworks(
        appCache: AppCache
    ):ShuffleSideworks{
        return ShuffleSideworks(
            appCache = appCache
        )
    }

    @Singleton
    @Provides
    fun provideGetSideworks(
        appCache: AppCache
    ): GetSideworks {
        return GetSideworks(
            appCache = appCache
        )
    }

    @Singleton
    @Provides
    fun provideGetEmployees(
        appCache: AppCache
    ): GetEmployees {
        return GetEmployees(
            appCache = appCache
        )
    }


    @Singleton
    @Provides
    fun provideDeleteEmployee(
        appCache: AppCache
    ): DeleteEmployee {
        return DeleteEmployee(
            appCache = appCache
        )
    }

    @Singleton
    @Provides
    fun provideEditEmployee(
        appCache: AppCache
    ): EditEmployee {
        return EditEmployee(
            appCache = appCache
        )
    }


    @Singleton
    @Provides
    fun provideDeleteSidework(
        appCache: AppCache
    ): DeleteSidework {
        return DeleteSidework(
            appCache = appCache
        )
    }

    @Singleton
    @Provides
    fun provideEditSidework(
        appCache: AppCache
    ): EditSidework {
        return EditSidework(
            appCache = appCache
        )
    }


}