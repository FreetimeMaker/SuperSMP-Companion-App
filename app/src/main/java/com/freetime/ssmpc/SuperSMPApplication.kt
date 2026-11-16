package com.freetime.ssmpc

import android.app.Application
import android.util.Log
import com.freetime.notifications.FreetimeNotifications
import com.freetime.notifications.NotificationCategory
import com.freetime.notifications.NotificationChannelOptions
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SuperSMPApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch {
            try {
                FreetimeNotifications.initialize(this@SuperSMPApplication) {
                    smallIcon = R.drawable.ic_stat_notification
                    checkIntervalMinutes = 360
                    notifyOnFirstCheck = false
                    maxNotificationsPerCheck = 5

                    // Both release feeds describe this app and its existing version scheme.
                    github(owner = "FreetimeMaker", repository = "SuperSMP-Companion-App")
                    fdroid(packageName = packageName)
                    category(
                        NotificationCategory.UPDATES,
                        options = NotificationChannelOptions(
                            name = getString(R.string.notification_channel_updates),
                            description = getString(R.string.notification_channel_updates_description),
                            importance = 3,
                        ),
                    )
                    category(
                        NotificationCategory.CHANGELOGS,
                        options = NotificationChannelOptions(
                            name = getString(R.string.notification_channel_changelogs),
                            description = getString(R.string.notification_channel_changelogs_description),
                            importance = 2,
                        ),
                    )
                    category(NotificationCategory.DEVELOPER_MESSAGES, enabled = false)
                }
            } catch (cancelled: CancellationException) {
                throw cancelled
            } catch (error: Exception) {
                Log.e("SuperSMPNotifications", "Unable to initialize notifications", error)
            }
        }
    }
}
