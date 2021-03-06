package com.barryalan.randomassignerkmm.android.presentation.sidework_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barryalan.randomassignerkmm.domain.model.*
import com.barryalan.randomassignerkmm.domain.util.GenericMessageInfoQueueUtil
import com.barryalan.randomassignerkmm.domain.util.Queue
import com.barryalan.randomassignerkmm.interactors.employee_edit.DeleteEmployee
import com.barryalan.randomassignerkmm.interactors.employee_edit.EditEmployee
import com.barryalan.randomassignerkmm.interactors.employee_edit.GetEmployees
import com.barryalan.randomassignerkmm.interactors.sidework_edit.DeleteSidework
import com.barryalan.randomassignerkmm.interactors.sidework_edit.EditSidework
import com.barryalan.randomassignerkmm.interactors.sidework_edit.GetSideworks
import com.barryalan.randomassignerkmm.interactors.sidework_list.*
import com.barryalan.randomassignerkmm.presentation.AppEvents
import com.barryalan.randomassignerkmm.presentation.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import java.util.*
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

    fun onTriggerEvent(event: AppEvents) {
        when (event) {

            is AppEvents.OnRemoveHeadMessageFromQueue -> removeHeadMessage()

            AppEvents.ShuffleSideworks -> assignSideworks()

            AppEvents.ToggleEditSideworks -> toggleEditSideworks()
            AppEvents.ToggleEditEmployees -> toggleEditEmployees()

            is AppEvents.ToggleEditSidework -> toggleEditSidework(event.sidework)
            is AppEvents.ToggleEditEmployee -> toggleEditEmployee(event.employee)

            is AppEvents.ToggleTodoToday -> saveSidework(event.sidework)
            is AppEvents.ToggleIsHere -> saveEmployee(event.employee)

            is AppEvents.SetNewSideworkName -> setNewSideworkName(event.name)
            is AppEvents.SetNewEmployeeName -> setNewEmployeeName(event.name)

            is AppEvents.DeleteSidework -> deleteSidework(event.sideworkID)
            is AppEvents.DeleteEmployee -> deleteEmployee(event.employeeID)

            is AppEvents.SaveSidework -> saveSidework(event.sidework)
            is AppEvents.SaveEmployee -> saveEmployee(event.employee)

            else -> appendToMessageQueue(
                GenericMessageInfo.Builder()
                    .id(UUID.randomUUID().toString())
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description("Invalid event")
            )
        }
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.queue
            queue.remove()
            state.value = state.value.copy(queue = Queue(mutableListOf())) //force recompose
            state.value = state.value.copy(queue = queue)
        } catch (e: Exception) {
            //nothing to remove, queue is empty
        }
    }

    private fun assignSideworks() {
        shuffleSideworks.execute().collectCommon(viewModelScope) { dataState ->

            dataState.data?.let { sideworks ->
                state.value = state.value.copy(shuffledSideworks = sideworks)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }

        }
    }

    private fun toggleEditSideworks() {
        getSideworks.execute().collectCommon(viewModelScope) { dataState ->

            dataState.data?.let { sideworks ->
                state.value = state.value.copy(
                    sideworks = sideworks,
                    isSideworksEditShowing = !state.value.isSideworksEditShowing
                )
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }


        }
    }

    private fun toggleEditEmployees() {
        getEmployees.execute().collectCommon(viewModelScope) { dataState ->

            dataState.data?.let { employees ->
                state.value = state.value.copy(
                    employees = employees,
                    isEmployeesEditShowing = !state.value.isEmployeesEditShowing
                )
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }


        }

    }

    private fun toggleEditSidework(sidework: Sidework) {
        if (state.value.selectedSideworkID == sidework.id) {
            state.value = state.value.copy(
                newSideworkName = "",
                selectedSideworkID = "",
                isSideworkEditShowing = false,
                sideworkButtonText = "Add"
            )
        } else {
            state.value = state.value.copy(
                newSideworkName = sidework.name,
                selectedSideworkID = sidework.id,
                isSideworkEditShowing = true,
                sideworkButtonText = "Save"
            )
        }
    }

    private fun toggleEditEmployee(employee: Employee) {
        if (state.value.selectedEmployeeID == employee.id) {
            state.value = state.value.copy(
                newEmployeeName = "",
                selectedEmployeeID = "",
                isEmployeeEditShowing = false,
                employeeButtonText = "Add"
            )
        } else {
            state.value = state.value.copy(
                newEmployeeName = employee.name,
                selectedEmployeeID = employee.id,
                isEmployeeEditShowing = true,
                employeeButtonText = "Save"
            )
        }
    }

    private fun setNewSideworkName(name: String) {
        state.value = state.value.copy(newSideworkName = name)
    }

    private fun setNewEmployeeName(name: String) {
        state.value = state.value.copy(newEmployeeName = name)
    }

    private fun deleteSidework(sideworkID: String) {
        deleteSidework.execute(sideworkID).collectCommon(viewModelScope) { dataState ->

            dataState.data?.let { sideworks ->
                state.value = state.value.copy(sideworks = sideworks)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }

        }
    }

    private fun deleteEmployee(employeeID: String) {
        deleteEmployee.execute(employeeID).collectCommon(viewModelScope) { dataState ->

            dataState.data?.let { employees ->
                state.value = state.value.copy(employees = employees)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }

        }
    }

    private fun saveSidework(sidework: Sidework) {
        editSidework.execute(sidework).collectCommon(viewModelScope) { dataState ->

            dataState.data?.let { sideworks ->
                state.value = state.value.copy(
                    sideworks = sideworks,
                    newSideworkName = "",
                    sideworkButtonText = "Add",
                    selectedSideworkID = "",
                    isSideworkEditShowing = false
                )
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }

        }
    }

    private fun saveEmployee(employee: Employee) {
        editEmployee.execute(employee).collectCommon(viewModelScope) { dataState ->

            dataState.data?.let { employees ->

                state.value = state.value.copy(
                    employees = employees,
                    newEmployeeName = "",
                    employeeButtonText = "Add",
                    selectedEmployeeID = "",
                    isEmployeeEditShowing = false
                )
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }

        }
    }

    private fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder) {
        if (!GenericMessageInfoQueueUtil()
                .doesMessageAlreadyExistInQueue(
                    queue = state.value.queue, messageInfo = messageInfo.build()
                )
        ) {
            val queue = state.value.queue
            queue.add(messageInfo.build())
            state.value = state.value.copy(queue = Queue(mutableListOf()))
            state.value = state.value.copy(queue = queue)
        }

    }
}


