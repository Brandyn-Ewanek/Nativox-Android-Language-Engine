package com.example.nativox.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// 1. Update Dark Mode to use your brand (Optional, but good practice)
private val DarkColorScheme = darkColorScheme(
    primary = Teal,
    secondary = BrandOrange,
    tertiary = BrandPurple,
    background = Navy,
    surface = Navy
)

// 2. Update Light Mode to use NATIVOX colors
private val LightColorScheme = lightColorScheme(
    primary = Navy,        // Main buttons and headers
    secondary = Teal,      // Accents
    tertiary = BrandOrange,// Highlights
    background = Cream,    // The warm paper background
    surface = Cream,       // Cards and sheets
    onPrimary = Cream,     // Text on Navy buttons
    onSecondary = Cream,   // Text on Teal buttons
    onBackground = Navy,   // Text on background
    onSurface = Navy       // Text on cards
)

@Composable
fun NativoxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}