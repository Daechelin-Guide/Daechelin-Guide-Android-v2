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

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, WidgetAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val triggerAtMillis = System.currentTimeMillis() + 1

        val alarmClockInfo = AlarmManager.AlarmClockInfo(triggerAtMillis, null)

        alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)

        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onEnabled(context: Context) {

        super.onEnabled(context)
        
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
