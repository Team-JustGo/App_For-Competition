package com.justgo.ui.SelectDone

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.justgo.R
import com.justgo.ui.SelectTrip.SelectTripActivity
import kotlinx.android.synthetic.main.activity_select_done.*

class SelectDoneActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_done)

        select_done_btn.setOnClickListener {
            TODO("여행 시작")
        }

        select_done_back.setOnClickListener {
            val intent = Intent(this,SelectTripActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}