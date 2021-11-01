package com.barryalan.randomassignerkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.barryalan.randomassignerkmm.android.presentation.sidework_list.AppViewModel
import com.barryalan.randomassignerkmm.android.presentation.sidework_list.components.MainScreen
import com.barryalan.randomassignerkmm.android.presentation.theme.AppTheme
import com.barryalan.randomassignerkmm.presentation.AppEvents
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
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


