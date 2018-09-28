package com.justgo.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.justgo.R
import com.justgo.Model.ArriveItem

class ArriveListAdapter(val items: ArrayList<ArriveItem>) : RecyclerView.Adapter<ArriveListAdapter.ArriveCustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArriveCustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_arrive, parent, false)
        return ArriveCustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArriveCustomViewHolder, position: Int) {
        holder.arrive_title.text = items[position].arrive_title
        holder.arrive_subtitle.text = items[position].arrive_subtitle
    }

    override fun getItemCount() = items.size

    inner class ArriveCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var arrive_subtitle = itemView.findViewById<TextView>(R.id.arrive_item_subtitle)
        var arrive_img = itemView.findViewById<ImageView>(R.id.arrive_item_img)
        var arrive_title = itemView.findViewById<TextView>(R.id.arrive_item_title)
    }
}