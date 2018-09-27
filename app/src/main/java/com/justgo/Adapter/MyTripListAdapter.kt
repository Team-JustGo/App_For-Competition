package com.justgo.Adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.justgo.R
import com.justgo.ui.MyTrip.MyTripItem
import com.justgo.ui.MyTrip.MyTripTagItem

class MyTripListAdapter (val items : ArrayList<MyTripItem>, val tags : ArrayList<MyTripTagItem>, val context : Context)
    : RecyclerView.Adapter<MyTripListAdapter.MyTripCustomView>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTripCustomView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mytrip,parent,false)
        return MyTripCustomView(view)
    }

    override fun onBindViewHolder(holder: MyTripCustomView, position: Int) {


        holder.title.text = items[position].title
        holder.howfar.text = items[position].howfar
        with(holder.taglist){
            adapter = holder.adapter
            layoutManager = holder.lm
        }
    }

    override fun getItemCount() = items.size

    inner class MyTripCustomView(itemView : View) : RecyclerView.ViewHolder(itemView){
        var adapter = MyTripTagAdapter(tags)
        var lm = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        var title = itemView.findViewById<TextView>(R.id.mytrip_item_title)
        var howfar = itemView.findViewById<TextView>(R.id.mytrip_item_howfar)
        var taglist = itemView.findViewById<RecyclerView>(R.id.mytrip_item_taglist)
    }
}