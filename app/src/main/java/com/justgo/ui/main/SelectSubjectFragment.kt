package com.justgo.ui.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration
import com.justgo.Adapter.SelectSubjectAdapter
import com.justgo.Adapter.SelectedSubjectAdapter

import com.justgo.R
import org.jetbrains.anko.find


class SelectSubjectFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_select_subject, container, false)

        val selectRecyclerView = view.find<RecyclerView>(R.id.selectSubject_select_rv)
        val selectedRecyclerView = view.find<RecyclerView>(R.id.selectSubject_selected_rv)
        selectRecyclerView.addItemDecoration(SpacingItemDecoration(resources.getDimensionPixelOffset(R.dimen.dimen_0), resources.getDimensionPixelOffset(R.dimen.dimen_8)))
        selectedRecyclerView.addItemDecoration(SpacingItemDecoration(resources.getDimensionPixelOffset(R.dimen.dimen_0), resources.getDimensionPixelOffset(R.dimen.dimen_8)))

        val stringList = arrayListOf("asdf", "DF", "ASdf", "dfdfff", "Asdfff", "제발;;;")

        selectRecyclerView.adapter = SelectSubjectAdapter(stringList)
        selectRecyclerView.layoutManager = getChipsLayoutManager()
        selectedRecyclerView.adapter = SelectedSubjectAdapter(stringList)
        selectedRecyclerView.layoutManager = getChipsLayoutManager()
        return view
    }

    fun getChipsLayoutManager() = ChipsLayoutManager.newBuilder(context)
            .setChildGravity(Gravity.START)
            .setScrollingEnabled(true)
            .setGravityResolver { Gravity.START }
            .setOrientation(ChipsLayoutManager.HORIZONTAL)
            .setRowStrategy(ChipsLayoutManager.STRATEGY_FILL_SPACE)
            .withLastRow(true)
            .build()

}
