package com.justgo.Adapter

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.justgo.Model.ReviewItem
import com.justgo.R

class ArriveInfoReviewAdapter(val items : ArrayList<ReviewItem>) : RecyclerView.Adapter<ArriveInfoReviewAdapter.ArriveInfoReviewVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArriveInfoReviewVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review,parent,false)
        return ArriveInfoReviewVH(view)
    }

    override fun onBindViewHolder(holder: ArriveInfoReviewVH, position: Int) {
        holder.stars.text = items[position].star
        holder.content.text = items[position].content
    }

    override fun getItemCount(): Int = items.size

    inner class ArriveInfoReviewVH(itemView : View) : RecyclerView.ViewHolder(itemView){
        val stars = itemView.findViewById<TextView>(R.id.arrive_info_review_star)
        val content = itemView.findViewById<TextView>(R.id.arrive_info_review_content)
    }
}