package com.example.randomassignerkmm.android.views

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
import com.jetbrains.handson.kmm.shared.AssignerSDK
import com.jetbrains.handson.kmm.shared.entity.Employee
import java.util.*

@Composable
fun EmployeeEditListView(sdk: AssignerSDK) {
    var name by remember { mutableStateOf("") }
    val maxLength = 110

    val number = 20

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
                value = name,
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
                    if (it.length <= maxLength) name = it
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
                text = "Add",
                textModifier = Modifier
                .padding(horizontal = 5.dp).padding(vertical = 5.dp),
                buttonModifier = Modifier.padding(end = 10.dp)
            ) {
                //todo change ids
//                sdk.saveEmployee(Employee(id = 1 ,name = name,isHere = false))

            }

        }


        Text(
            text = "Employees:$number",
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
            items(sdk.getEmployees()) { employee ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    RoundedCheckView(text = employee.name)


                    Row() {
                        IconView(
                            imageVector = Icons.Default.Edit,
                            iconColor = Color.White,
                            iconSize = 30.dp,
                            circleColor = Color.Black,
                            onSelected = { }) {
                        }

                        Spacer(Modifier.size(30.dp))

                        IconView(
                            imageVector = Icons.Default.Delete,
                            iconColor = Color.White,
                            iconSize = 30.dp,
                            circleColor = Color.Black,
                            onSelected = { sdk.deleteEmployee(employee)}) {
                        }
                    }

                }
            }
        }

    }
}

//todo finish this view