package com.justgo.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.Fade
import android.transition.TransitionManager
import android.view.animation.OvershootInterpolator
import com.justgo.R
import com.justgo.Util.DataBindingActivity
import com.justgo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_backdrop.*
import kotlinx.android.synthetic.main.main_toolbar.*
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : DataBindingActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    val container by lazy { find<ConstraintLayout>(R.id.activity_main) }
    val travelCardView by lazy { find<ConstraintLayout>(R.id.main_startTravel_constraint) }

    val setLocationFragment = SetLocationFragment()
    val selectSubjectFragment = SelectSubjectFragment()
    //    val  = SelectSubjectFragment()
    val setRangeFragment = SetRangeFragment()
    val viewModel by lazy { ViewModelProviders.of(this)[MainViewModel::class.java] }
    var isTravelStart = false
    var isBackdropOpened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainViewModel = viewModel

        main_startTravel_header.onClick {
            if (!isTravelStart || isBackdropOpened) {
                updateConstraints(R.layout.activity_main_travel, container)
                isTravelStart = true
                isBackdropOpened = false
            } else {
                updateConstraints(R.layout.activity_main, container)
                isTravelStart = false
                isBackdropOpened = false
            }
        }

        main_toolbar_list_iv.onClick {
            if (!isBackdropOpened) {
                updateConstraints(R.layout.activity_main_backdrop, container)
                isBackdropOpened = true
            } else {
                updateConstraints(R.layout.activity_main_travel, container)
                isBackdropOpened = false
            }
        }

        main_backdrop_up_iv.onClick {
            updateConstraints(R.layout.activity_main_travel, container)
            isBackdropOpened = false
        }


        viewModel.selectedFragment.observe(this, Observer {
            val fragmentTransaction = supportFragmentManager.beginTransaction()

            when (it) {
                1 -> {
                    fragmentTransaction.replace(R.id.main_startTravel_fragment, setLocationFragment).commit()
                    updateConstraints(R.layout.activity_main_travel, container)
                }
                2 -> {
                    fragmentTransaction.replace(R.id.main_startTravel_fragment, selectSubjectFragment).commit()
                    updateConstraints(R.layout.activity_main_travel, container)
                }
                3 -> {
                    fragmentTransaction.replace(R.id.main_startTravel_fragment, selectSubjectFragment).commit()
                    updateConstraints(R.layout.activity_main_travel, container)
                }
                4 -> {
                    fragmentTransaction.replace(R.id.main_startTravel_fragment, setRangeFragment).commit()
                    updateConstraints(R.layout.activity_main_travel, container)
                }
            }
        })

//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        fragmentTransaction.add(R.id.main_startTravel_fragment, SetRangeFragment())
//        fragmentTransaction.commit()
    }

    fun updateConstraints(@LayoutRes id: Int, layout: ConstraintLayout) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this, id)
        newConstraintSet.applyTo(layout)
        val transition = Fade()
        transition.interpolator = OvershootInterpolator()
        TransitionManager.beginDelayedTransition(layout)
    }
}
