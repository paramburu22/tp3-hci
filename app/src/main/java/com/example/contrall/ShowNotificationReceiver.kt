package com.example.contrall

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ShowNotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == MyIntent.SHOW_NOTIFICATION) {
            val deviceId: String? = intent.getStringExtra(MyIntent.DEVICE_ID)
            val event : String? = intent.getStringExtra(MyIntent.EVENT)
            Log.d(TAG, "Show notification intent received {$deviceId} {$event})")

            showNotification(context,deviceId!!,event!!)
        }
    }

    private fun showNotification(context: Context, device: String, event: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(MyIntent.DEVICE_ID, device)
            putExtra(MyIntent.EVENT,event)
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
        )



        val builder = NotificationCompat.Builder(context, MyApplication.CHANNEL_ID)
            .setSmallIcon(R.drawable.notifications)
            .setContentTitle(context.resources.getString(R.string.notifications_title))
            .setContentText(context.resources.getString(R.string.notifications_message))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)


        try {
            val notificationManager = NotificationManagerCompat.from(context)
            if (notificationManager.areNotificationsEnabled()){
                notificationManager.notify(device.hashCode(), builder.build())
            }
        } catch (e: SecurityException) {
            Log.d(TAG, "Notification permission not granted $e")
        }
    }

    companion object {
        private const val TAG = "ShowNotificationReceiver"
    }
}