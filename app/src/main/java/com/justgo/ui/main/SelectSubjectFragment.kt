package com.justgo.ui.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.justgo.Adapter.SelectSubjectAdapter

import com.justgo.R
import org.jetbrains.anko.find


class SelectSubjectFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_select_subject, container, false)

        val chipsLayoutManager = ChipsLayoutManager.newBuilder(context)
                .setChildGravity(Gravity.TOP)
                .setScrollingEnabled(true)
                .setGravityResolver { Gravity.START }
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                .build()
        val selectRecyclerView = view.find<RecyclerView>(R.id.selectedSubject_select_rv)
        val stringList = arrayListOf("asdf", "DF", "ASdf", "dfdfff", "Asdfff","제발;;;")

        selectRecyclerView.adapter = SelectSubjectAdapter(stringList)
        selectRecyclerView.layoutManager = chipsLayoutManager

        return view
    }

}
