package com.example.simplenotificationapp.first

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationReceiver: BroadcastReceiver() {

    override fun onReceive(ctx: Context?, intent: Intent?) {
        val msg: String? = intent?.getStringExtra("toastMessage")
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
    }
}