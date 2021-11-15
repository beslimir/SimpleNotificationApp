package com.example.simplenotificationapp.second

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simplenotificationapp.R
import java.util.*

class TimerNotification: AppCompatActivity(), SelectedTime {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timer_notification)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.iOne -> {
                true
            }
            R.id.iTwo -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showTimePickerDialog(item: MenuItem) {
        TimePickerFragment().show(supportFragmentManager, "timePicker")
    }

    fun showToast(item: MenuItem) {
        Toast.makeText(this, "Nema međa", Toast.LENGTH_SHORT).show()
    }

    override fun onSelectedTime(string: String) {
        val hourOfDay = string.substringBefore(":").toInt()
        val minute = string.substringAfter(":").toInt()
        Toast.makeText(this, "$hourOfDay:$minute", Toast.LENGTH_SHORT).show()

        callBroadcast(hourOfDay, minute)
    }

    private fun callBroadcast(hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        //if it's already too late, wait for tomorrow :)
        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        val intent = Intent(this, TimerBroadcast::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }
    }
}