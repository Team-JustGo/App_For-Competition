package com.justgo.ui.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.justgo.R
import io.apptik.widget.MultiSlider
import org.jetbrains.anko.find

class SetRangeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_set_range, container, false)
        val slider: MultiSlider = view.find(R.id.setRange_slider)
        val rangeStartTextView: TextView = view.find(R.id.setRange_rangeStart_tv)
        val rangeEndTextView: TextView = view.find(R.id.setRange_rangeEnd_tv)

        slider.setOnThumbValueChangeListener { multiSlider, thumb, thumbIndex, value ->
            when (thumbIndex) {
                0 -> rangeStartTextView.text = "$value Km"
                1 -> rangeEndTextView.text = "$value Km"
            }
//            thumb.
        }

        return view
    }


}
