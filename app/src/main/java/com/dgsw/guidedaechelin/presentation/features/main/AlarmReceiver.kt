package com.dgsw.guidedaechelin.presentation.features.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        Log.d("Asd", "onReceive: ")

        val mealType = intent.getStringExtra("MealType")

        val serviceIntent = Intent(context, NotificationService::class.java)
        serviceIntent.putExtra("MealType", mealType)

        context.startService(serviceIntent)

    }
}