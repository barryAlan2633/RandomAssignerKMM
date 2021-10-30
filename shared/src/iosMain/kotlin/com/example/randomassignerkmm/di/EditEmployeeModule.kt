package com.example.randomassignerkmm.di

import com.example.randomassignerkmm.interactors.employee_edit.EditEmployee
import com.example.randomassignerkmm.interactors.sidework_edit.DeleteSidework

class EditEmployeeModule (
    private val cacheModule: CacheModule,
) {
    val editEmployee: EditEmployee by lazy {
        EditEmployee(
            appCache = cacheModule.appCache
        )
    }
}