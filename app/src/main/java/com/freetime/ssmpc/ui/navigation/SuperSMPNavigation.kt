package com.freetime.ssmpc.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.freetime.ssmpc.ui.screens.HomeScreen
import com.freetime.ssmpc.ui.screens.ServerCommandScreen
import com.freetime.ssmpc.ui.screens.LinksScreen
import com.freetime.ssmpc.ui.screens.ShopScreen
import com.freetime.ssmpc.ui.screens.MapScreen

@Composable
fun SuperSMPNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen()
        }
        composable("servercmd") {
            ServerCommandScreen()
        }
        composable("links") {
            LinksScreen()
        }
        composable("shop") {
            ShopScreen()
        }
        composable("map") {
            MapScreen()
        }
    }
}
