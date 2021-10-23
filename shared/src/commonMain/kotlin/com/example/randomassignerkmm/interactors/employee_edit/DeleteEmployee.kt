package com.example.randomassignerkmm.interactors.employee_edit

import com.example.randomassignerkmm.datasource.cache.AppCache
import com.example.randomassignerkmm.domain.model.Employee
import com.example.randomassignerkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteEmployee (
    private val appCache: AppCache
) {

    fun execute(
        sideworkID:String
    ): Flow<DataState<List<Employee>>> = flow {

        try {
            appCache.deleteEmployee(sideworkID)
        } catch (e: Exception) {
            //how can we emit errors?
            emit(DataState.error(message = e.message ?: "Unknown Error"))
        }
    }
}