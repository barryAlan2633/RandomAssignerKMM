package com.barryalan.randomassignerkmm.di

import com.barryalan.randomassignerkmm.interactors.sidework_list.ShuffleSideworks

class ShuffleSideworksModule(
    val cacheModule: CacheModule
) {
    val shuffleSideworks: ShuffleSideworks by lazy{
        ShuffleSideworks(
            appCache = cacheModule.appCache
        )
    }
}