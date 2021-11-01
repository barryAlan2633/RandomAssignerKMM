package com.barryalan.randomassignerkmm.android.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonView(
    text: String,
    textModifier: Modifier = Modifier,
    buttonModifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        modifier = buttonModifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(Color.Black)
    ) {
        Text(
            modifier = textModifier,
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal
        )
    }
}