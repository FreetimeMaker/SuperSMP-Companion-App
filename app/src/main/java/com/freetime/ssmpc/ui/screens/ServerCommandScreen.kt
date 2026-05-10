package com.freetime.ssmpc.ui.screens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.freetime.ssmpc.collectAsState
import com.freetime.ssmpc.ui.theme.SuperSMPTheme

class ServerCommandActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        hideSystemBars()

        setContent {
            val sharedPreferences = remember { getSharedPreferences("ssmpc_prefs", MODE_PRIVATE) }
            val useSystemTheme = sharedPreferences.collectAsState(key = "use_system_theme", defaultValue = true)
            val darkModeEnabled = sharedPreferences.collectAsState(key = "dark_mode_enabled", defaultValue = false)
            val dynamicColor = sharedPreferences.collectAsState(key = "dynamic_color", defaultValue = true)

            val darkTheme = if (useSystemTheme.value) isSystemInDarkTheme() else darkModeEnabled.value

            SuperSMPTheme(darkTheme = darkTheme, dynamicColor = dynamicColor.value) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ServerCommandScreen(
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
fun ServerCommandScreen(
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Server Commands",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Basic Commands
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Basic Commands",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("/spawn - Teleport to spawn")
                Text("/rtp - Telport to a random location")
                Text("/home - Teleport to your home if you have one")
                Text("/sethome - Set your home location")
                Text("/warp <name> - Teleport to warp")
                Text("/pwarp <name> - Teleport to a players warp")
                Text("/pvp - Activate/Deactivate PVP")
                Text("/tpask <player> - Send teleport request")
                Text("/tpaccept - Accept teleport request")
                Text("/tpadeny - Deny teleport request")
                Text("/msg <player> <message> - Talk to a Player Privately")
                Text("/warp center - Go to Welcome Center")
                Text("/kiss <player> - Kiss a player")
                Text("/warp arena - Teleport to Arena for fighting agains other Players")
                Text("/fly - Activate/Deactivate flying")
                Text("/trash - Dispose of useless items")
                Text("/wb - Welcome a Player back")
                Text("/warp crates - Open crates when you get keys")
            }
        }

        // Economy Commands
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Economy Commands",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("/bal - Check your balance")
                Text("/pay <player> <amount> - Pay someone")
                Text("/baltop - Top balances")
                Text("/shop - Buy or Sell Items")
                Text("/sellg - Quickly sell items within a GUI")
            }
        }

        // Land Claim Commands
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Land Claim Commands",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("/claim - Claim current chunk")
                Text("/unclaim - Unclaim current chunk")
                Text("/claims - List your claims")
                Text("/trust <player> - Trust player")
                Text("/untrust <player> - Untrust player")
                Text("/trustlist - List trusted players")
                Text("/land ban <player> - Ban a Player from your land")
                Text("/land delete - Delete your land")
            }
        }

        if (onBack != null) {
            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go Back")
            }
        }
    }
}
