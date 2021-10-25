package com.example.randomassignerkmm.presentation

import com.example.randomassignerkmm.domain.model.Employee
import com.example.randomassignerkmm.domain.model.GenericMessageInfo
import com.example.randomassignerkmm.domain.model.Sidework
import com.example.randomassignerkmm.domain.util.Queue

data class AppState (
    val shuffledSideworks:List<Sidework> = listOf(),

    val sideworks:List<Sidework> = listOf(),
    val employees:List<Employee> = listOf(),

    val isSideworksEditShowing:Boolean = false,
    val isEmployeesEditShowing:Boolean = false,

    val isSideworkEditShowing:Boolean = false,
    val isEmployeeEditShowing:Boolean = false,

    val newSideworkName:String = "",
    val newEmployeeName:String = "",

    val selectedSideworkID:String = "",
    val selectedEmployeeID:String = "",

    val employeeButtonText:String = "Add",
    val sideworkButtonText:String = "Add",

    val queue:Queue<GenericMessageInfo> = Queue(mutableListOf())

    )