package com.justgo.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class PreparationAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    /*
     override fun getItem(position: Int): Fragment? {
         return when (position) {
             0 -> SelectLocationFragment()
             1 -> SelectTransportationFragment()
             2 -> SelectTransportationFragment()
             3 -> SelectTransportationFragment()
             else -> null
         }
     }
     */

    override fun getCount() = 4
}