package com.example.randomassignerkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent

import androidx.core.view.WindowCompat
import com.example.randomassignerkmm.android.forms.MainScreen
import com.jetbrains.handson.kmm.shared.AssignerSDK
import com.jetbrains.handson.kmm.shared.cache.DatabaseDriverFactory


class MainActivity : AppCompatActivity() {

    private val sdk = AssignerSDK(DatabaseDriverFactory(this))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

//        sdk.saveEmployee(Employee(
//            id = 1,
//            name= "Alan Ramirez",
//            isHere = false
//        ))
//
//        sdk.saveEmployee(Employee(
//            id = 2,
//            name= "Mannuel Melgar",
//            isHere = false
//        ))
//        sdk.saveEmployee(Employee(
//            id = 3,
//            name= "Saul Leyva",
//            isHere = false
//        ))
//
//        sdk.saveEmployee(Employee(
//            id = 4,
//            name= "Martin Olvera",
//            isHere = false
//        ))

//        Toast.makeText(this,sdk.getEmployees().toString(),Toast.LENGTH_LONG).show()

        setContent {
            MainScreen(sdk)

        }
    }



}

