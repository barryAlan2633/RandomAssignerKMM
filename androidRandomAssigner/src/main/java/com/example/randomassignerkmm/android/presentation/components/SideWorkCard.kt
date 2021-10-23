package com.example.randomassignerkmm.android.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomassignerkmm.domain.model.Sidework
import java.util.*


@Composable
fun SideWorkCard(sideWork: Sidework) {


    Column(modifier = Modifier.padding(5.dp)) {
        if (sideWork.todoToday) {
            Text(
                text = sideWork.name.replaceFirstChar { it.uppercase() },
                color = Color.Black,
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium
            )


            Text(
                text = sideWork.employees.map {
                    it.name.replaceFirstChar { c->c.uppercase() }
                }.joinToString(", "),
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )

        }

    }
}
