package com.freetime.ssmpc.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.freetime.ssmpc.collectAsState
import com.freetime.ssmpc.ui.theme.SuperSMPTheme
import com.freetime.ssmpc.R

class ChangeLogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        hideSystemBars()
        setContent {
            val sharedPreferences = remember { getSharedPreferences("ssmpc_prefs",
                MODE_PRIVATE
            ) }
            val useSystemTheme = sharedPreferences.collectAsState(key = "use_system_theme", defaultValue = true)
            val darkModeEnabled = sharedPreferences.collectAsState(key = "dark_mode_enabled", defaultValue = false)
            val dynamicColor = sharedPreferences.collectAsState(key = "dynamic_color", defaultValue = true)
            
            val darkTheme = if (useSystemTheme.value) isSystemInDarkTheme() else darkModeEnabled.value

            SuperSMPTheme(darkTheme = darkTheme, dynamicColor = dynamicColor.value) {
                ChangeLogScreen(onBack = { finish() })
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
fun ReleaseCard(
    version: String,
    details: List<String>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = version,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            details.forEach { line ->
                Text(
                    text = "• $line",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeLogScreen(onBack: () -> Unit) {
    val releases = listOf(
        "v1.4.3" to listOf(
            "Fixed Hopefully the App Icon"
        ),
        "v1.4.2" to listOf(
            "Fixed Icon pixeling"
        ),
        "v1.4.1" to listOf(
            "Fixed the Monochrome Icon not rendering"
        ),
        "v1.4.0" to listOf(
            "Added Monochrome Icon",
            "Fixed Donations not Opening via WebView"
        ),
        "v1.3.0" to listOf(
            "Added ChangeLog",
            "Added Donate Screen",
            "Added more Commands"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.whats_new_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back_nav_desc))
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            releases.forEach { (version, details) ->
                ReleaseCard(version = version, details = details)
            }
        }
    }
}
