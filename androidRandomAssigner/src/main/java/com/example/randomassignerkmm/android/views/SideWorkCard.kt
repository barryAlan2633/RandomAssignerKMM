package com.example.randomassignerkmm.android.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomassignerkmm.sideW
import com.jetbrains.handson.kmm.shared.entity.Sidework


@Composable
fun SideWorkCard(sideWork: Sidework) {

    val fontWeight:FontWeight
    val fontSize: TextUnit

    if(sideWork.isDueToday){
        fontSize = 25.sp
        fontWeight = FontWeight.Medium
    }else{
        fontSize = 15.sp
        fontWeight = FontWeight.Light
    }

    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = sideWork.name,
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = fontWeight
            )

        if(sideWork.isDueToday){
            Text(
                text = sideWork.employee?.name?: "",//.joinToString(", "),
                fontWeight = FontWeight.Medium,
                fontSize = 25.sp
            )

        }

    }
}
