package com.freetime.ssmpc.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.freetime.ssmpc.ui.theme.SuperSMPTheme
import com.freetime.ssmpc.R
import com.freetime.ssmpc.ui.glass.SuperSMPGlassButton
import com.freetime.ssmpc.ui.glass.SuperSMPGlassTopBar
import com.freetime.ssmpc.ui.glass.superSMPGlass

class ChangeLogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        hideSystemBars()
        setContent {
            SuperSMPTheme {
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
        modifier = modifier.fillMaxWidth().superSMPGlass(RoundedCornerShape(24.dp)),
        colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.Transparent)
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
        "v2.0.0" to listOf(
            "Added Liquid Glass throughout the app",
            "Redesigned cards, buttons, dialogs, top bars, and navigation",
            "Added interactive Glass effects and animations",
            "Added a new floating bottom navigation bar",
            "Improved Material You integration",
            "Simplified the main navigation",
            "Added a More menu for Links, Shop, Map, and Settings",
            "Improved navigation and back controls across the app",
            "Added Automatic, Light, and Dark theme modes",
            "Automatic mode switches between light and dark based on the time of day",
            "Unified theme handling across all screens and activities",
            "Improved the Map screen and WebView rendering",
            "Fixed the map turning white or black",
            "Added DOM storage and other required WebView features",
            "Improved zoom and viewport behavior",
            "Redesigned the Coordinates dialog",
            "Improved Home, Settings, Commands, Coordinates, Donation, Wallet, and Changelog screens",
            "Improved WebView controls and fullscreen screens",
            "Added better support for edge-to-edge layouts",
            "Fixed several UI issues",
            "Fixed various Kotlin and layout issues",
            "Improved compatibility with older Android versions",
            "General code cleanup and consistency improvements"
        ),
        "v1.6.1" to listOf(
            "Deleted News Section",
            "Removed FreetimeSDK"
        ),
        "v1.6.0" to listOf(
            "Added FreetimeSDK for Donations",
            "Added something where you can save your Coordinates"
        ),
        "v1.5.0" to listOf(
            stringResource(R.string.changelog_v1_5_0_1),
            stringResource(R.string.changelog_v1_5_0_2),
            stringResource(R.string.changelog_v1_5_0_3),
            stringResource(R.string.changelog_v1_5_0_4)
        ),
        "v1.4.4" to listOf(
            stringResource(R.string.changelog_v1_4_4_1)
        ),
        "v1.4.3" to listOf(
            stringResource(R.string.changelog_v1_4_3_1)
        ),
        "v1.4.2" to listOf(
            stringResource(R.string.changelog_v1_4_2_1)
        ),
        "v1.4.1" to listOf(
            stringResource(R.string.changelog_v1_4_1_1)
        ),
        "v1.4.0" to listOf(
            stringResource(R.string.changelog_v1_4_0_1),
            stringResource(R.string.changelog_v1_4_0_2)
        ),
        "v1.3.0" to listOf(
            stringResource(R.string.changelog_v1_3_0_1),
            stringResource(R.string.changelog_v1_3_0_2),
            stringResource(R.string.changelog_v1_3_0_3)
        )
    )

    Scaffold(
        topBar = {
            SuperSMPGlassTopBar(
                title = stringResource(R.string.whats_new_title),
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

