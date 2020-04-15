package com.dvoineu.crnvirus.view


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

import com.dvoineu.crnvirus.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(::closeSplashScreen, getSplashScreenDelay())
    }

    fun closeSplashScreen() {
        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra(Constants.SPLASH_SCREEN_INTENT_VALUE, "")
        startActivity(intent)
        finish()
    }

    fun getSplashScreenDelay(): Long {
        return 3000L
    }
}