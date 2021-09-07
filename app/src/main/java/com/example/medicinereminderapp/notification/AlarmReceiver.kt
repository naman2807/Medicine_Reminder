package com.example.medicinereminderapp.notification

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.fragments.ReminderFragment
import com.google.android.material.timepicker.TimeFormat
import java.time.LocalDateTime
import java.time.LocalTime

class AlarmReceiver: BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        val intentReceiver = Intent(context, ReminderFragment::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

        val pendingIntent = PendingIntent.getActivity(
                        context,
                1,
                        intentReceiver,
                    0)

        val builder = NotificationCompat.Builder(context!!, "medicineReminder")
            .setSmallIcon(R.drawable.ic_pill)
            .setContentTitle("Reminder")
            .setContentText("Time: " + getTime() + "\nTime to take medicine.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(false)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(111,builder.build())

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getTime(): String {
        val currentTime: String
        val time = LocalTime.now()
        val hour = time.hour
        if(hour > 12){
                currentTime = String.format("%02d", hour - 12) + ":" + String.format(
                    "%02d", time.minute
                ) + " PM"
        }else {
            currentTime = String.format("%02d", hour) + ":" + String.format(
                    "%02d", time.minute
                ) + " AM"
        }
        return currentTime
    }
}