package com.justgo.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.Fade
import android.transition.TransitionManager
import android.view.animation.OvershootInterpolator
import com.justgo.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_backdrop.*
import kotlinx.android.synthetic.main.main_toolbar.*
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    val container by lazy { find<ConstraintLayout>(R.id.activity_main) }
    val travelCardView by lazy { find<ConstraintLayout>(R.id.main_startTravel_constraint) }

    var isTravelStart = false
    var isBackdropOpened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_startTravel_header.onClick {
            if (!isTravelStart) {
                updateConstraints(R.layout.activity_main_travel, container)
                updateConstraints(R.layout.activity_main_travel, travelCardView)
                isTravelStart = true
                isBackdropOpened = false
            } else {
                updateConstraints(R.layout.activity_main, container)
                updateConstraints(R.layout.activity_main_travel, travelCardView)
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
