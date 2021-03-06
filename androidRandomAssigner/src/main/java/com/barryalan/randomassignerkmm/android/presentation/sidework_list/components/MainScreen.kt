package com.barryalan.randomassignerkmm.android.presentation.sidework_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.barryalan.randomassignerkmm.android.presentation.components.BottomSheetView
import com.barryalan.randomassignerkmm.android.presentation.components.ButtonView
import com.barryalan.randomassignerkmm.presentation.AppEvents
import com.barryalan.randomassignerkmm.presentation.AppState


@Composable
fun MainScreen(
    state: AppState,
    onStateEvent: (AppEvents) -> Unit,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            Modifier.weight(1f)
        ) {
            SideworkListView(state)
        }


        Column {
            Row(
                modifier = Modifier.fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.Center
            ) {
                ButtonView(
                    text = "Side Work",
                    buttonModifier = Modifier
                        .weight(1f)
                        .height(58.dp)
                        .padding(start = 5.dp, bottom = 10.dp, end = 5.dp),
                    textModifier = Modifier.padding(vertical = 5.dp),
                    onClick = { onStateEvent(AppEvents.ToggleEditSideworks) }
                )

                ButtonView(
                    text = "Employees",
                    buttonModifier = Modifier
                        .weight(1f)
                        .height(58.dp)
                        .padding(start = 5.dp, bottom = 10.dp, end = 5.dp),
                    textModifier = Modifier.padding(vertical = 5.dp),
                    onClick = { onStateEvent(AppEvents.ToggleEditEmployees) }
                )
            }

            ButtonView(
                text = "Shuffle",
                buttonModifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(58.dp)
                    .padding(start = 5.dp, bottom = 10.dp, end = 5.dp),
                textModifier = Modifier.padding(vertical = 5.dp),
                onClick = { onStateEvent(AppEvents.ShuffleSideworks) }
            )
        }
    }



    if (state.isEmployeesEditShowing) {

        Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.1f)))

        BottomSheetView(onValueChanged = { onStateEvent(AppEvents.ToggleEditEmployees) }) {
            EmployeeEditListView(state, onStateEvent)
        }
    }

    if (state.isSideworksEditShowing) {

        Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.1f)))

        BottomSheetView(onValueChanged = { onStateEvent(AppEvents.ToggleEditSideworks) }) {
            SideworkEditListView(state, onStateEvent)
        }
    }
}


