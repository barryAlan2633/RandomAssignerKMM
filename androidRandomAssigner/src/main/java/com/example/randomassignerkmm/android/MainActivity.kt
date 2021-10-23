package com.example.randomassignerkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color

import androidx.core.view.WindowCompat
import com.example.randomassignerkmm.android.presentation.navigation.Navigation
import com.google.accompanist.systemuicontroller.rememberSystemUiController
//import com.jetbrains.handson.kmm.shared.AssignerSDK
//import com.jetbrains.handson.kmm.shared.cache.DatabaseDriverFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)



        setContent {
            val systemUiController = rememberSystemUiController()

            systemUiController.setStatusBarColor(
                color = Color.Transparent,
                darkIcons = true
            )

             Navigation()
         }
    }



}

