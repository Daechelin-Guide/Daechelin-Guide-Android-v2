package com.dgsw.guidedaechelin.presentation.features.main

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.toBitmap
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.domain.repository.MenuRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class NotificationService : Service(){
    @Inject
    lateinit var menuRepository : MenuRepository


    private val CHANNEL_ID = "DaechelinChannel"
    private val NOTIFICATION_ID = "123"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val date = LocalDate.now().format(formatter)

        val mealType = intent?.getStringExtra("MealType")

        CoroutineScope(Dispatchers.IO).launch {
            val menu = async {
                menuRepository.getMenu(date = date)
            }

            launch(Dispatchers.Main) {
                showNotification(menu.await().breakfast, mealType)
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun showNotification(message: String?, mealType : String?) {

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)

        var notificationTitle = ""
        var notificationIcon = R.drawable.orange_widget

        when(mealType){
            "BREAKFAST" -> {
                notificationTitle = "조식"
                notificationIcon = R.drawable.orange_notification
            }
            "LUNCH" -> {
                notificationTitle = "중식"
                notificationIcon = R.drawable.green_widget
            }
            "DINNER" -> {
                notificationTitle = "석식"
                notificationIcon = R.drawable.violet_widget
            }
        }

//        notificationIcon = R.drawable.burger

        val notificationBitmap = getDrawable(notificationIcon)?.toBitmap()

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_ID)
            .setContentTitle(notificationTitle)
            .setContentText(message)
            .setSmallIcon(notificationIcon)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Notification 채널 생성 (API 26+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_ID,
                "My Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Notification 표시
        notificationManager.notify(NOTIFICATION_ID.toInt(), notificationBuilder.build())
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


}