package com.justgo.ui.main


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
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
import com.justgo.Util.DataBindingFragment
import com.justgo.databinding.FragmentSelectSubjectBinding
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.toast


class SelectSubjectFragment : DataBindingFragment<FragmentSelectSubjectBinding>(), AnkoLogger {
    override fun getLayoutId(): Int = R.layout.fragment_select_subject

    val viewModel by lazy { ViewModelProviders.of(activity!!)[MainViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.mainViewModel = viewModel

        val selectableRecyclerView = view.find<RecyclerView>(R.id.selectableSubject_select_rv)
        val selectedRecyclerView = view.find<RecyclerView>(R.id.selectSubject_selected_rv)
        val selectableSubjectAdapter = SelectSubjectAdapter(viewModel.selectableSubject.value!!, viewModel.selectableSubjectDeletedEvent)
        val selectedSubjectAdapter = SelectedSubjectAdapter(viewModel.selectedSubject.value!!, viewModel.selectedSubjectDeletedEvent)

        selectableRecyclerView.addItemDecoration(SpacingItemDecoration(resources.getDimensionPixelOffset(R.dimen.dimen_0), resources.getDimensionPixelOffset(R.dimen.dimen_8)))
        selectedRecyclerView.addItemDecoration(SpacingItemDecoration(resources.getDimensionPixelOffset(R.dimen.dimen_0), resources.getDimensionPixelOffset(R.dimen.dimen_8)))

        selectableRecyclerView.adapter = selectableSubjectAdapter
        selectableRecyclerView.layoutManager = getChipsLayoutManager()

        selectedRecyclerView.adapter = selectedSubjectAdapter
        selectedRecyclerView.layoutManager = getChipsLayoutManager()


        viewModel.selectableSubject.observe(this, Observer {
            selectableSubjectAdapter.setModel(it!!)
        })

        viewModel.selectedSubject.observe(this, Observer {
            selectedSubjectAdapter.setModel(it!!)
        })

        viewModel.selectableSubjectDeletedEvent.observe(this, Observer {
            viewModel.addSelectedSubject(viewModel.selectableSubject.value!![it!!])
            selectedSubjectAdapter.notifyItemInserted(viewModel.selectedSubjectList.size - 1)
            selectableSubjectAdapter.notifyItemRemoved(it)
        })

        viewModel.selectedSubjectDeletedEvent.observe(this, Observer {
            viewModel.addSelectableSubject(viewModel.selectedSubject.value!![it!!])

            selectableSubjectAdapter.notifyItemInserted(viewModel.selectableSubjectList.size - 1)
            selectedSubjectAdapter.notifyItemRemoved(it)
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
