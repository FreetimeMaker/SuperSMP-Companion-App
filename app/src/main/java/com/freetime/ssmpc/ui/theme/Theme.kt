package com.freetime.ssmpc.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

import androidx.compose.ui.graphics.Color

import com.freetime.design.ThemeMode
import com.freetime.design.rememberDarkTheme
import com.freetime.design.templates.expressive.AppTheme

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
    AppTheme(darkTheme = useDarkTheme, dynamicColor = dynamicColor) {
        if (useDarkTheme && oledBlack) {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme.copy(
                    background = Color.Black,
                    surface = Color.Black,
                ),
                content = content,
            )
        } else {
            content()
        }
    }
}
