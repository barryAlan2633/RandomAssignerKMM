package com.example.randomassignerkmm.android.presentation.components

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
import androidx.compose.material.icons.filled.Close
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
    modifier:Modifier,
    text: String,
    isChecked:Boolean,
    onTap:() -> Unit,
) {

    val circleSize = remember { mutableStateOf(20.dp) }
    val circleThickness = remember { mutableStateOf(2.dp) }
    val color = remember { mutableStateOf(Color.Gray) }

    if (isChecked) {
        circleSize.value = 40.dp
        circleThickness.value = 3.dp
        color.value = Color.Black
    } else {
        circleSize.value = 20.dp
        circleThickness.value = 2.dp
        color.value = Color.Gray
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .toggleable(value = isChecked,role = Role.Checkbox) {
                onTap()
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
            if(isChecked){
                Icon(imageVector = Icons.Default.Check, contentDescription = "", tint = Color.Black)
            }else{
                Icon(imageVector = Icons.Default.Close, contentDescription = "", tint = Color.Gray)
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