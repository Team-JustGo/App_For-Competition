package com.justgo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.justgo.util.getToken
import com.justgo.ui.login.LoginActivity
import com.justgo.ui.main.MainActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getToken(baseContext, true) == "") {
            startActivity<LoginActivity>()
            finish()
        } else {
            startActivity<MainActivity>()
            finish()
        }
//        setContentView(R.layout.activity_splash)

    }
}
