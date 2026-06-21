package com.freetime.ssmpc.ui.screens

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.freetime.ssmpc.R
import com.freetime.ssmpc.collectAsState
import com.freetime.ssmpc.ui.theme.SuperSMPTheme
import com.freetime.ssmpc.ui.viewmodels.ServerStatusViewModel
import java.util.concurrent.TimeUnit
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        hideSystemBars()

        setContent {
            val sharedPreferences = remember { getSharedPreferences("ssmpc_prefs", Context.MODE_PRIVATE) }
            val useSystemTheme = sharedPreferences.collectAsState(key = "use_system_theme", defaultValue = true)
            val darkModeEnabled = sharedPreferences.collectAsState(key = "dark_mode_enabled", defaultValue = false)
            val dynamicColor = sharedPreferences.collectAsState(key = "dynamic_color", defaultValue = true)
            val oledBlack = sharedPreferences.collectAsState(key = "oled_black", defaultValue = false)

            val darkTheme = if (useSystemTheme.value) isSystemInDarkTheme() else darkModeEnabled.value

            SuperSMPTheme(darkTheme = darkTheme, dynamicColor = dynamicColor.value, oledBlack = oledBlack.value) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
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
fun HomeScreen(
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    statusViewModel: ServerStatusViewModel = viewModel()
) {
    val status by statusViewModel.status.collectAsState()
    val isLoading by statusViewModel.isLoading.collectAsState()
    val context = LocalContext.current
    val sharedPreferences = remember { context.getSharedPreferences("ssmpc_prefs", Context.MODE_PRIVATE) }
    
    val lastVoteTime = sharedPreferences.getLong("last_vote_time", 0L)
    val currentTime = System.currentTimeMillis()
    val timePassed = currentTime - lastVoteTime
    val isVoteAvailable = timePassed >= TimeUnit.HOURS.toMillis(24)

    LaunchedEffect(Unit) {
        statusViewModel.refreshStatus("supersmp.fun")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.home_title),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Server Status Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (status?.online == true) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = if (status?.online == true) "Server Online" else if (isLoading) "Loading..." else "Server Offline",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                if (status?.online == true) {
                    Text(text = "Players: ${status?.players?.online}/${status?.players?.max}")
                    Text(text = "Version: ${status?.version}")
                }
                Button(onClick = { statusViewModel.refreshStatus("supersmp.fun") }, enabled = !isLoading) {
                    Text("Refresh Status")
                }
            }
        }

        // Vote Reminder Card
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Daily Vote Reminder", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                if (isVoteAvailable) {
                    Text("You can vote now for rewards!")
                } else {
                    val hoursLeft = 24 - TimeUnit.MILLISECONDS.toHours(timePassed)
                    Text("Next vote available in approx. $hoursLeft hours.")
                }
                Button(
                    onClick = { 
                        sharedPreferences.edit().putLong("last_vote_time", System.currentTimeMillis()).apply()
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("I just voted!")
                }
            }
        }

        // News Section
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Latest News", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("• Version 1.5.0 is out with many new features!")
                Text("• Join the Discord for more updates.")
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.home_welcome_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.home_description),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.home_server_info_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(stringResource(R.string.home_ip))
                Text(stringResource(R.string.home_port))
                Text(stringResource(R.string.home_version))
                Text(stringResource(R.string.home_type))
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.home_features_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(stringResource(R.string.home_feature_crates))
                Text(stringResource(R.string.home_feature_economy))
                Text(stringResource(R.string.home_feature_land_claim))
            }
        }

        if (onBack != null) {
            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.go_back))
            }
        }
    }
}
