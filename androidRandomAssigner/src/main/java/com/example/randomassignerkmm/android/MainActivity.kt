package com.example.randomassignerkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.randomassignerkmm.android.presentation.sidework_list.AppViewModel
import com.example.randomassignerkmm.android.presentation.sidework_list.components.MainScreen
import com.example.randomassignerkmm.android.presentation.theme.AppTheme
import com.example.randomassignerkmm.presentation.AppEvents
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
//            val systemUiController = rememberSystemUiController()
//
//            systemUiController.setStatusBarColor(
//                color = Color.Transparent,
//                darkIcons = true
//            )

            val viewModel: AppViewModel = hiltViewModel()

            AppTheme(
                dialogQueue = viewModel.state.value.queue,
                onRemoveHeadMessageFromQueue = {viewModel.onTriggerEvent(AppEvents.OnRemoveHeadMessageFromQueue)}
            ) {
                MainScreen(
                    state = viewModel.state.value,
                    onStateEvent = viewModel::onTriggerEvent
                )
            }
        }
    }


}


//todo are you sure you want to delete on sidework and employee
