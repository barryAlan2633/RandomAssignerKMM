package com.example.randomassignerkmm.android.presentation.navigation

sealed class Screen(
    val route: String
) {
    object SideworkList: Screen( "sideworkList")

    object SideworkEdit: Screen( "sideworkeEdit")

    object EmployeeEdit: Screen( "employeeEdit")
}