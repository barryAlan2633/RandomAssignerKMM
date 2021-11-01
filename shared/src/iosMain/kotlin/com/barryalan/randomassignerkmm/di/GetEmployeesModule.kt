package com.barryalan.randomassignerkmm.di

import com.barryalan.randomassignerkmm.interactors.employee_edit.GetEmployees

class GetEmployeesModule (
    private val cacheModule: CacheModule,
) {
    val getEmployees: GetEmployees by lazy {
        GetEmployees(
            appCache = cacheModule.appCache
        )
    }
}