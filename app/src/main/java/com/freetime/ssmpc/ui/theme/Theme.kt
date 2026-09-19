package com.freetime.ssmpc.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import android.content.Context
import com.freetime.ssmpc.ui.glass.SuperSMPLiquidGlassRoot
import kotlinx.coroutines.delay
import java.time.LocalTime

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun rememberSuperSMPAutomaticDarkTheme(): Boolean {
    var currentHour by remember { mutableIntStateOf(LocalTime.now().hour) }
    LaunchedEffect(Unit) {
        while (true) {
            currentHour = LocalTime.now().hour
            delay(60_000)
        }
    }
    return currentHour < 7 || currentHour >= 19
}

@Composable
fun SuperSMPTheme(
    darkTheme: Boolean? = null,
    dynamicColor: Boolean = true,
    oledBlack: Boolean = false,
    content: @Composable () -> Unit
) {
    val automaticDark = rememberSuperSMPAutomaticDarkTheme()
    val context = LocalContext.current
    val themeMode = remember { context.getSharedPreferences("ssmpc_prefs", Context.MODE_PRIVATE).getString("theme_mode", "automatic") ?: "automatic" }
    val useDarkTheme = darkTheme ?: when (themeMode) {
        "light" -> false
        "dark" -> true
        else -> automaticDark
    }

    val colorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else if (useDarkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = GeoTypography,
        content = { SuperSMPLiquidGlassRoot(content) }
    )
}
