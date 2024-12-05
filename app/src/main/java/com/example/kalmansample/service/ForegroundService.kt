package com.example.kalmansample.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
import android.os.Build
import android.provider.Settings
import android.provider.Settings.EXTRA_APP_PACKAGE
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.example.kalmansample.R

private const val NOTIFICATION_CHANNEL_ID = "CHANNEL_ID"
class ForegroundService: LifecycleService() {
    private lateinit var notificationManager: NotificationManager

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        if(intent == null)
            return START_STICKY

        if(notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID) == null) {
            val serviceChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "알림 설정",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(serviceChannel)
        }

        val notification: Notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("타이틀")
            .setContentText("설명")
            .setSmallIcon(R.drawable.noti_icon)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .build()
        //알람 비활성일시
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID)
            if(channel != null && channel.importance == NotificationManager.IMPORTANCE_NONE){
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                    putExtra(EXTRA_APP_PACKAGE, packageName)
                }
                startActivity(intent)
            }
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            startForeground(1, notification, FOREGROUND_SERVICE_TYPE_LOCATION)
        else
            startForeground(1, notification)

        return START_STICKY
    }
}