package com.freetime.ssmpc.ui.glass

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import me.free_time.design.FreetimeGlassRoot
import me.free_time.design.freetimeGlass

@Composable
fun SuperSMPLiquidGlassRoot(content: @Composable () -> Unit) {
    FreetimeGlassRoot(content = content)
}

@Composable
fun Modifier.superSMPGlass(
    shape: Shape,
    interactive: Boolean = true,
): Modifier = freetimeGlass(shape = shape, interactive = interactive)
