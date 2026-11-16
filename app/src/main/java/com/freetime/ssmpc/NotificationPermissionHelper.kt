package com.freetime.ssmpc

import android.Manifest
import android.content.Intent
import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.freetime.notifications.FreetimeNotifications

/** Place in an Activity-hosted composition; never prompts merely by entering composition. */
@Composable
fun NotificationPermissionHelper(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val preferences = context.getSharedPreferences("notification_permission", Context.MODE_PRIVATE)
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { granted ->
        if (granted && FreetimeNotifications.hasPermission(context)) {
            FreetimeNotifications.requestCheck(context)
        }
    }
    val openSettings = {
        context.startActivity(
            Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                .putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName),
        )
    }

    Column(modifier = modifier) {
        Text(stringResource(R.string.notification_permission_title))
        Text(stringResource(R.string.notification_permission_explanation))
        Button(onClick = {
            if (FreetimeNotifications.hasPermission(context)) {
                FreetimeNotifications.requestCheck(context)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                !preferences.getBoolean("requested", false)
            ) {
                preferences.edit().putBoolean("requested", true).apply()
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                openSettings()
            }
        }) {
            Text(stringResource(R.string.notification_permission_enable))
        }
        // Always provide a route for denied permission and user-disabled channels.
        TextButton(onClick = openSettings) {
            Text(stringResource(R.string.notification_permission_settings))
        }
    }
}
