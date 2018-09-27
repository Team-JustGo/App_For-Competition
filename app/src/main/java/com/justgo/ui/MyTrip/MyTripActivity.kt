package com.justgo.ui.MyTrip

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.justgo.Adapter.MyTripListAdapter
import com.justgo.Adapter.MyTripTagAdapter
import com.justgo.R
import kotlinx.android.synthetic.main.activity_mytrip.*
import kotlinx.android.synthetic.main.item_mytrip.*

class MyTripActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mytrip)

        setSupportActionBar(mytrip_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        var sampledata_list = ArrayList<MyTripItem>().apply {
            for (i in 1..10)
                add(MyTripItem("Select " + (64 + i).toChar(),"44km, 30min" ))
        }

        var sampledata_tag = ArrayList<MyTripTagItem>().apply {
                add(MyTripTagItem("Food"))
                add(MyTripTagItem("Instagram"))
                add(MyTripTagItem("Rainbow"))
                add(MyTripTagItem("Six Siege"))
        }

        var lm = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        var adapter_list = MyTripListAdapter(sampledata_list,sampledata_tag,this)

        mytrip_recycler.adapter = adapter_list


    }
}