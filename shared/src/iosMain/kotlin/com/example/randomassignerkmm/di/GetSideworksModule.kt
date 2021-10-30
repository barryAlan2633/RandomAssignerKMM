package com.example.randomassignerkmm.di

 import com.example.randomassignerkmm.interactors.sidework_edit.GetSideworks

class GetSideworksModule(
    private val cacheModule: CacheModule,
) {
    val getSideworks: GetSideworks by lazy {
        GetSideworks(
            appCache = cacheModule.appCache
        )
    }
}