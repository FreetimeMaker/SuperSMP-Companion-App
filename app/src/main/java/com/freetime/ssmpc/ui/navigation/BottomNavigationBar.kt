package com.freetime.ssmpc.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var moreExpanded by remember { mutableStateOf(false) }

    val primaryItems = listOf(
        BottomNavItem("home", stringResource(R.string.nav_home), Icons.Default.Home),
        BottomNavItem("coords", stringResource(R.string.nav_coords), Icons.Default.PinDrop),
        BottomNavItem("servercmd", stringResource(R.string.nav_cmds), Icons.Default.Terminal)
    )

    NavigationBar(
        modifier = Modifier
            .padding(horizontal = 28.dp, vertical = 10.dp)
            .superSMPGlass(RoundedCornerShape(34.dp), interactive = false),
        containerColor = Color.Transparent,
        tonalElevation = 0.dp
    ) {
        primaryItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                onClick = { navigate(navController, item.route) }
            )
        }

        NavigationBarItem(
            icon = { Icon(Icons.Default.MoreHoriz, contentDescription = "More") },
            label = { Text("More") },
            selected = currentRoute in setOf("links", "shop", "map", "settings"),
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
            onClick = { moreExpanded = true }
        )
        DropdownMenu(expanded = moreExpanded, onDismissRequest = { moreExpanded = false }) {
            listOf(
                "links" to stringResource(R.string.nav_links),
                "shop" to stringResource(R.string.nav_shop),
                "map" to stringResource(R.string.nav_map),
                "settings" to stringResource(R.string.nav_settings)
            ).forEach { (route, label) ->
                DropdownMenuItem(
                    text = { Text(label) },
                    onClick = {
                        moreExpanded = false
                        navigate(navController, route)
                    }
                )
            }
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
