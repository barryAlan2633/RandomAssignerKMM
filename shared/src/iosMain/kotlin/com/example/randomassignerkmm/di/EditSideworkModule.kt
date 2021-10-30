package com.example.randomassignerkmm.di

import com.example.randomassignerkmm.interactors.sidework_edit.EditSidework

class EditSideworkModule (
    private val cacheModule: CacheModule,
) {
    val editSidework: EditSidework by lazy {
        EditSidework(
            appCache = cacheModule.appCache
        )
    }
}