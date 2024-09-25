package com.dgsw.guidedaechelin.presentation.features.main

import android.app.Activity
import android.app.AlarmManager
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.databinding.ActivityMainBinding
import com.dgsw.guidedaechelin.presentation.base.BaseActivity
import com.dgsw.guidedaechelin.presentation.features.terms.TermsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()

    override fun start() {

        val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        val isTermsAccepted = sharedPreferences.getBoolean("isTermsAccepted", false)

        if (!isTermsAccepted) {
            // 사용 약관 화면으로 이동
            val intent = Intent(this, TermsActivity::class.java)
            startActivity(intent)
            finish() // 메인 화면 종료
        }

        installSplashScreen()

    }

}