package com.barryalan.randomassignerkmm.di

import com.barryalan.randomassignerkmm.interactors.employee_edit.EditEmployee

class EditEmployeeModule (
    private val cacheModule: CacheModule,
) {
    val editEmployee: EditEmployee by lazy {
        EditEmployee(
            appCache = cacheModule.appCache
        )
    }
}