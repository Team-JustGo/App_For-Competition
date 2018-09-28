package com.justgo.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.justgo.R
import com.justgo.ui.MyTrip.MyTripItem
import com.justgo.ui.MyTrip.MyTripTagItem
import com.justgo.ui.SelectDone.SelectDoneActivity
import com.justgo.ui.SelectTrip.ItemClickMethod
import com.justgo.ui.SelectTrip.SelectTripActivity
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.textColor
import org.jetbrains.anko.textColorResource
import org.jetbrains.anko.toast

class SelectTripListAdapter(val items : ArrayList<MyTripItem>, val tags : ArrayList<MyTripTagItem>, val ItemClick : ItemClickMethod, val context : Context)
    : RecyclerView.Adapter<SelectTripListAdapter.SelectTripViewHolder>() {


    companion object {
        var select_item_flag = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTripViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mytrip,parent,false)
        return SelectTripViewHolder(view)
    }

    override fun onBindViewHolder(holder: SelectTripViewHolder, position: Int) {
        holder.select_title.text = items[position].title
        holder.select_howfar.text = items[position].howfar

        with(holder.taglist){
            adapter = holder.adapter
            layoutManager = holder.lm
        }

        holder.slelect_item.setOnClickListener {
            if(!select_item_flag){
//                ItemClick.onClick()
                val intent = Intent(context,SelectDoneActivity::class.java)
                context.startActivity(intent)

                select_item_flag = true
                holder.View.backgroundResource = R.drawable.back_v_round_shape_select_trip
                holder.select_title.textColorResource = R.color.colorWhite
                holder.select_howfar.textColorResource = R.color.colorArriveBack
                holder.taglist.bringToFront()

            } else {
//                ItemClick.onClick()
                select_item_flag = false
                holder.View.backgroundResource = Color.TRANSPARENT
                holder.select_title.textColor = Color.parseColor("#707070")
                holder.select_howfar.textColor = Color.parseColor("#24202020")
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class SelectTripViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val adapter = MyTripTagAdapter(tags)
        val lm = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val select_title = itemView.findViewById<TextView>(R.id.mytrip_item_title)
        val select_howfar = itemView.findViewById<TextView>(R.id.mytrip_item_howfar)
        val slelect_item = itemView.findViewById<ConstraintLayout>(R.id.trip_item_view)
        var taglist = itemView.findViewById<RecyclerView>(R.id.mytrip_item_taglist)
        val View = itemView

    }

}