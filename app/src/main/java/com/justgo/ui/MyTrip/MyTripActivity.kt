package com.justgo.ui.MyTrip

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.justgo.Adapter.MyTripListAdapter
import com.justgo.Connecter.getTourInfo
import com.justgo.Model.MyTripItem
import com.justgo.Model.MyTripTagItem
import com.justgo.R
import com.justgo.Util.getToken
import kotlinx.android.synthetic.main.activity_mytrip.*

class MyTripActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mytrip)

        setSupportActionBar(mytrip_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val sampledata_list = ArrayList<MyTripItem>()
        getTourInfo(getToken(baseContext, true)) {
            onSuccess = {
                val body = body()!!
                val tag = arrayListOf<String>()
                body.tags.split(",").forEach {
                    tag.add(it)
                }
                sampledata_list.add(MyTripItem(body.name, body.date, tag, "", 0.0, 0.0))
                mytrip_recycler.adapter.notifyDataSetChanged()
            }
        }
        /*val sampledata_tag = ArrayList<String>().apply {
            add("Food")
            add("Instagram")
            add("Rainbow")
            add("Six Siege")
        }
        val sampledata_list = ArrayList<MyTripItem>().apply {
            for (i in 1..10)
                add(MyTripItem("Select " + (64 + i).toChar(), "44km, 30min", sampledata_tag))
        }*/

        val lm = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val adapter_list = MyTripListAdapter(sampledata_list, this)

        mytrip_recycler.adapter = adapter_list


    }
}