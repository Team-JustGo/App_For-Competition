package com.justgo.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.justgo.R
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class SelectSubjectAdapter(val models: ArrayList<String>) : RecyclerView.Adapter<SelectSubjectAdapter.SelectSubjectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectSubjectViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_select_subject, parent, false)
        return SelectSubjectViewHolder(itemView)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: SelectSubjectViewHolder, position: Int) = holder.bind(models[position])

    class SelectSubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.find(R.id.selectSubjectItem_tv)

        fun bind(content: String) {
            text.text = content
            text.onClick {

            }
        }
    }
}