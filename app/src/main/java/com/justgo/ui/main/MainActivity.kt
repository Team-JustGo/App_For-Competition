package com.justgo.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
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
import com.justgo.ui.MyTrip.MyTripActivity
import com.justgo.ui.SelectTrip.SelectTripActivity
import com.justgo.ui.SplashActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_backdrop.*
import kotlinx.android.synthetic.main.main_profile.*
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
    val setTransportationFragment = SetTransportationFragment()
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

        main_myTrips_tv.onClick {
            startActivity(Intent(this@MainActivity, MyTripActivity::class.java))
        }
        viewModel.selectedFragment.observe(this, Observer {
            val fragmentTransaction = supportFragmentManager.beginTransaction()

            when (it) {
                0 -> {
                    fragmentTransaction.replace(R.id.main_startTravel_fragment, SetLocationFragment()).commit()
                    main_toolbar_tv.text = "Set Location"
                    main_toolbar_progress_tv.text = "1 / 4"
                }
                1 -> {
                    fragmentTransaction.replace(R.id.main_startTravel_fragment, SetLocationFragment()).commit()
                    main_toolbar_tv.text = "Set Location"
                    main_toolbar_progress_tv.text = "1 / 4"
                    updateConstraints(R.layout.activity_main_travel, container)
                }
                2 -> {
                    fragmentTransaction.replace(R.id.main_startTravel_fragment, setTransportationFragment).commit()
                    main_toolbar_tv.text = "Set transportation"
                    main_toolbar_progress_tv.text = "2 / 4"
                    updateConstraints(R.layout.activity_main_travel, container)
                }
                3 -> {
                    fragmentTransaction.replace(R.id.main_startTravel_fragment, selectSubjectFragment).commit()
                    main_toolbar_progress_tv.text = "3 / 4"
                    main_toolbar_tv.text = "Travel subject"
                    updateConstraints(R.layout.activity_main_travel, container)
                }
                4 -> {
                    fragmentTransaction.replace(R.id.main_startTravel_fragment, setRangeFragment).commit()
                    main_toolbar_tv.text = "Time limit"
                    main_toolbar_progress_tv.text = "4 / 4"
                    updateConstraints(R.layout.activity_main_travel, container)
                }
                5 -> {
                    val intent = Intent(this@MainActivity, SelectTripActivity::class.java)
                    intent.putExtra("lat", viewModel.lat.value)
                    intent.putExtra("lng", viewModel.lng.value)
                    intent.putExtra("theme", viewModel.generateSubject())
                    intent.putExtra("minDistance", viewModel.minRange.value!!.times(1000))
                    intent.putExtra("maxDistance", viewModel.maxRange.value!!.times(1000))
                    intent.putExtra("transType", 0)
                    startActivity(intent)
//                    viewModel.getTourList()
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
