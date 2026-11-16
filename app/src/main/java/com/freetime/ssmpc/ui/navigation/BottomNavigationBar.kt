package com.freetime.ssmpc.ui.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Icon

import androidx.compose.runtime.Composable

import androidx.compose.ui.res.stringResource

import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.freetime.ssmpc.R
import com.freetime.design.FloatingBottomNavigationBar
import com.freetime.design.FloatingBottomNavigationItem

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val items = listOf(
        BottomNavItem("home", stringResource(R.string.nav_home), Icons.Default.Home),
        BottomNavItem("coords", stringResource(R.string.nav_coords), Icons.Default.PinDrop),
        BottomNavItem("servercmd", stringResource(R.string.nav_cmds), Icons.Default.Terminal),
        BottomNavItem("links", stringResource(R.string.nav_links), Icons.Default.Link),
        BottomNavItem("shop", stringResource(R.string.nav_shop), Icons.Default.ShoppingCart),
        BottomNavItem("map", stringResource(R.string.nav_map), Icons.Default.Map),
        BottomNavItem("settings", stringResource(R.string.nav_settings), Icons.Default.Settings)
    )

    val tabs = items.dropLast(1)
    val settings = items.last()
    // The library's separate action is required; use the existing Settings destination.
    FloatingBottomNavigationBar(
        items = tabs.map { item ->
            FloatingBottomNavigationItem(
                label = item.title,
                icon = { Icon(item.icon, contentDescription = item.title) }
            )
        },
        selectedItemIndex = tabs.indexOfFirst { it.route == currentRoute },
        onItemSelected = { index -> navigate(navController, tabs[index].route) },
        searchItem = FloatingBottomNavigationItem(
            label = settings.title,
            icon = { Icon(settings.icon, contentDescription = settings.title) }
        ),
        onSearchSelected = { navigate(navController, settings.route) },
        searchSelected = currentRoute == settings.route
    )
}

private fun navigate(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId)
        launchSingleTop = true
    }
}

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)
