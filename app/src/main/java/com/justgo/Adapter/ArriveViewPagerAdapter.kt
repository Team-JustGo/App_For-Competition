package com.justgo.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.justgo.ui.Arrive.ArriveFoodFragment
import com.justgo.ui.Arrive.ArriveInfoFragment
import com.justgo.ui.Arrive.ArriveNearByFragment

class ArriveViewPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment? {
       return when(position){
            0 -> ArriveInfoFragment()
            1 -> ArriveNearByFragment()
            2 -> ArriveFoodFragment()
            else -> null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Info"
            1 -> "Near By"
            2 -> "Food"
            else -> null
        }
    }
}