package com.justgo.Adapter

import android.arch.lifecycle.LiveData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.justgo.R
import com.justgo.Util.SingleLiveEvent
import com.justgo.ui.main.MainViewModel
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class SelectSubjectAdapter(var models: ArrayList<String>, val event: SingleLiveEvent<Int>) : RecyclerView.Adapter<SelectSubjectAdapter.SelectSubjectViewHolder>(), AnkoLogger {
    fun setModel(models: ArrayList<String>) {
        this.models = models
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectSubjectViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_select_subject, parent, false)
        return SelectSubjectViewHolder(itemView)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: SelectSubjectViewHolder, position: Int) {
        holder.bind(models[holder.adapterPosition])
        holder.text.onClick {
            //            notifyItemRemoved(position)
            debug("holder.adapterPosition: ${holder.adapterPosition}")
            info("holder.adapterPosition: ${holder.adapterPosition}")
            verbose("holder.adapterPosition: ${holder.adapterPosition}")
//            models.removeAt(position)
//            notifyItemRemoved(position)
            event.value = holder.adapterPosition
        }
    }

    class SelectSubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.find(R.id.selectSubjectItem_tv)

        fun bind(content: String) {
            text.text = content
            text.onClick {

            }
        }
    }
}