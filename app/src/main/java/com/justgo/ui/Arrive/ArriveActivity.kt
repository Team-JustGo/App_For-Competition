package com.justgo.ui.Arrive

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.justgo.Adapter.ArriveViewPagerAdapter
import com.justgo.R
import kotlinx.android.synthetic.main.activity_arrive.*

class ArriveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arrive)

        val adapter = ArriveViewPagerAdapter(supportFragmentManager)
        arrive_viewpager.adapter = adapter


        arrive_tab.setupWithViewPager(arrive_viewpager)
    }
}