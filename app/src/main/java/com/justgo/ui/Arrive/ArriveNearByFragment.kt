package com.justgo.ui.Arrive

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.justgo.Adapter.ArriveNearListAdapter
import com.justgo.Connecter.getTourInfo
import com.justgo.Model.ArriveItem
import com.justgo.R


class ArriveNearByFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_arrive_nearby, container, false)

        val data = ArrayList<ArriveItem>()


        getTourInfo("ChIJ45fXsE1IZTURoogpKhRsxuY"){
            onSuccess = {
                body()!!.nearSpot.forEach {
                    data.add(ArriveItem(it.image,it.title,it.address))
                }
            }

            onFailure = {

            }
        }


        val adapter = ArriveNearListAdapter(data,activity!!)

        val nearbylist = rootview.findViewById<RecyclerView>(R.id.arrive_nearby_recycler)
        nearbylist.adapter = adapter

        return rootview
    }
}