package com.example.randomassignerkmm.interactors.employee_edit

import com.example.randomassignerkmm.datasource.cache.AppCache
import com.example.randomassignerkmm.domain.model.Employee
import com.example.randomassignerkmm.domain.model.GenericMessageInfo
import com.example.randomassignerkmm.domain.model.PositiveAction
import com.example.randomassignerkmm.domain.model.UIComponentType
import com.example.randomassignerkmm.domain.util.CommonFlow
import com.example.randomassignerkmm.domain.util.DataState
import com.example.randomassignerkmm.domain.util.asCommonFlow
import kotlinx.coroutines.flow.flow

class DeleteEmployee(
    private val appCache: AppCache
) {

    fun execute(
        employeeID: String
    ): CommonFlow<DataState<List<Employee>>> = flow {

        try {
            appCache.deleteEmployee(employeeID)

            emit(
                DataState.data(
                    message = null,
                    data = appCache.getAllEmployees().sortedBy { it.name })
            )

        } catch (e: Exception) {
            emit(
                DataState.error<List<Employee>>(
                    message = GenericMessageInfo.Builder()
                        .id("DeleteEmployee.Error")
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
    }.asCommonFlow()
}