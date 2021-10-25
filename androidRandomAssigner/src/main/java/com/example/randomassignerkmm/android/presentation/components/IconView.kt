package com.example.randomassignerkmm.android.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun IconView(
    imageVector: ImageVector,
    iconColor: Color,
    iconSize: Dp,
    circleColor: Color,
    onSelected: () -> Unit,
    onDeselected: () -> Unit
) {
    val isChecked = remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .background(circleColor)
    ) {

        Icon(
            imageVector = imageVector,
            contentDescription = "",
            tint = iconColor,
            modifier = Modifier
                .toggleable(
                    value = isChecked.value,
                    role = Role.Switch,
                    onValueChange = {
                        isChecked.value = it

                        if (isChecked.value) {
                            onSelected()
                        } else {
                            onDeselected()
                        }
                    }
                )
                .size(iconSize)
                .padding(5.dp)
        )
    }


}