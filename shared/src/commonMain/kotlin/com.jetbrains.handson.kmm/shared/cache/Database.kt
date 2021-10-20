package com.jetbrains.handson.kmm.shared.cache

import com.jetbrains.handson.kmm.shared.cache.DatabaseDriverFactory
import com.jetbrains.handson.kmm.shared.entity.Employee
import com.jetbrains.handson.kmm.shared.entity.Sidework

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries


    internal fun insertEmployee(employee:Employee){
        dbQuery.transaction {
            dbQuery.insertWorker(
                workerID = employee.id,workerName = employee.name,isHere = employee.isHere,sideworkID = 10
            )
        }
    }

    internal fun insertSidework(sidework:Sidework){
        dbQuery.transaction {
            dbQuery.insertWork(
                workID = sidework.id,workName = sidework.name, isDueToday = sidework.isDueToday
            )
        }
    }

    internal fun clearDatabase(){
        dbQuery.transaction {
            dbQuery.removeAllWorkers()
            dbQuery.removeAllWorks()
        }
    }

    internal fun getAllEmployees():List<Employee> {
        return dbQuery.selectAllWorkers(::mapEmployees).executeAsList()
    }

    internal fun getAllSideworks():List<Sidework> {
        return dbQuery.selectAllWorks(::mapSideworks).executeAsList()
    }

    internal fun deleteEmployee(employee: Employee){
        dbQuery.removeWorker(workerID = employee.id,workerName = employee.name)
    }

    private fun mapEmployees(
        workerID: Long,
        workerName: String,
        isHere: Boolean?,
        sideworkID: Long?
    ): Employee {
        return Employee(id = workerID, name = workerName, isHere = isHere?:false)
    }

//    internal fun createSideWorks(sideworks:List<Sidework>){
//        dbQuery.transaction {
//            sideworks.forEach { sidework->
//                val employee = dbQuery.selectWorkersByID(sidework.employee.id).executeAsOneOrNull()
//                if (employee == null){
//                    insertEmployee(sidework.employee)
//                }
//                insertSidework(sidework)
//            }
//        }
//    }








    private fun mapSideworks(
        workID: Long,
        workName: String,
        isDueToday: Boolean?,
        workerID: Long?,
        workerName: String?,
        isHere: Boolean?,
        sideworkID: Long?

    ): Sidework {
        if(workerID == null){
            return Sidework(
                id = workID,
                name = workName,
                isDueToday = isDueToday?: false,
                employee = Employee(id = -1, name = "No Assignment", isHere = false)
            )
        }else{
            return Sidework(
                id = workID,
                name = workName,
                isDueToday = isDueToday?: false,
                employee = Employee(id = workerID, name = workerName!!, isHere = isHere?:false)
            )
        }


    }
}