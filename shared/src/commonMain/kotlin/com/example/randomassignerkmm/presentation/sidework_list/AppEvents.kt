package com.example.randomassignerkmm.presentation.sidework_list

import com.example.randomassignerkmm.domain.model.Employee
import com.example.randomassignerkmm.domain.model.Sidework


sealed class AppEvents {

    object ShuffleSideworks : AppEvents()

    object ToggleEditEmployees : AppEvents()
    data class ToggleEditEmployee(val employee: Employee) : AppEvents()

    data class DeleteEmployee(val employeeID:String) : AppEvents()
    data class SaveEmployee(val employee: Employee) : AppEvents()


    object ToggleEditSideworks : AppEvents()
    data class ToggleEditSidework(val sidework: Sidework) : AppEvents()

    data class DeleteSidework(val sideworkID:String) : AppEvents()
    data class SaveSidework(val sidework: Sidework) : AppEvents()

    data class SetNewEmployeeName(val name:String) :AppEvents()
    data class SetNewSideworkName(val name:String) :AppEvents()

    data class ToggleTodoToday(val sidework: Sidework):AppEvents()
    data class ToggleIsHere(val employee: Employee):AppEvents()
}