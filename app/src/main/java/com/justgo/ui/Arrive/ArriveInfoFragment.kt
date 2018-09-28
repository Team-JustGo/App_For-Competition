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

class ArriveInfoFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_arrive_info, container, false)

        val sampledata = ArrayList<ArriveItem>().apply {
            for (i in 1..7)
                add(ArriveItem("", "Info", "subtitle"))
        }

        val adapter = ArriveListAdapter(sampledata)

        val infolist = rootview.findViewById<RecyclerView>(R.id.arrive_info_recycler)
        infolist.adapter = adapter

        return rootview
    }
}