package com.example.contrall

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.compose.rememberNavController
import com.example.contrall.ui.theme.ContrAllTheme
import com.example.contrall.ui.theme.rememberWindowSizeClass
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionState
import java.util.Locale


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter",
        "UnusedMaterial3ScaffoldPaddingParameter"
    )
    private lateinit var receiver : BroadcastReceiver
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel()

        collectServerEvents()

        val deviceId = intent.getStringExtra(MyIntent.DEVICE_ID)

        setContent {
            val window = rememberWindowSizeClass()
            ContrAllTheme(window) {
                setupScreens()
                val navController = rememberNavController()
                ContrAllNavGraph(navController = navController, this, deviceId)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    val permissionState =
                        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
                    if (!permissionState.hasPermission) {
                        NotificationPermission(permissionState = permissionState)
                        LaunchedEffect(true) {
                            permissionState.launchPermissionRequest()
                        }
                    }
                }
            }
        }
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            with(NotificationManagerCompat.from(this)) {
                createNotificationChannel(channel)
            }
        }
    }

    private fun collectServerEvents() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, ServerEventReceiver::class.java)

        var pendingIntent = PendingIntent.getBroadcast(
            this, 0, intent, PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE)
        if (pendingIntent != null)
            alarmManager.cancel(pendingIntent)

        pendingIntent = PendingIntent.getBroadcast(
            this, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            SystemClock.currentThreadTimeMillis(),
            INTERVAL,
            pendingIntent
        )
        Log.d(TAG, "Alarm set every $INTERVAL millis")
    }
    @SuppressLint("SuspiciousIndentation")
    fun generateNotif(id: String){
        receiver = SkipNotificationReceiver(id)
        IntentFilter(MyIntent.SHOW_NOTIFICATION)
            .apply { priority=1 }
            .also {
                var flags = 0
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                flags = Context.RECEIVER_NOT_EXPORTED
                registerReceiver(receiver,it,flags)
            }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        receiver = SkipNotificationReceiver("")
        IntentFilter(MyIntent.SHOW_NOTIFICATION)
            .apply {
                priority = 1
            }
            .also {
                var flags = 0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    flags = Context.RECEIVER_NOT_EXPORTED
                registerReceiver(receiver, it, flags)
            }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }


    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun NotificationPermission(
        permissionState: PermissionState,
    ) {
        PermissionRequired(
            permissionState = permissionState,
            permissionNotGrantedContent = {  },
            permissionNotAvailableContent = {  }
        ) {

        }
    }
    companion object {
        const val TAG = "MyApplication"
        const val CHANNEL_ID = "device"
        const val INTERVAL: Long = 1000 * 60
    }
}

