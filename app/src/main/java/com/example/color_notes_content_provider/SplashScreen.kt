package com.example.color_notes_content_provider

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        this.supportActionBar?.hide()
        window.statusBarColor = Color.WHITE

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },5000)
    }
}