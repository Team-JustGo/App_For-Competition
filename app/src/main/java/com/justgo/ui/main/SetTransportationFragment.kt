package com.justgo.ui.main


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.justgo.R
import com.justgo.Util.DataBindingFragment
import com.justgo.databinding.FragmentSetTransportationBinding

class SetTransportationFragment : DataBindingFragment<FragmentSetTransportationBinding>() {
    val viewModel by lazy { ViewModelProviders.of(activity!!)[MainViewModel::class.java] }

    override fun getLayoutId(): Int = R.layout.fragment_set_transportation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.mainViewModel = viewModel
        return view
    }
}
