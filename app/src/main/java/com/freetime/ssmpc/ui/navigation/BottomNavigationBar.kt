package com.freetime.ssmpc.ui.navigation

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.freetime.ssmpc.R
import com.freetime.ssmpc.ui.glass.superSMPGlass

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

    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .superSMPGlass(RoundedCornerShape(34.dp), interactive = false)
            .horizontalScroll(rememberScrollState())
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                onClick = { navigate(navController, item.route) }
            )
        }
    }
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
