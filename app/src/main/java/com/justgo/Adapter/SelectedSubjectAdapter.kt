package com.justgo.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.justgo.R
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class SelectedSubjectAdapter(val models: ArrayList<String>) : RecyclerView.Adapter<SelectedSubjectAdapter.SelectedSubjectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedSubjectViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_selected_subject, parent, false)
        return SelectedSubjectViewHolder(itemView)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: SelectedSubjectViewHolder, position: Int){
        holder.text.text = models[position]
        holder.text.onClick{
            models.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
        }
    }

    class SelectedSubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.find(R.id.selectedSubjectItem_tv)

        fun bind(content: String) {

        }
    }
}