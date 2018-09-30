package com.justgo.ui.SelectDone

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.justgo.R
import com.justgo.ui.SelectTrip.SelectTripActivity
import com.justgo.ui.navigation.NavigationActivity
import kotlinx.android.synthetic.main.activity_select_done.*

class SelectDoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_done)
        val intent = intent
        val lat = intent.getDoubleExtra("lat", 0.0)
        val lng = intent.getDoubleExtra("lng", 0.0)
        val desLat = intent.getDoubleExtra("desLat", 0.0)
        val desLng = intent.getDoubleExtra("desLng", 0.0)
        val transType = intent.getIntExtra("transType", 0)
        val placeId = intent.getStringExtra("placeId")
        select_done_btn.setOnClickListener {
            val navigationIntent = Intent(this, NavigationActivity::class.java)
            with(navigationIntent) {
                putExtra("lat", lat)
                putExtra("lng", lng)
                putExtra("desLat", desLat)
                putExtra("desLng", desLng)
                putExtra("transType", transType)
                putExtra("placeId", placeId)
            }
            startActivity(navigationIntent)
            finish()

        }

        select_done_back.setOnClickListener {
            //            val intent = Intent(this,SelectTripActivity::class.java)
//            startActivity(intent)
            finish()
        }
    }
}