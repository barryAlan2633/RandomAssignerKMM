package com.example.randomassignerkmm.interactors.sidework_list

import com.example.randomassignerkmm.datasource.cache.AppCache
import com.example.randomassignerkmm.domain.model.Employee
import com.example.randomassignerkmm.domain.model.Sidework
import com.example.randomassignerkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.floor

class ShuffleSideworks(
    private val appCache: AppCache
) {
    fun execute(
    ): Flow<DataState<List<Sidework>>> = flow {

        try {

            //guard while making sure to do the list work possible but keep it readable at more work expense
            var sideworks = appCache.getAllSideworks()
            if (sideworks.isEmpty()) {
                println("You need to create sideworks first!")
                return@flow
            }
            sideworks = sideworks.filter { it.todoToday }.toMutableList()

            var employees = appCache.getAllEmployees()
            if (employees.isEmpty()) {
                println("You need to create employees first!")
                return@flow
            }
            employees = employees.filter { it.isHere }.toMutableList()

            if (sideworks.isEmpty()) {
                println("You need to select some sideworks first!")
                return@flow
            }
            if (employees.isEmpty()) {
                println("You need to select some employees first!")
                return@flow
            }

            //shuffle list to make sure they are random
            employees = employees.shuffled().toMutableList()
            sideworks = sideworks.shuffled()

            //Make sure there are enough employees to fill the jobs
            if (employees.size < sideworks.size) {
                var employeesMissing = sideworks.size - employees.size
                var employeeIndex = employees.size - 1

                while (employeesMissing > 0) {
                    employees.add(employees[employeeIndex])
                    employeesMissing -= 1
                    employeeIndex -= 1

                    if (employeeIndex == -1) {
                        employeeIndex = employees.size - 1
                    }
                }
            }


            //Assign each employee to however many sideworks they are supposed to have evenly
            var employeeIndex = 0
            var sideworkPerEmployee: Double =
                floor(employees.size.toDouble() / sideworks.size.toDouble())

            for (sidework in sideworks) {
                sidework.employees = mutableListOf()
                while (sideworkPerEmployee > 0) {
                    sidework.employees.add(employees[employeeIndex])
                    sideworkPerEmployee -= 1
                    employeeIndex += 1
                }


                sideworkPerEmployee = floor(employees.size.toDouble() / sideworks.size.toDouble())

            }


            //If there are employees left then assign them to random sideworks
            if (employees.size > sideworks.size) {
                for (sidework in sideworks) {
                    if (employeeIndex > employees.size - 1) {
                        break
                    }

                    sidework.employees.add(employees[employeeIndex])
                    employeeIndex += 1

                }
            }

            emit(DataState.data(message = null, data = sideworks.sortedBy { it.name }))

        } catch (e: Exception) {
            //how can we emit errors?
            emit(DataState.error(message = e.message ?: "Unknown Error"))
        }
    }
}



