package com.justgo.ui.Arrive

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.justgo.Adapter.ArriveListAdapter
import com.justgo.Model.ArriveItem
import com.justgo.R


class ArriveFoodFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = layoutInflater.inflate(R.layout.fragment_arrive_food, container,false)

        val sampledata = ArrayList<ArriveItem>().apply {
            for (i in 1..9)
                add(ArriveItem("", "Food", "subtitle"))
        }

        val adapter = ArriveListAdapter(sampledata)

        val foodlist = rootview.findViewById<RecyclerView>(R.id.arrive_food_recycler)
        foodlist.adapter = adapter

        return rootview
    }
}