package com.freetime.ssmpc.ui.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SuperSMPGlassCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    if (onClick == null) {
        Card(modifier = modifier) {
            Box(modifier = Modifier.padding(16.dp)) { content() }
        }
    } else {
        Card(modifier = modifier, onClick = onClick) {
            Box(modifier = Modifier.padding(16.dp)) { content() }
        }
    }
}

@Composable
fun SuperSMPGlassButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        content = content
    )
}

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun SuperSMPGlassTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null
) {
    androidx.compose.material3.TopAppBar(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .superSMPGlass(shape = MaterialTheme.shapes.large, interactive = false),
        title = { Text(title, color = MaterialTheme.colorScheme.onSurface) },
        navigationIcon = { navigationIcon?.invoke() },
        colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            scrolledContainerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}
