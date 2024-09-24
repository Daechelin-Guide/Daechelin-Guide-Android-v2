package com.dgsw.guidedaechelin.presentation.features.main

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Calendar

object DailyAlarmScheduler {

    private const val ALARM_REQUEST_CODE = 10000
    private const val PERMISSION_REQUEST_CODE = 1000

    fun getPermission(context : Context, activity: Activity) {
        val list = Array(2){""}
        var index = 0

        if (ContextCompat.checkSelfPermission(context,android.Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                list.set(index,android.Manifest.permission.POST_NOTIFICATIONS)
            }
            index++
        }
        if (ContextCompat.checkSelfPermission(context,android.Manifest.permission.SCHEDULE_EXACT_ALARM)
            != PackageManager.PERMISSION_GRANTED) {
            list.set(index, android.Manifest.permission.SCHEDULE_EXACT_ALARM)
            index++
        }

        if (list[0] != ""){
            ActivityCompat.requestPermissions(
                activity,
                list,
                PERMISSION_REQUEST_CODE
            )
        }
    }
    fun scheduleAlarms(context : Context, activity: Activity){

        getPermission(context, activity)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyAlarmReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, intent, PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }
    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, ALARM_REQUEST_CODE, intent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.cancel(pendingIntent)
        pendingIntent.cancel()
    }

}