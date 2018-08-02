package com.justgo.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.justgo.ui.Preparation.SelectLocationFragment
import com.justgo.ui.Preparation.SelectTransportationFragment

class PreparationAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> SelectLocationFragment()
            1 -> SelectTransportationFragment()
            2 -> SelectTransportationFragment()
            3 -> SelectTransportationFragment()
            else -> null
        }
    }

    override fun getCount() = 4
}