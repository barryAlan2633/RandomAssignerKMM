package com.barryalan.randomassignerkmm.di

import com.barryalan.randomassignerkmm.interactors.employee_edit.DeleteEmployee

class DeleteEmployeeModule (
    private val cacheModule:CacheModule,
){
    val deleteEmployee: DeleteEmployee by lazy{
        DeleteEmployee(
            appCache =  cacheModule.appCache
        )
    }
}