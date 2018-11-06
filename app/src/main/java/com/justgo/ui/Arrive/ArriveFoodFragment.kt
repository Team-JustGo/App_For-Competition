package com.justgo.ui.Arrive

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.justgo.adapter.ArriveFoodListAdapter
import com.justgo.connecter.getTourInfo
import com.justgo.model.ArriveItem
import com.justgo.R
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class ArriveFoodFragment : Fragment(), AnkoLogger {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = layoutInflater.inflate(R.layout.fragment_arrive_food, container, false)

        val data = arrayListOf<ArriveItem>()
        val intent = activity!!.intent
        val adapter = ArriveFoodListAdapter(data, context!!)
        getTourInfo(intent.getStringExtra("placeid")) {
            onSuccess = {
                //                Log.d("ArriveFoodFragment","nearRestaurant: ${body()!!.nearRestaurant}")
                info("${body()!!.nearRestaurant}")
                body()!!.nearRestaurant.forEach {
                    data.add(ArriveItem(it.image, it.title, it.address))
                }
                adapter.notifyDataSetChanged()
            }

            onFailure = {

            }
        }

        /*val sampledata = ArrayList<ArriveItem>().apply {
            for (i in 1..9)
                add(ArriveItem("", "Food", "subtitle"))
        }*/

        val foodlist = rootview.findViewById<RecyclerView>(R.id.arrive_food_recycler)
        foodlist.adapter = adapter

        return rootview
    }

}