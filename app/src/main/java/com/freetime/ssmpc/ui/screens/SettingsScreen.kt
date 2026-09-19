package com.freetime.ssmpc.ui.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.freetime.ssmpc.R
import com.freetime.ssmpc.ui.glass.superSMPGlass
import com.freetime.ssmpc.ui.glass.SuperSMPGlassButton
import com.freetime.ssmpc.ui.theme.SuperSMPTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        hideSystemBars()

        setContent {
            SuperSMPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SettingsScreen(
                        modifier = Modifier.padding(innerPadding),
                        onBack = { finish() }
                    )
                }
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemBars()
        }
    }

    private fun hideSystemBars() {
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences("ssmpc_prefs", Context.MODE_PRIVATE)
    }

    var themeMode by remember {
        mutableStateOf(sharedPreferences.getString("theme_mode", "automatic") ?: "automatic")
    }

    var disablePrivateView by remember {
        mutableStateOf(sharedPreferences.getBoolean("disable_private_view", false))
    }

    var openExternalBrowser by remember {
        mutableStateOf(sharedPreferences.getBoolean("open_external_browser", false))
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().superSMPGlass(RoundedCornerShape(24.dp)),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilledTonalButton(onClick = {
                context.startActivity(
                    Intent(
                        context,
                        DonateActivity::class.java
                    )
                )
            }) {
                Text(stringResource(R.string.open_donation_screen))
            }
            FilledTonalButton(onClick = {
                context.startActivity(
                    Intent(
                        context,
                        ChangeLogActivity::class.java
                    )
                )
            }) {
                Text(stringResource(R.string.open_change_log))
            }
        }

        Text(text = stringResource(R.string.theme_settings_title), style = MaterialTheme.typography.headlineSmall)
        Card(
            modifier = Modifier.fillMaxWidth().superSMPGlass(RoundedCornerShape(24.dp)),
            colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.Transparent)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Theme", style = MaterialTheme.typography.titleMedium)
                SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                    listOf("automatic" to "Automatic", "light" to "Light", "dark" to "Dark").forEachIndexed { index, (value, label) ->
                        SegmentedButton(
                            selected = themeMode == value,
                            onClick = {
                                themeMode = value
                                sharedPreferences.edit().putString("theme_mode", value).apply()
                            },
                            shape = SegmentedButtonDefaults.itemShape(index, 3)
                        ) { Text(label) }
                    }
                }
            }
        }

        Text(text = stringResource(R.string.webview_settings_title), style = MaterialTheme.typography.headlineSmall)
        Card(
            modifier = Modifier.fillMaxWidth().superSMPGlass(RoundedCornerShape(24.dp)),
            colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.Transparent)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SettingsToggle(
                    title = stringResource(R.string.disable_private_view),
                    subtitle = stringResource(R.string.disable_private_view_subtitle),
                    checked = disablePrivateView,
                    onCheckedChange = {
                        disablePrivateView = it
                        sharedPreferences.edit().putBoolean("disable_private_view", it).apply()
                    }
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                SettingsToggle(
                    title = stringResource(R.string.open_external_browser),
                    subtitle = stringResource(R.string.open_external_browser_subtitle),
                    checked = openExternalBrowser,
                    onCheckedChange = {
                        openExternalBrowser = it
                        sharedPreferences.edit().putBoolean("open_external_browser", it).apply()
                    }
                )
            }
        }
    }
}

@Composable
fun SettingsToggle(
    title: String,
    subtitle: String? = null,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().superSMPGlass(RoundedCornerShape(24.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyLarge)
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        Switch(
            checked = checked,
            enabled = enabled,
            onCheckedChange = onCheckedChange
        )
    }
}
