package com.freetime.ssmpc.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.freetime.ssmpc.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomAppBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState().value
        val currentRoute = navBackStackEntry?.destination?.route
        
        val items = listOf(
            BottomNavItem("home", stringResource(R.string.nav_home), Icons.Default.Home),
            BottomNavItem("servercmd", stringResource(R.string.nav_cmds), Icons.Default.Terminal),
            BottomNavItem("links", stringResource(R.string.nav_links), Icons.Default.Link),
            BottomNavItem("shop", stringResource(R.string.nav_shop), Icons.Default.ShoppingCart),
            BottomNavItem("map", stringResource(R.string.nav_map), Icons.Default.Map),
            BottomNavItem("settings", stringResource(R.string.nav_settings), Icons.Default.Settings)
        )
        
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)
