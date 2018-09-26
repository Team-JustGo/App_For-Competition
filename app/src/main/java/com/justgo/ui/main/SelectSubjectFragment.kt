package com.justgo.ui.main


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        val viewModel by lazy { ViewModelProviders.of(activity!!)[MainViewModel::class.java] }

        val selectableRecyclerView = view.find<RecyclerView>(R.id.selectableSubject_select_rv)
        val selectedRecyclerView = view.find<RecyclerView>(R.id.selectSubject_selected_rv)
        selectableRecyclerView.addItemDecoration(SpacingItemDecoration(resources.getDimensionPixelOffset(R.dimen.dimen_0), resources.getDimensionPixelOffset(R.dimen.dimen_8)))
        selectedRecyclerView.addItemDecoration(SpacingItemDecoration(resources.getDimensionPixelOffset(R.dimen.dimen_0), resources.getDimensionPixelOffset(R.dimen.dimen_8)))

        val stringList = arrayListOf("asdf", "DF", "ASdf", "dfdfff", "Asdfff", "제발;;;")

        Log.d("SelectableSubject", viewModel.selectableSubject.value!!.toString() )
        selectableRecyclerView.adapter = SelectSubjectAdapter(viewModel.selectableSubject.value!!)
        selectableRecyclerView.layoutManager = getChipsLayoutManager()
//        selectedRecyclerView.adapter = SelectedSubjectAdapter(viewModel.selectedSubject.value!!)
//        selectedRecyclerView.layoutManager = getChipsLayoutManager()

        viewModel.selectableSubject.observe(this, Observer {
            Toast.makeText(context, "되긴하네", Toast.LENGTH_SHORT).show()
        })


        return view
    }

    fun getChipsLayoutManager() = ChipsLayoutManager.newBuilder(context)
            .setScrollingEnabled(true)
            .setGravityResolver { Gravity.START }
            .setOrientation(ChipsLayoutManager.HORIZONTAL)
            .setRowStrategy(ChipsLayoutManager.STRATEGY_DEFAULT)
            .withLastRow(true)
            .build()

}
