package com.freetime.ssmpc.ui.glass

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.free_time.design.FreetimeGlassCard
import me.free_time.design.freetimeGlassCapsule

@Composable
fun SuperSMPGlassCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    if (onClick == null) {
        FreetimeGlassCard(modifier = modifier, content = content)
    } else {
        Box(
            modifier = modifier
                .freetimeGlassCapsule()
                .clickable(onClick = onClick)
                .padding(16.dp)
        ) { content() }
    }
}

@Composable
fun SuperSMPGlassButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .freetimeGlassCapsule()
            .then(if (enabled) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(horizontal = 20.dp, vertical = 12.dp),
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
            .freetimeGlassCapsule(interactive = false),
        title = { Text(title, color = MaterialTheme.colorScheme.onSurface) },
        navigationIcon = { navigationIcon?.invoke() },
        colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
            containerColor = androidx.compose.ui.graphics.Color.Transparent,
            scrolledContainerColor = androidx.compose.ui.graphics.Color.Transparent,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}
