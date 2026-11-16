package com.freetime.ssmpc.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.freetime.design.AppTheme
import com.freetime.design.ThemeMode
import com.freetime.design.rememberDarkTheme
import com.freetime.design.FloatingBottomNavigationGlassRoot

private val DarkColorScheme = androidx.compose.material3.darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = androidx.compose.material3.lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun rememberSuperSMPAutomaticDarkTheme(): Boolean = rememberDarkTheme(ThemeMode.AUTO_TIME)

@Composable
fun SuperSMPTheme(
    darkTheme: Boolean? = null,
    dynamicColor: Boolean = true,
    oledBlack: Boolean = false,
    content: @Composable () -> Unit
) {
    val useDarkTheme = darkTheme ?: rememberSuperSMPAutomaticDarkTheme()
    val themeMode = when {
        useDarkTheme && oledBlack -> ThemeMode.OLED
        useDarkTheme -> ThemeMode.DARK
        else -> ThemeMode.LIGHT
    }

    AppTheme(themeMode = themeMode, dynamicColor = dynamicColor) {
        // AppTheme owns dynamic colors, expressive motion and navigation glass locals.
        val colors = if (!dynamicColor || android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.S) {
            val fallback = if (useDarkTheme) DarkColorScheme else LightColorScheme
            if (themeMode == ThemeMode.OLED) {
                fallback.copy(
                    background = androidx.compose.ui.graphics.Color.Black,
                    surface = androidx.compose.ui.graphics.Color.Black
                )
            } else fallback
        } else MaterialTheme.colorScheme

        MaterialTheme(colorScheme = colors, typography = GeoTypography) {
            FloatingBottomNavigationGlassRoot {
                content()
            }
        }
    }
}
