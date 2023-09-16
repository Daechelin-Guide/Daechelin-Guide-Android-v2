package com.dgsw.guidedaechelin.presentation.features.main

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

        installSplashScreen()
    }



}