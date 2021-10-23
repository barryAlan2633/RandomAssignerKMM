package com.example.randomassignerkmm.interactors.employee_edit

import com.example.randomassignerkmm.datasource.cache.AppCache
import com.example.randomassignerkmm.domain.model.Employee
import com.example.randomassignerkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetEmployees (
    private val appCache: AppCache
) {

    fun execute(
    ): Flow<DataState<List<Employee>>> = flow {

        try{
            emit(DataState.data(message = null,data = appCache.getAllEmployees().sortedBy { it.name }))
        }catch (e:Exception){
            //how can we emit errors?
            emit(DataState.error(message = e.message ?: "Unknown Error"))
        }
    }
}