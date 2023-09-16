package com.dgsw.guidedaechelin.presentation.features.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.presentation.features.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        moveMain(1)

    }

    private fun moveMain(sec: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            delay((sec*1000).toLong())
            CoroutineScope(Dispatchers.Main).launch {
                Intent(baseContext, MainActivity::class.java).run {
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(this)
                }
                finish()
            }
        }
    }
}