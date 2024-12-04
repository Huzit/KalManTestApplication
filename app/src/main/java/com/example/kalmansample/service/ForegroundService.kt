package com.example.kalmansample.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService

class ForegroundService: LifecycleService() {
    private val NOTIFICATION_CHANNEL_ID = "CHANNEL_ID"
    private lateinit var notificationManager: NotificationManager

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d("dkdkdk", "서비스 시작")
        if(intent == null)
            return START_STICKY

        val serviceChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            "알림 설정",
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationManager.createNotificationChannel(serviceChannel)

        val notification: Notification = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setContentTitle("타이틀")
            .setContentText("설명")
            .build()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
            startForeground(1, notification, FOREGROUND_SERVICE_TYPE_LOCATION)
        else
            startForeground(1, notification)

        return super.onStartCommand(intent, flags, startId)


        return START_STICKY
    }
}