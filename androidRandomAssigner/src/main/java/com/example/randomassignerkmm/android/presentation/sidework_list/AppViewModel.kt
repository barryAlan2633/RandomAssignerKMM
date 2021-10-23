package com.example.randomassignerkmm.android.presentation.sidework_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomassignerkmm.domain.model.Employee
import com.example.randomassignerkmm.domain.model.Sidework
import com.example.randomassignerkmm.interactors.employee_edit.DeleteEmployee
import com.example.randomassignerkmm.interactors.employee_edit.EditEmployee
import com.example.randomassignerkmm.interactors.employee_edit.GetEmployees
import com.example.randomassignerkmm.interactors.sidework_edit.DeleteSidework
import com.example.randomassignerkmm.interactors.sidework_edit.EditSidework
import com.example.randomassignerkmm.interactors.sidework_edit.GetSideworks
import com.example.randomassignerkmm.interactors.sidework_list.*
import com.example.randomassignerkmm.presentation.sidework_list.AppEvents
import com.example.randomassignerkmm.presentation.sidework_list.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AppViewModel
@Inject
constructor(
    private val shuffleSideworks: ShuffleSideworks,
    private val getEmployees: GetEmployees,
    private val getSideworks: GetSideworks,
    private val deleteEmployee: DeleteEmployee,
    private val deleteSidework: DeleteSidework,
    private val editEmployee: EditEmployee,
    private val editSidework: EditSidework
) : ViewModel() {


    val state: MutableState<AppState> = mutableStateOf(AppState())

    init {
        onTriggerEvent(AppEvents.ShuffleSideworks)
    }

    fun onTriggerEvent(event: AppEvents) {
        when (event) {

            AppEvents.ShuffleSideworks -> {
                shuffleSideworks()
            }

            AppEvents.ToggleEditSideworks -> {
                getSideWorks()
                state.value =
                    state.value.copy(isSideworksEditShowing = !state.value.isSideworksEditShowing)
            }
            AppEvents.ToggleEditEmployees -> {
                getEmployees()
                state.value =
                    state.value.copy(isEmployeesEditShowing = !state.value.isEmployeesEditShowing)
            }

            is AppEvents.ToggleEditEmployee -> {
                if(state.value.selectedEmployeeID == event.employee.id){
                    state.value = state.value.copy(newEmployeeName = "",
                        selectedEmployeeID = "",
                        isEmployeeEditShowing = false,
                        employeeButtonText = "Add"
                        )
                }else{
                    state.value = state.value.copy(newEmployeeName = event.employee.name,
                        selectedEmployeeID = event.employee.id,
                        isEmployeeEditShowing = true,
                        employeeButtonText = "Save"
                    )
                }


            }
            is AppEvents.ToggleEditSidework -> {
                if(state.value.selectedSideworkID == event.sidework.id){
                    state.value = state.value.copy(
                        newSideworkName = "",
                        selectedSideworkID = "",
                        isSideworkEditShowing = false,
                        sideworkButtonText = "Add"
                    )
                }else{
                    state.value = state.value.copy(
                        newSideworkName = event.sidework.name,
                        selectedSideworkID = event.sidework.id,
                        isSideworkEditShowing = true,
                        sideworkButtonText = "Save"
                    )
                }


            }

            is AppEvents.SetNewEmployeeName -> {
                state.value = state.value.copy(newEmployeeName = event.name)
            }
            is AppEvents.SetNewSideworkName -> {
                state.value = state.value.copy(newSideworkName = event.name)
            }

            is AppEvents.DeleteEmployee -> {
                deleteEmployee(event.employeeID)
                getEmployees()
            }
            is AppEvents.DeleteSidework -> {
                deleteSidework(event.sideworkID)
                getSideWorks()
            }

            is AppEvents.SaveEmployee -> {
                state.value = state.value.copy(newEmployeeName = "")
                saveEmployee(event.employee)
                getEmployees()
            }
            is AppEvents.SaveSidework -> {
                state.value = state.value.copy(newSideworkName = "")
                saveSidework(event.sidework)
                getSideWorks()
            }

            is AppEvents.ToggleTodoToday -> {
                saveSidework(sidework = event.sidework)
                getSideWorks()
            }
            is AppEvents.ToggleIsHere -> {
                saveEmployee(employee = event.employee)
                getEmployees()
            }

            else -> {
                handleError("Invalid event")
            }
        }
    }


    private fun shuffleSideworks() {
        shuffleSideworks.execute().onEach { dataState ->

            dataState.data?.let { sideworks ->
                state.value = state.value.copy(shuffledSideworks = sideworks)
            }

            dataState.message?.let { message ->
                handleError(message)
            }


        }.launchIn(viewModelScope)
    }

    private fun getSideWorks() {
        getSideworks.execute().onEach { dataState ->

            dataState.data?.let { sideworks ->
                state.value = state.value.copy(sideworks = sideworks)
            }

            dataState.message?.let { message ->
                handleError(message)
            }


        }.launchIn(viewModelScope)
    }
    private fun getEmployees() {
        getEmployees.execute().onEach { dataState ->

            dataState.data?.let { employees ->
                state.value = state.value.copy(employees = employees)
            }

            dataState.message?.let { message ->
                handleError(message)
            }


        }.launchIn(viewModelScope)
    }

    private fun deleteEmployee(employeeID: String) {
        deleteEmployee.execute(employeeID).onEach { dataState ->

            dataState.message?.let { message ->
                handleError(message)
            }

        }.launchIn(viewModelScope)
    }
    private fun deleteSidework(sideworkID: String) {
        deleteSidework.execute(sideworkID).onEach { dataState ->

            dataState.message?.let { message ->
                handleError(message)
            }

        }.launchIn(viewModelScope)
    }

    private fun saveEmployee(employee: Employee) {
        editEmployee.execute(employee).onEach { dataState ->

            dataState.message?.let { message ->
                handleError(message)
            }

        }.launchIn(viewModelScope)
    }
    private fun saveSidework(sidework:Sidework) {
        editSidework.execute(sidework).onEach { dataState ->

            dataState.message?.let { message ->
                handleError(message)
            }

        }.launchIn(viewModelScope)
    }

    private fun handleError(errorMessage: String) {
        //todo handle the error
    }
}


//todo set uuids with dependency, figure out saving one to many relation,finish course,hook to ios