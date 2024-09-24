package com.dgsw.guidedaechelin.presentation.features.main

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DailyAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        AlarmScheduler.scheduleAlarms(context,context as Activity)

    }
}