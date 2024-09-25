package com.dgsw.guidedaechelin.presentation.features.widget

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.domain.model.MenuModel
import com.dgsw.guidedaechelin.presentation.features.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

const val REPEAT_TIME : Long = 15
const val NO_MENU_TEXT = "급식 정보가 없습니다"

@AndroidEntryPoint
class AppWidgetProvider : AppWidgetProvider() {

    val TAG = "AppWidgetProvider"

    @SuppressLint("ScheduleExactAlarm")
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {

        Log.d(TAG, "onUpdate: Start")

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, WidgetAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // 알람 시간 설정 (예: 5초 후)
        val triggerAtMillis = System.currentTimeMillis() + 1

        val alarmClockInfo = AlarmManager.AlarmClockInfo(triggerAtMillis, null)

        alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)

        Log.d(TAG, "onUpdate: Finish")

        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onEnabled(context: Context) {

        super.onEnabled(context)

//        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val intent = Intent(context, WidgetAlarmReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//
//        // 알람 시간 설정 (예: 5초 후)
//        val triggerAtMillis = System.currentTimeMillis() + 5000
//
//        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)



//        Log.d(TAG, "onEnabled: Start")
//
//        val workManager = WorkManager.getInstance(context)
//
//        val periodicWorkRequest = PeriodicWorkRequestBuilder<MenuWorker>(REPEAT_TIME, TimeUnit.MINUTES)
//            .setConstraints(
//                Constraints.Builder()
//                    .setRequiredNetworkType(NetworkType.CONNECTED)
//                    .build()
//            )
//            .build()
//
//        workManager.enqueueUniquePeriodicWork(
//            "MenuUpdateWorker",
//            ExistingPeriodicWorkPolicy.REPLACE,
//            periodicWorkRequest
//        )
    }

    fun updateWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        menuToday: MenuModel,
        menuTomorrow: MenuModel
    ) {
        val remoteViews = RemoteViews(context.packageName, R.layout.app_widget).apply {
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                Intent(context, MainActivity::class.java),
                PendingIntent.FLAG_IMMUTABLE
            )
            setOnClickPendingIntent(R.id.widget_frame, pendingIntent)

            // 위젯에 표시할 메뉴 데이터 설정
            val currentTime = LocalDateTime.now().hour

            if (currentTime < 9) {
                setTextViewText(R.id.widget_menu, menuToday.breakfast ?: NO_MENU_TEXT)
                setImageViewResource(R.id.widget_img, R.drawable.orange_widget)
            } else if (currentTime < 13) {
                setTextViewText(R.id.widget_menu, menuToday.lunch ?: NO_MENU_TEXT)
                setImageViewResource(R.id.widget_img, R.drawable.green_widget)
            } else if (currentTime < 19) {
                setTextViewText(R.id.widget_menu, menuToday.dinner ?: NO_MENU_TEXT)
                setImageViewResource(R.id.widget_img, R.drawable.violet_widget)
            } else {
                setTextViewText(R.id.widget_menu, menuTomorrow.breakfast ?: NO_MENU_TEXT)
                setImageViewResource(R.id.widget_img, R.drawable.orange_widget)
            }
        }

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
    }
}
