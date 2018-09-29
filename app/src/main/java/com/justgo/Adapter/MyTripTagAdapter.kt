package com.justgo.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.justgo.R
import com.justgo.Model.MyTripTagItem

class MyTripTagAdapter(val items: ArrayList<String>) : RecyclerView.Adapter<MyTripTagAdapter.MyTripTagCustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTripTagCustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mytrip_tag, parent, false)
        return MyTripTagCustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyTripTagCustomViewHolder, position: Int) {
        holder.tag.text = items[position]
    }

    override fun getItemCount() = items.size

    inner class MyTripTagCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tag = itemView.findViewById<TextView>(R.id.mytrip_item_tag_text)
    }
}