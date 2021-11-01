package com.barryalan.randomassignerkmm.android.presentation.theme

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.barryalan.randomassignerkmm.android.presentation.components.ProcessDialogQueue
import com.barryalan.randomassignerkmm.domain.model.GenericMessageInfo
import com.barryalan.randomassignerkmm.domain.util.Queue
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Purple100,
    primaryVariant = Purple200,
    secondary = Teal200,
    onBackground = Color.White,
    background = BlueBackground,
    onSurface = Color.White

)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    onBackground = Color.Black,
    background = BlueBackground,
    onSurface = Color.Black

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dialogQueue: Queue<GenericMessageInfo> = Queue(mutableListOf()),
    onRemoveHeadMessageFromQueue: () -> Unit,
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()
    if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = Color.Black
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    }

    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = true
    )

    val padding: PaddingValues
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            padding = PaddingValues(bottom = 0.dp, start = 0.dp, end = 0.dp, top = 0.dp)
        }
        else -> {
            padding = PaddingValues(bottom = 50.dp, start = 0.dp, end = 0.dp, top = 0.dp)
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
    ) {

        Box(

            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(padding)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colors.background,
                            Color.White,
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
                    )

                ),
        ) {
            ProcessDialogQueue(
                dialogQueue = dialogQueue,
                onRemoveHeadMessageFromQueue = onRemoveHeadMessageFromQueue,
            )
            content()

        }
    }
}