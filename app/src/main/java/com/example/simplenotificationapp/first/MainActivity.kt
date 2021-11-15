package com.example.simplenotificationapp.first

import android.app.PendingIntent
import android.content.Intent
import android.media.session.MediaSession
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.simplenotificationapp.BaseApplication.Companion.CHANNEL_1_ID
import com.example.simplenotificationapp.BaseApplication.Companion.CHANNEL_2_ID
import com.example.simplenotificationapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManagerCompat
    private lateinit var etTitle: EditText
    private lateinit var etMessage: EditText
    private lateinit var mediaSession: MediaSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = NotificationManagerCompat.from(this)

        etTitle = findViewById(R.id.etTitle)
        etMessage = findViewById(R.id.etMessage)

        mediaSession = MediaSession(this, "tag")
    }

    fun sendOnChannel1(view: View) {
        val activityIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0)

        val notification = NotificationCompat.Builder(this, CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_one)
            .setContentTitle(etTitle.text.toString())
            .setContentText(etMessage.text.toString())
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .build()

        notificationManager.notify(1, notification)
    }

    fun sendOnChannel2(view: View) {
        val activityIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0)

        val notification = NotificationCompat.Builder(this, CHANNEL_2_ID)
            .setSmallIcon(R.drawable.ic_two)
            .setContentTitle(etTitle.text.toString())
            .setContentText(etMessage.text.toString())
            .addAction(R.drawable.ic_one, "One", contentIntent)
            .addAction(R.drawable.ic_two, "Two", null)
            .addAction(R.drawable.ic_pause, "Pause", null)
            .addAction(R.drawable.ic_hand, "Hand", null)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(contentIntent)
            .setSubText("Sub text")
            .build()

        notificationManager.notify(2, notification)
    }
}































