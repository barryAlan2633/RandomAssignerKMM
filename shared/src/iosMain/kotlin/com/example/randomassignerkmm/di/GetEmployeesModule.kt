package com.example.randomassignerkmm.di

import com.example.randomassignerkmm.interactors.employee_edit.EditEmployee
import com.example.randomassignerkmm.interactors.employee_edit.GetEmployees
import com.example.randomassignerkmm.interactors.sidework_edit.EditSidework

class GetEmployeesModule (
    private val cacheModule: CacheModule,
) {
    val getEmployees: GetEmployees by lazy {
        GetEmployees(
            appCache = cacheModule.appCache
        )
    }
}