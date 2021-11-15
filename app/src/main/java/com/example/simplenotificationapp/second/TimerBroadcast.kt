package com.example.simplenotificationapp.second

import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.simplenotificationapp.BaseApplication.Companion.CHANNEL_1_ID
import com.example.simplenotificationapp.R

class TimerBroadcast: BroadcastReceiver() {

    override fun onReceive(ctx: Context?, intent: Intent?) {
        val repeatingIntent = Intent(ctx, NotificationOpen::class.java)
        repeatingIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        val pendingIntent = PendingIntent.getActivity(ctx, 0, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(ctx!!, CHANNEL_1_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_hand)
            .setContentTitle("Content Title")
            .setContentText("This notification will repeat every day at the same time")
            .setPriority(Notification.DEFAULT_SOUND)
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(ctx)
        notificationManager.notify(100, builder.build())
    }
}