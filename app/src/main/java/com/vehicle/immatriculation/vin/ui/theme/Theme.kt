package com.vehicle.immatriculation.vin.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme =
    darkColorScheme(
        primary = primary,
        background = backgroundNight,
        surface = surfaceNight,
        onBackground = white,
        onPrimary = white,
    )

private val LightColorScheme =
    lightColorScheme(
        primary = primary,
        background = backgroundDay,
        surface = surfaceDay,
        onBackground = black,
        onPrimary = black,
        surfaceVariant = card,
    )

@Composable
fun AutoScanAppTheme(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val initialThemePref = loadThemePreference(context)
    val themePreference = remember { mutableStateOf(initialThemePref) }
    val isSystemDarkTheme = isSystemInDarkTheme()

    val colorScheme =
        when (themePreference.value) {
            ThemePreference.DARK -> DarkColorScheme
            ThemePreference.LIGHT -> LightColorScheme
            ThemePreference.SYSTEM -> if (isSystemDarkTheme) DarkColorScheme else LightColorScheme
        }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isSystemDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
