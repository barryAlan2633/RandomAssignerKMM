package com.example.randomassignerkmm.domain.model


data class Sidework(
    val id:String,
    val name: String,
    var employees: List<Employee>,
    val todoToday: Boolean
)

