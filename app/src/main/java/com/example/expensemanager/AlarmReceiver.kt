package com.example.expensemanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi

class AlarmReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {

        if (intent?.action == "ALARM_ACTION") {
            showNotification(context)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification(context: Context?) {
        val notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "my_channel_id"
        val channelName = "My Channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

//        Intent To MainActivity
        val contentIntent = Intent(context, HomeActivity::class.java)
    contentIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            contentIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = Notification.Builder(context, channelId)
            .setContentTitle("Income Expense")
            .setContentText("Add Your Income and Expense")
            .setSmallIcon(R.drawable.expense_manager)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val notification = notificationBuilder.build()

        notificationManager.notify(0, notification)
    }
}