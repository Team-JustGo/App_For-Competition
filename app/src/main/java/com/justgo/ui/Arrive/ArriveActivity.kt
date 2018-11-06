package com.justgo.ui.Arrive

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.justgo.adapter.ArriveViewPagerAdapter
import com.justgo.connecter.getTourInfo
import com.justgo.R
import kotlinx.android.synthetic.main.activity_arrive.*

class ArriveActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arrive)
        val intent = intent
        val placeId = intent.getStringExtra("placeid")

        getTourInfo(placeId) {
            onSuccess = {
                val body = body()

                arrive_area_tv?.text = body()!!.address
                arrive_place_tv?.text = body()!!.name
            }

            onFailure = {

            }
        }

        val adapter = ArriveViewPagerAdapter(supportFragmentManager)
        arrive_viewpager.adapter = adapter

        arrive_tab.setupWithViewPager(arrive_viewpager)
    }
}