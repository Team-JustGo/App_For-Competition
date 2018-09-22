package com.justgo.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.OvershootInterpolator
import com.justgo.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    val container by lazy { find<ConstraintLayout>(R.id.activity_main) }
    val travelCardView by lazy { find<ConstraintLayout>(R.id.main_startTravel_constraint) }

    var isTravelStart = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_startTravel_cardView.onClick {
            if (!isTravelStart) {
                updateConstraints(R.layout.activity_main_travel, container)
                updateConstraints(R.layout.activity_main_travel, travelCardView)
                isTravelStart = true
            } else {
                updateConstraints(R.layout.activity_main, container)
                updateConstraints(R.layout.activity_main_travel, travelCardView)
                isTravelStart = false
            }
        }

    }

    fun updateConstraints(@LayoutRes id: Int, layout: ConstraintLayout) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this, id)
        newConstraintSet.applyTo(layout)
        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator()
        TransitionManager.beginDelayedTransition(layout)
    }
}
