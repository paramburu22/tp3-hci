package com.example.contrall

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class SkipNotificationReceiver(id: String) : BroadcastReceiver() {
    var id = id

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "Received notification send ")
        if (intent.action.equals(MyIntent.SHOW_NOTIFICATION) &&
            intent.getStringExtra(MyIntent.DEVICE_ID)?.equals(id) == true
        ) {
            Log.d(TAG, "Skipping notification send ")
            abortBroadcast()
        }
    }

    companion object {
        private const val TAG = "SkipNotificationReceiver"
    }
}