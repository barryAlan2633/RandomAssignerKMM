package com.barryalan.randomassignerkmm.di

import com.barryalan.randomassignerkmm.interactors.sidework_edit.DeleteSidework

class DeleteSideworkModule(
    private val cacheModule: CacheModule,
) {
    val deleteSidework: DeleteSidework by lazy {
        DeleteSidework(
            appCache = cacheModule.appCache
        )
    }
}