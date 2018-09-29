package com.justgo.ui.Arrive

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.justgo.Adapter.ArriveFoodListAdapter
import com.justgo.Adapter.ArriveNearListAdapter
import com.justgo.Connecter.getTourInfo
import com.justgo.Model.ArriveItem
import com.justgo.R
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class ArriveFoodFragment : Fragment(),AnkoLogger {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = layoutInflater.inflate(R.layout.fragment_arrive_food, container,false)

        val data = arrayListOf<ArriveItem>()

        getTourInfo("ChIJ45fXsE1IZTURoogpKhRsxuY"){
            onSuccess = {
//                Log.d("ArriveFoodFragment","nearRestaurant: ${body()!!.nearRestaurant}")
                info("${body()!!.nearRestaurant}")
                body()!!.nearRestaurant.forEach {
                    data.add(ArriveItem(it.image,it.title,it.address))
                }
            }

            onFailure = {

            }
        }

        val adapter = ArriveFoodListAdapter(data,activity!!)
        val sampledata = ArrayList<ArriveItem>().apply {
            for (i in 1..9)
                add(ArriveItem("", "Food", "subtitle"))
        }


        val foodlist = rootview.findViewById<RecyclerView>(R.id.arrive_food_recycler)
        foodlist.adapter = adapter

        return rootview
    }

}