package com.barryalan.randomassignerkmm.interactors.employee_edit

 import com.barryalan.randomassignerkmm.datasource.cache.AppCache
 import com.barryalan.randomassignerkmm.domain.model.Employee
 import com.barryalan.randomassignerkmm.domain.model.GenericMessageInfo
 import com.barryalan.randomassignerkmm.domain.model.PositiveAction
 import com.barryalan.randomassignerkmm.domain.model.UIComponentType
 import com.barryalan.randomassignerkmm.domain.util.CommonFlow
 import com.barryalan.randomassignerkmm.domain.util.DataState
 import com.barryalan.randomassignerkmm.domain.util.asCommonFlow
 import kotlinx.coroutines.flow.flow

class EditEmployee(
    private val appCache: AppCache
) {

    fun execute(
        employee: Employee
    ): CommonFlow<DataState<List<Employee>>> = flow {

        try {
            if (employee.name == "") {
                throw Exception("Your new employee must have a name!")
            }

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
    }.asCommonFlow()
}