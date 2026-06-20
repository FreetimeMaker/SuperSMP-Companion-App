package com.freetime.ssmpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.freetime.ssmpc.ui.navigation.BottomNavigationBar
import com.freetime.ssmpc.ui.navigation.SuperSMPNavigation
import com.freetime.ssmpc.ui.theme.SuperSMPTheme

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.remember
import com.freetime.ssmpc.collectAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            val sharedPreferences = remember { getSharedPreferences("ssmpc_prefs", Context.MODE_PRIVATE) }
            val useSystemTheme = sharedPreferences.collectAsState(key = "use_system_theme", defaultValue = true)
            val darkModeEnabled = sharedPreferences.collectAsState(key = "dark_mode_enabled", defaultValue = false)
            val dynamicColor = sharedPreferences.collectAsState(key = "dynamic_color", defaultValue = true)
            val oledBlack = sharedPreferences.collectAsState(key = "oled_black", defaultValue = false)

            val darkTheme = if (useSystemTheme.value) isSystemInDarkTheme() else darkModeEnabled.value

            SuperSMPTheme(darkTheme = darkTheme, dynamicColor = dynamicColor.value, oledBlack = oledBlack.value) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    Scaffold(
                        bottomBar = { BottomNavigationBar(navController) }
                    ) { paddingValues ->
                        SuperSMPNavigation(
                            navController = navController,
                            modifier = Modifier.padding(paddingValues)
                        )
                    }
                }
            }
        }
    }
}
