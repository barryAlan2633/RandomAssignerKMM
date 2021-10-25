package com.example.randomassignerkmm.interactors.employee_edit

import com.example.randomassignerkmm.datasource.cache.AppCache
import com.example.randomassignerkmm.domain.model.*
import com.example.randomassignerkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EditEmployee(
    private val appCache: AppCache
) {

    fun execute(
        employee: Employee
    ): Flow<DataState<List<Employee>>> = flow {

        try {
            appCache.insertEmployee(employee)

            emit(
                DataState.data(
                    message = null,
                    data = appCache.getAllEmployees().sortedBy { it.name })
            )
        } catch (e: Exception) {
            emit(
                DataState.error<List<Employee>>(
                    message = GenericMessageInfo.Builder()
                        .id("EditEmployee.Error")
                        .title("Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .description(e.message ?: "Unknown Error")
                        .positive(
                            PositiveAction(
                                positiveBtnTxt = "OK",
                                onPositiveAction = {}
                            )
                        )
                )
            )
        }
    }
}