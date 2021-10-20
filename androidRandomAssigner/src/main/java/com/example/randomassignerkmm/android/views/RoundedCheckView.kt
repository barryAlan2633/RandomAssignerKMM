package com.example.randomassignerkmm.android.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RoundedCheckView(
    text: String
) {

    val isChecked = remember { mutableStateOf(false) }
    val checkedText = remember { mutableStateOf(text) }
    val circleSize = remember { mutableStateOf(20.dp) }
    val circleThickness = remember { mutableStateOf(2.dp) }
    val color = remember { mutableStateOf(Color.Gray) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .toggleable(value = isChecked.value,role = Role.Checkbox) {
                isChecked.value = it

                if (isChecked.value) {
                     circleSize.value = 40.dp
                    circleThickness.value = 3.dp
                    color.value = Color.Black
                } else {
                    circleSize.value = 20.dp
                    circleThickness.value = 2.dp
                    color.value = Color.Gray
                }
            }) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(circleSize.value)
                .background(color.value)
                .padding(circleThickness.value)
                .clip(CircleShape)
                .background(Color.White)  ,
            contentAlignment = Alignment.Center
        ) {
            if(isChecked.value){
                Icon(imageVector = Icons.Default.Check, contentDescription = "")
            }
         }

        Text(
            text = text,
            color = color.value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 5.dp)
        )
    }

}