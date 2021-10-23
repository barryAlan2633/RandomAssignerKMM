package com.example.randomassignerkmm.android.presentation.sidework_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomassignerkmm.android.presentation.components.ButtonView
import com.example.randomassignerkmm.android.presentation.components.IconView
import com.example.randomassignerkmm.android.presentation.components.RoundedCheckView
import com.example.randomassignerkmm.datasource.cache.RandomUUID
import com.example.randomassignerkmm.domain.model.Employee
import com.example.randomassignerkmm.presentation.sidework_list.AppEvents
import com.example.randomassignerkmm.presentation.sidework_list.AppState

@Composable
fun EmployeeEditListView(
    state: AppState,
    onStateEvent: (AppEvents) -> Unit
) {

    val maxLength = 110
    val selected = remember { mutableStateOf(false) }

    //button,text field,text
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        //button,text field
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(0.7f),
                value = state.newEmployeeName,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    disabledLabelColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                onValueChange = {
                    if (it.length <= maxLength) onStateEvent(AppEvents.SetNewEmployeeName(it))
                },
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Enter Employee's name",
                        fontSize = 20.sp,
                        color = Color.LightGray
                    )
                }
//            trailingIcon = {
//                if (textState.isNotEmpty()) {
//                    IconButton(onClick = { textState = "" }) {
//                        Icon(
//                            imageVector = Icons.Outlined.Close,
//                            contentDescription = null
//                        )
//                    }
//                }
//            }

            )

            ButtonView(
                text = state.employeeButtonText,
                textModifier = Modifier
                    .padding(horizontal = 5.dp)
                    .padding(vertical = 5.dp),
                buttonModifier = Modifier.padding(end = 10.dp)
            ) {
                if(state.sideworkButtonText == "Add"){
                    onStateEvent(AppEvents.SaveEmployee(Employee(id = RandomUUID().create() ,name = state.newEmployeeName,isHere = false)))
                }else{//save the edit
                    onStateEvent(AppEvents.SaveEmployee(Employee(id = state.selectedEmployeeID ,name = state.newEmployeeName,isHere = false)))
                }
             }

        }


        Text(
            text = "Employees:${state.employees.size}",
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(start = 15.dp)
        )


        //list
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            items(state.employees) { employee ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    RoundedCheckView(
                        text = employee.name,
                        isChecked = employee.isHere,
                        onTap = {
                             onStateEvent(AppEvents.ToggleIsHere(Employee(id = employee.id ,name = employee.name,isHere = !employee.isHere)))
                        }
                    )


                    Row() {
                        IconView(
                            imageVector = Icons.Default.Edit,
                            iconColor = Color.White,
                            iconSize = 30.dp,
                            circleColor = if (state.isEmployeeEditShowing && state.selectedEmployeeID == employee.id) Color.Red else Color.Black,
                            onSelected = {
                                 onStateEvent(AppEvents.ToggleEditEmployee(employee))
                            },
                            onDeselected = {
                                 onStateEvent(AppEvents.ToggleEditEmployee(employee))
                            })


                        Spacer(Modifier.size(30.dp))

                        IconView(
                            imageVector = Icons.Default.Delete,
                            iconColor = Color.White,
                            iconSize = 30.dp,
                            circleColor = Color.Black,
                            onSelected = {
                                onStateEvent(AppEvents.DeleteEmployee(employee.id))
                            },
                            onDeselected = {}
                        )
                    }
                }
            }
        }
    }
}

