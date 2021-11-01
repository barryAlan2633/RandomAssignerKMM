package com.barryalan.randomassignerkmm.datasource.cache

import com.barryalan.randomassignerkmm.domain.model.Employee
import com.barryalan.randomassignerkmm.domain.model.Sidework
import com.barryalan.randomassignerkmm.datasource.cache.AppDatabase
import com.barryalan.randomassignerkmm.datasource.cache.Employee_Entity
import com.barryalan.randomassignerkmm.datasource.cache.Sidework_Entity
import com.squareup.sqldelight.db.SqlDriver

class AppDatabaseFactory(
    private val driverFactory: DriverFactory
) {

    fun createDatabase(): AppDatabase {
        return AppDatabase(driverFactory.createDriver())
    }
}

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun Sidework_Entity.toSidework(): Sidework {
    return Sidework(
        id = id,
        name = name,
        employees = employees.convertEmployeesToList().toMutableList(),
        todoToday = todoToday ?: false
    )
}

fun Employee_Entity.toEmployee(): Employee {
    return Employee(
        id = id,
        name = name,
        isHere = isHere ?: false
    )
}

fun List<Sidework_Entity>.toSideworkList(): List<Sidework> {
    return map { it.toSidework() }
}

fun List<Employee_Entity>.toEmployeeList(): List<Employee> {
    return map { it.toEmployee() }
}

fun List<Employee>.convertEmployeeListToString(): String {
    val employeeString = StringBuilder()

    for (employee in this) {
        employeeString.append("${employee.id}/${employee.name}/${employee.isHere},")
    }
    return employeeString.toString()
}


fun String.convertEmployeesToList(): List<Employee> {
    val list: ArrayList<Employee> = ArrayList()
    for (employeeString in split(",")) {
        var id = ""
        var name = ""
        var ishere = false

        for ((i, info) in split("/").withIndex()) {
            if (i == 0) {
                id = info
            }
            if (i == 1) {
                name = info
            }
            if (i == 2) {
                ishere = info.toBoolean()
            }
        }

        list.add(Employee(id, name, ishere))

    }
    return list
}

