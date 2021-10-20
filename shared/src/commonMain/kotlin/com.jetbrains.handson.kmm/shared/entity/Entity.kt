package com.jetbrains.handson.kmm.shared.entity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//todo add UUIDs

@Serializable
data class Employee(
    @SerialName("employee_id")
    val id:Long,

    @SerialName("employee_name")
    val name:String,

    @SerialName("employee_is_here")
    val isHere:Boolean
)

@Serializable
data class Sidework(
    @SerialName("sidework_id")
    val id:Long,

    @SerialName("sidework_name")
    val name: String,

    @SerialName("employees")
    var employee: Employee?,

    @SerialName("side_work_is_due_today")
    val isDueToday: Boolean
)