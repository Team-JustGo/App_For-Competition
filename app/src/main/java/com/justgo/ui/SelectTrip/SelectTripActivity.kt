package com.justgo.ui.SelectTrip

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.justgo.Adapter.MyTripListAdapter
import com.justgo.Adapter.SelectTripListAdapter
import com.justgo.R
import com.justgo.ui.MyTrip.MyTripItem
import com.justgo.ui.MyTrip.MyTripTagItem
import kotlinx.android.synthetic.main.activity_select_trip.*

class SelectTripActivity : AppCompatActivity(),ItemClickMethod{
    override fun onClick() {
        if(!SelectTripListAdapter.select_item_flag){
            select_trip_btn.visibility = View.VISIBLE
        } else {
            select_trip_btn.visibility = View.GONE
        }    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_trip)

        setSupportActionBar(select_trip_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        val sampledata_list = ArrayList<MyTripItem>().apply {
            for (i in 1..10)
                add(MyTripItem("Select " + (64 + i).toChar(),"44km, 30min" ))
        }

        val sampledata_tag = ArrayList<MyTripTagItem>().apply {
            add(MyTripTagItem("Food"))
            add(MyTripTagItem("Instagram"))
            add(MyTripTagItem("Rainbow"))
            add(MyTripTagItem("Six Siege"))
        }

        val adapter_list = SelectTripListAdapter(sampledata_list,sampledata_tag,this,this)

        select_trip_recycler.adapter = adapter_list

    }
}