package com.example.sneakers_admin_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    background = Gray900,
    onBackground = White,
    primary = Blue,
    onPrimary = White,
    surface = Gray700,
    onSurface = White,
    error = Red,
    onError = White
)

private val LightColorScheme = lightColorScheme(
    background = Gray100,
    onBackground = Black,
    primary = Blue,
    onPrimary = White,
    surface = White,
    onSurface = Black,
    error = Red,
    onError = White,
)

object AppColors {
    val Success = Green
    val Warning = Orange
    val TextMuted = Gray300
}

@Composable
fun Products_AdminTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}