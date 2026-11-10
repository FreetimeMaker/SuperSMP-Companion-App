package com.freetime.ssmpc.ui.glass

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape

@Composable
fun SuperSMPLiquidGlassRoot(content: @Composable () -> Unit) {
    content()
}

@Suppress("UNUSED_PARAMETER") // Retained for source compatibility with existing callers.
@Composable
fun Modifier.superSMPGlass(
    shape: Shape,
    interactive: Boolean = true,
): Modifier = clip(shape).background(MaterialTheme.colorScheme.surface)
