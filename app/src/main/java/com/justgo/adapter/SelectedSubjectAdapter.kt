package com.justgo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.justgo.R
import com.justgo.util.SingleLiveEvent
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class SelectedSubjectAdapter(var models: ArrayList<String>, val event: SingleLiveEvent<Int>) : RecyclerView.Adapter<SelectedSubjectAdapter.SelectedSubjectViewHolder>(), AnkoLogger {
    fun setModel(models: ArrayList<String>) {
        this.models = models
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedSubjectViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_selected_subject, parent, false)
        return SelectedSubjectViewHolder(itemView)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: SelectedSubjectViewHolder, position: Int) {
        holder.text.text = models[holder.adapterPosition]
        holder.text.onClick {
            //            models.removeAt(position)
//            notifyItemRemoved(position)
            debug("holder.adapterPosition: ${holder.adapterPosition}")
            info("holder.adapterPosition: ${holder.adapterPosition}")
            verbose("holder.adapterPosition: ${holder.adapterPosition}")
            event.value = holder.adapterPosition
        }

    }

    class SelectedSubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.find(R.id.selectedSubjectItem_tv)

        fun bind(content: String) {

        }
    }
}