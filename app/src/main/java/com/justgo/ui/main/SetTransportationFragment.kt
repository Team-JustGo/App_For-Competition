package com.justgo.ui.main


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.justgo.R
import com.justgo.util.DataBindingFragment
import com.justgo.databinding.FragmentSetTransportationBinding
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.textColorResource

class SetTransportationFragment : DataBindingFragment<FragmentSetTransportationBinding>() {
    val viewModel by lazy { ViewModelProviders.of(activity!!)[MainViewModel::class.java] }

    override fun getLayoutId(): Int = R.layout.fragment_set_transportation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.mainViewModel = viewModel
        viewModel.trans.observe(this, Observer {
            when (it) {
                1 -> {
                    binding.setTransPublicTransCv.backgroundResource = R.drawable.back_v_round_shape_select_trip
                    binding.setTransPublicTransTv.textColorResource = R.color.colorWhite
                    binding.setTransPublicTransImg.setColorFilter(ContextCompat.getColor(context!!, R.color.white))
                    binding.setTransCarCv.backgroundResource = R.color.white
                    binding.setTransCarTv.textColorResource = R.color.colorPrimary
                    binding.setTransCarImg.setColorFilter(ContextCompat.getColor(context!!, R.color.colorPrimary))
                    binding.setTransWalkCv.backgroundResource = R.color.white
                    binding.setTransWalkTv.textColorResource = R.color.colorPrimary
                    binding.setTransWalkImg.setColorFilter(ContextCompat.getColor(context!!, R.color.colorPrimary))
                }
                0 -> {
                    binding.setTransCarCv.backgroundResource = R.drawable.back_v_round_shape_select_trip
                    binding.setTransCarTv.textColorResource = R.color.colorWhite
                    binding.setTransCarImg.setColorFilter(ContextCompat.getColor(context!!, R.color.white))
                    binding.setTransPublicTransCv.backgroundResource = R.color.white
                    binding.setTransPublicTransTv.textColorResource = R.color.colorPrimary
                    binding.setTransPublicTransImg.setColorFilter(ContextCompat.getColor(context!!, R.color.colorPrimary))
                    binding.setTransWalkCv.backgroundResource = R.color.white
                    binding.setTransWalkTv.textColorResource = R.color.colorPrimary
                    binding.setTransWalkImg.setColorFilter(ContextCompat.getColor(context!!, R.color.colorPrimary))
                }
                2 -> {
                    binding.setTransWalkCv.backgroundResource = R.drawable.back_v_round_shape_select_trip
                    binding.setTransWalkTv.textColorResource = R.color.colorWhite
                    binding.setTransWalkImg.setColorFilter(ContextCompat.getColor(context!!, R.color.white))
                    binding.setTransCarCv.backgroundResource = R.color.white
                    binding.setTransCarTv.textColorResource = R.color.colorPrimary
                    binding.setTransCarImg.setColorFilter(ContextCompat.getColor(context!!, R.color.colorPrimary))
                    binding.setTransPublicTransCv.backgroundResource = R.color.white
                    binding.setTransPublicTransTv.textColorResource = R.color.colorPrimary
                    binding.setTransPublicTransImg.setColorFilter(ContextCompat.getColor(context!!, R.color.colorPrimary))
                }
            }
        })
        return view
    }
}
