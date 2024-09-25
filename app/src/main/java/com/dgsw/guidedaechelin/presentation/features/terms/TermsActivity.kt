package com.dgsw.guidedaechelin.presentation.features.terms

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.presentation.features.main.MainActivity

class TermsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)

        val acceptButton: Button = findViewById(R.id.acceptButton)
        val webView : WebView = findViewById(R.id.terms_web)

        webView.loadUrl("https://sites.google.com/view/daechelinguide-privacy-terms/%ED%99%88")

        acceptButton.setOnClickListener {

            val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putBoolean("isTermsAccepted", true)
                apply()
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}