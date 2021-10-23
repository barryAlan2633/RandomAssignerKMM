package com.example.randomassignerkmm.android.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.randomassignerkmm.android.presentation.sidework_list.AppViewModel
import com.example.randomassignerkmm.android.presentation.sidework_list.components.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()



    NavHost(navController = navController,startDestination = Screen.SideworkList.route) {
        composable(route = Screen.SideworkList.route){
            val viewModel: AppViewModel = hiltViewModel()

            Column{
                Spacer(Modifier.height(25.dp))
                MainScreen(
                    state = viewModel.state.value,
                    onStateEvent = viewModel::onTriggerEvent
                )
            }
        }
    }
}