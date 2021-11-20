package com.barryalan.randomassignerkmm.android.presentation.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun RoundedCheckView(
    modifier:Modifier,
    text: String,
    isChecked:Boolean,
    onTap:() -> Unit,
) {


    val iconColor by animateColorAsState(
        if(isChecked) Color.Black else Color.Gray
    )

    val circleS by animateDpAsState(
        if(isChecked) 40.dp else 20.dp
    )

    val circleT by animateDpAsState(
        if(isChecked) 3.dp else 2.dp
    )

//    val iconAlpha by animateFloatAsState(
//        if(isChecked) 1f else .5f
//    )


//    val circleSize = remember { mutableStateOf(20.dp) }
//    val circleThickness = remember { mutableStateOf(2.dp) }
//    val color = remember { mutableStateOf(Color.Gray) }
//
//    if (isChecked) {
//        circleSize.value = 40.dp
//        circleThickness.value = 3.dp
//        color.value = Color.Black
//    } else {
//        circleSize.value = 20.dp
//        circleThickness.value = 2.dp
//        color.value = Color.Gray
//    }

    var alpha = 1f
    val animatedAlpha by animateFloatAsState(targetValue = alpha)

    LaunchedEffect(key1 = isChecked){
        //Triggered upon key change. Change the value of trigger upon a button click or similar event.
        alpha = 0f // First we make it go all the way down
        delay(100) // Slight Delay, as a pause
        alpha = 1f // Bring it up
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
                .size(circleS)
                .background(iconColor)
                .padding(circleT)
                .clip(CircleShape)
                .background(Color.White)  ,
            contentAlignment = Alignment.Center
        ) {
//            Icon(imageVector = if(isChecked) Icons.Default.Check else Icons.Default.Close ,
//                contentDescription = "",
//                tint = iconColor
//            )

            Crossfade(targetState = isChecked) { isChecked ->
                // note that it's required to use the value passed by Crossfade
                // instead of your state value
                if (isChecked) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "", tint = Color.Black)
                } else {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "", tint = Color.Gray)
                }
            }

         }

        Text(
            text = text,
            color = iconColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 5.dp)
        )
    }

}