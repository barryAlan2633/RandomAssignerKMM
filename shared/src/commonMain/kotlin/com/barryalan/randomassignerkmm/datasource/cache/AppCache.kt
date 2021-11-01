package com.barryalan.randomassignerkmm.datasource.cache

import com.barryalan.randomassignerkmm.domain.model.Employee
import com.barryalan.randomassignerkmm.domain.model.Sidework


interface AppCache {


    fun insertSidework(sidework: Sidework)

    fun deleteSidework(sideworkID: String)

    fun getAllSideworks():List<Sidework>



    fun insertEmployee(employee: Employee)

    fun deleteEmployee(employeeID: String)

    fun getAllEmployees():List<Employee>


}