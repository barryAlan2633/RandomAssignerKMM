package com.example.randomassignerkmm.di

import com.example.randomassignerkmm.interactors.sidework_list.ShuffleSideworks

class ShuffleSideworksModule(
    val cacheModule: CacheModule
) {
    val shuffleSideworks: ShuffleSideworks by lazy{
        ShuffleSideworks(
            appCache = cacheModule.appCache
        )
    }
}