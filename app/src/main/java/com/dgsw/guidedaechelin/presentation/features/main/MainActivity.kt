package com.dgsw.guidedaechelin.presentation.features.main

import android.app.Activity
import android.app.AlarmManager
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.ActivityMainBinding
import com.dgsw.guidedaechelin.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()

    override fun start() {


//        DailyAlarmScheduler.cancelAlarm(this)
//        DailyAlarmScheduler.scheduleAlarms(this,this)

//        AlarmScheduler.cancelAlarm(this)
//        AlarmScheduler.scheduleAlarms(this, this)


//        val serviceIntent = Intent(this, NotificationService::class.java)
//        serviceIntent.putExtra("MealType", "BREAKFAST")
//
//        this.startService(serviceIntent)

        installSplashScreen()

    }

}