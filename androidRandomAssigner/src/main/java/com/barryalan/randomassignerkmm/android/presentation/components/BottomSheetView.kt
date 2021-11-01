package com.barryalan.randomassignerkmm.android.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetView(
    onValueChanged: () -> Unit,
    content: @Composable () -> Unit

) {

    Box(
        Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 100.dp)

    ) {

        Box(
            modifier = Modifier
                .shadow(
                    elevation = 30.dp,
                    shape = RoundedCornerShape(30.dp)
                )
                .fillMaxSize()
                .background(Color.White, RoundedCornerShape(30.dp))
        )

        Column(
            modifier = Modifier
                .padding(top = 15.dp)
                .padding(horizontal = 5.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier.weight(9f).padding(bottom = 10.dp)) {
                content()

            }


            ButtonView(
                text = "Finished",
                buttonModifier = Modifier
                    .fillMaxWidth(0.75f)
                    .height(60.dp)
                    .padding(start = 5.dp, bottom = 10.dp, end = 5.dp)
            ) {
                onValueChanged()
            }
        }

    }
}