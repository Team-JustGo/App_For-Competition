package com.justgo.ui.Preparation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.justgo.Adapter.PreparationAdapter
import com.justgo.R
import kotlinx.android.synthetic.main.activity_preparation.*
import org.jetbrains.anko.sdk25.coroutines.onTouch

class PreparationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preparation)
        initIndicator()
        initViewPager()
    }

    fun initViewPager() {
        preparation_viewPager.adapter = PreparationAdapter(supportFragmentManager)
        preparation_viewPager.currentItem = 0
        preparation_viewPager.onTouch { v, event -> false }
        preparation_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                preparation_indicator.selectDot(position)
            }

        })
    }

    fun initIndicator() {
        preparation_indicator.setItemMargin(16)
        preparation_indicator.setAnimDuration(300)
        preparation_indicator.createDotPanel(4, R.drawable.ellipse_white, R.drawable.ellipse_black)
    }
}
