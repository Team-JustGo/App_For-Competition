package com.justgo.ui.SelectTrip

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.justgo.Adapter.MyTripListAdapter
import com.justgo.Adapter.SelectTripListAdapter
import com.justgo.Connecter.getTourList
import com.justgo.Model.MyTripItem
import com.justgo.Model.MyTripTagItem
import com.justgo.R
import kotlinx.android.synthetic.main.activity_select_trip.*
import org.jetbrains.anko.toast
import kotlin.math.roundToInt

class SelectTripActivity : AppCompatActivity(), ItemClickMethod {
    override fun onClick() {
        /*    if (!SelectTripListAdapter.select_item_flag) {
                select_trip_btn.visibility = View.VISIBLE
            } else {
                select_trip_btn.visibility = View.GONE
            }*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_trip)

        setSupportActionBar(select_trip_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        val intent = intent
        val lat = intent.getDoubleExtra("lat", 0.0)
        val lng = intent.getDoubleExtra("lng", 0.0)
        val theme = intent.getStringExtra("theme")
        val minDistance = intent.getIntExtra("minDistance", 0)
        val maxDistance = intent.getIntExtra("maxDistance", 0)
        val transType = intent.getIntExtra("transType", 0)

        val data = arrayListOf<MyTripItem>()
        getTourList(lat, lng, theme, minDistance, maxDistance) {
            onSuccess = {
                var index = 1
                body()!!.list.forEach {
                    data.add(MyTripItem("Select ${(64 + index).toChar()}", "${it.distance.roundToInt().toDouble() / 1000} Km", it.theme.split(",") as ArrayList<String>, it.placeId))
                    index++
                }
                val adapter_list = SelectTripListAdapter(data, this@SelectTripActivity, this@SelectTripActivity)
                adapter_list.transType = transType
                select_trip_recycler.adapter = adapter_list
            }
            onFailure = {
                toast("이건아닌데;")
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

    }
}