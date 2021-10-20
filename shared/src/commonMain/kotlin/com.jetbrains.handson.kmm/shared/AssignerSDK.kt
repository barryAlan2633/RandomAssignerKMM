package com.jetbrains.handson.kmm.shared

import com.jetbrains.handson.kmm.shared.cache.Database
import com.jetbrains.handson.kmm.shared.cache.DatabaseDriverFactory
import com.jetbrains.handson.kmm.shared.entity.Employee
import com.jetbrains.handson.kmm.shared.entity.Sidework

class AssignerSDK (databaseDriverFactory: DatabaseDriverFactory){
    private val database = Database(databaseDriverFactory)

    fun getSideWorks():List<Sidework> {
        val cachedSideWorks = database.getAllSideworks()
        return if (cachedSideWorks.isNotEmpty()){
            cachedSideWorks
        }else{
            listOf()
        }
    }

    fun getEmployees():List<Employee> {
        val cachedEmployees = database.getAllEmployees()
        return if (cachedEmployees.isNotEmpty()){
            cachedEmployees
        }else{
            listOf()
        }
    }

    fun deleteEmployee(employee:Employee) {
        database.deleteEmployee(employee)
    }


    fun saveEmployee(employee:Employee){
        database.insertEmployee(employee)
    }

    fun saveSidework(sidework: Sidework){
        database.insertSidework(sidework)
    }
}

