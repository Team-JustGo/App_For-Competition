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
import com.justgo.Connecter.getTourList
import com.justgo.R
import com.justgo.Util.DataBindingActivity
import com.justgo.databinding.ActivityMainBinding
import com.justgo.ui.SplashActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_backdrop.*
import kotlinx.android.synthetic.main.main_toolbar.*
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.http.QueryMap

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
        viewModel.getTravelListEvent.observe(this, Observer {
            //            startActivity<SplashActivity>()
        })
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
                    fragmentTransaction.replace(R.id.main_startTravel_fragment, SetLocationFragment()).commit()
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
                5 -> {
                    toast("되냐")
                    val map = mapOf<String, Any>(
                            "theme" to viewModel.selectableSubject.value!!.map { "$it," },
                            "minDistance" to viewModel.minRange.value!!.toInt() * 1000,
                            "maxDistance" to viewModel.maxRange.value!!.toInt() * 1000,
                            "lat" to viewModel.lat.value!!,
                            "lng" to viewModel.lng.value!!
                    )
                    val theme = viewModel.selectableSubject.value!!.reduce { x, y -> "$x ,$y" }
                    val min = viewModel.minRange.value!!.toInt() * 1000
                    val max = viewModel.maxRange.value!!.toInt() * 1000
                    val lat = viewModel.lat.value!!
                    val lng = viewModel.lng.value!!
                    getTourList(lat, lng, theme, min, max) {
                        onSuccess = {
                            toast("${code()}")
                        }
                        onFailure = {
                            toast("왜안되는데;")
                        }
                    }
                }
            }
        })
    }

    fun updateConstraints(@LayoutRes id: Int, layout: ConstraintLayout) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this, id)
        newConstraintSet.applyTo(layout)
//        val transition = Fade()
//        transition.interpolator = OvershootInterpolator()
        TransitionManager.beginDelayedTransition(layout)
    }
}
