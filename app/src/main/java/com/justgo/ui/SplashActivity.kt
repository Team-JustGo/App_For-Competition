package com.justgo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justgo.R
import com.justgo.ui.main.MainActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity<MainActivity>()
//        setContentView(R.layout.activity_splash)
    }
}
