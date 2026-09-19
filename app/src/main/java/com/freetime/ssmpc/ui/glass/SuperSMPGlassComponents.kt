package com.freetime.ssmpc.ui.glass

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SuperSMPGlassCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(24.dp)
    val glassModifier = modifier.superSMPGlass(shape)
    Card(
        modifier = if (onClick != null) glassModifier.clickable(onClick = onClick) else glassModifier,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = shape
    ) { content() }
}

@Composable
fun SuperSMPGlassButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val shape = RoundedCornerShape(20.dp)
    androidx.compose.material3.Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.superSMPGlass(shape),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        content = content
    )
}

@Composable
fun SuperSMPGlassTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null
) {
    androidx.compose.material3.TopAppBar(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .superSMPGlass(RoundedCornerShape(28.dp), interactive = false),
        title = { Text(title) },
        navigationIcon = { navigationIcon?.invoke() },
        colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        )
    )
}
