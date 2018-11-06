package com.justgo.ui.Arrive

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.justgo.adapter.ArriveNearListAdapter
import com.justgo.connecter.getTourInfo
import com.justgo.model.ArriveItem
import com.justgo.R


class ArriveNearByFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_arrive_nearby, container, false)

        val data = ArrayList<ArriveItem>()
        val intent = activity!!.intent
        val adapter = ArriveNearListAdapter(data, context!!)
        getTourInfo(intent.getStringExtra("placeid")) {
            onSuccess = {
                body()!!.nearSpot.forEach {
                    data.add(ArriveItem(it.image, it.title, it.address))
                }
                adapter.notifyDataSetChanged()
            }

            onFailure = {

            }
        }

        val nearbylist = rootview.findViewById<RecyclerView>(R.id.arrive_nearby_recycler)
        nearbylist.adapter = adapter

        return rootview
    }
}