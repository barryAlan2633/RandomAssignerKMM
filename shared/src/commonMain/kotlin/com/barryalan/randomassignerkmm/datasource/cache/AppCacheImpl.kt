package com.barryalan.randomassignerkmm.datasource.cache

import com.barryalan.randomassignerkmm.domain.model.Employee
import com.barryalan.randomassignerkmm.domain.model.Sidework
import com.barryalan.randomassignerkmm.datasource.cache.AppDatabase
import com.barryalan.randomassignerkmm.datasource.cache.AppDatabaseQueries

class AppCacheImpl(
    val appDatabase: AppDatabase
) : AppCache {

    private val queries: AppDatabaseQueries = appDatabase.appDatabaseQueries

    override fun insertSidework(sidework: Sidework) {
        queries.insertSidework(
            id = sidework.id,
            name = sidework.name,
            employees = sidework.employees.convertEmployeeListToString(),
            todoToday = sidework.todoToday
        )
    }

    override fun deleteSidework(sideworkID: String) {
        queries.deleteSidework(
            id = sideworkID
        )
    }


    override fun getAllSideworks(): List<Sidework> {
        return queries.selectAllSideworks().executeAsList().toSideworkList()
    }

    override fun insertEmployee(employee: Employee) {
        queries.insertEmployee(
            id = employee.id,
            name = employee.name,
            isHere = employee.isHere
        )
    }

    override fun deleteEmployee(employeeID: String) {
        queries.deleteEmployee(
            id = employeeID
        )
    }

    override fun getAllEmployees(): List<Employee> {
        return queries.selectAllEmployees().executeAsList().toEmployeeList()
    }
}