package com.example.randomassignerkmm.interactors.employee_edit

import com.example.randomassignerkmm.datasource.cache.AppCache
import com.example.randomassignerkmm.domain.model.Employee
import com.example.randomassignerkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EditEmployee (
    private val appCache: AppCache
) {

    fun execute(
        employee:Employee
    ): Flow<DataState<List<Employee>>> = flow {

        try {
            appCache.insertEmployee(employee)
        } catch (e: Exception) {
            //how can we emit errors?
            emit(DataState.error(message = e.message ?: "Unknown Error"))
        }
    }
}