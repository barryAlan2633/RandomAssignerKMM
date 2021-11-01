package com.barryalan.randomassignerkmm.di

import com.barryalan.randomassignerkmm.interactors.sidework_edit.GetSideworks

class GetSideworksModule(
    private val cacheModule: CacheModule,
) {
    val getSideworks: GetSideworks by lazy {
        GetSideworks(
            appCache = cacheModule.appCache
        )
    }
}