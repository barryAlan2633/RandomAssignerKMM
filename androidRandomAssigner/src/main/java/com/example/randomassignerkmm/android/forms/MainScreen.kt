package com.example.randomassignerkmm.android.forms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.randomassignerkmm.android.views.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jetbrains.handson.kmm.shared.AssignerSDK


@Composable
fun MainScreen(sdk: AssignerSDK) {
    val systemUiController = rememberSystemUiController()
    val isSideWorkBottomSheetShown = remember { mutableStateOf(false) }
    val isEmployeeBottomSheetShown = remember { mutableStateOf(false) }

    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = true
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9405f)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(110, 150, 255, 255),
                        Color.White,
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
                )

            ),
        content = {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {

                Column(
                    Modifier.weight(5f)
                ){
                    SideWorkListView(sideWorks = sdk.getSideWorks())

                }


                Column(
                    Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ButtonView(
                            text = "Side Work",
                            buttonModifier = Modifier
                                .weight(1f)
                                .height(58.dp)
                                .padding(start = 5.dp, bottom = 10.dp, end = 5.dp)
                            ,
                            textModifier = Modifier.padding(vertical = 5.dp),
                            onClick = { isSideWorkBottomSheetShown.value = true })
                        ButtonView(
                            text = "Employees",
                            buttonModifier = Modifier
                                .weight(1f)
                                .height(58.dp)
                                .padding(start = 5.dp, bottom = 10.dp, end = 5.dp)
                            ,
                            textModifier = Modifier.padding(vertical = 5.dp),
                            onClick = { isEmployeeBottomSheetShown.value = true })
                    }
                    ButtonView(
                        text = "Shuffle",
                        buttonModifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(58.dp)
                            .padding(start = 5.dp, bottom = 10.dp, end = 5.dp)
                          ,
                        textModifier = Modifier.padding(vertical = 5.dp),
                        onClick = {})
                }
            }



            if (isEmployeeBottomSheetShown.value) {

                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.1f))
                )
                BottomSheetView(
                    onValueChanged = {
                        isEmployeeBottomSheetShown.value = false
                    },
                    content = {
                        EmployeeEditListView(sdk)
                    }

                )


            }

            if (isSideWorkBottomSheetShown.value) {

                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.1f))
                )
                BottomSheetView(
                    onValueChanged = {
                        isSideWorkBottomSheetShown.value = false
                    },
                    content = {
                        SideWorkEditListView(sdk)
                    }

                )


            }

        }
    )
}


//            val employees = remember { mutableStateListOf<Employee>() }
//            employees.addAll(SharedData().getEmployeeList().shuffled())
