package com.justgo.ui.main


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.JsonObject

import com.justgo.R
import com.justgo.Util.DataBindingFragment
import com.justgo.databinding.FragmentSetRangeBinding
import io.apptik.widget.MultiSlider
import kotlinx.android.synthetic.main.fragment_set_range.*
import org.jetbrains.anko.find

class SetRangeFragment : DataBindingFragment<FragmentSetRangeBinding>() {
    override fun getLayoutId() = R.layout.fragment_set_range

    val viewModel by lazy { ViewModelProviders.of(activity!!)[MainViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)

        val slider: MultiSlider = view.find(R.id.setRange_slider)
        val rangeStartTextView: TextView = view.find(R.id.setRange_rangeStart_tv)
        val rangeEndTextView: TextView = view.find(R.id.setRange_rangeEnd_tv)

        slider.getThumb(0).value = viewModel.minRange.value!!
        slider.getThumb(1).value = viewModel.maxRange.value!!
        slider.setOnThumbValueChangeListener { multiSlider, thumb, thumbIndex, value ->
            when (thumbIndex) {
                0 -> viewModel.setMinRange(value)
                1 -> viewModel.setMaxRange(value)
            }
        }

        viewModel.minRange.observe(this, Observer {
            setRange_rangeStart_tv.text = it.toString()
        })

        viewModel.maxRange.observe(this, Observer {
            setRange_rangeEnd_tv.text = it.toString()
        })

        return view
    }


}
