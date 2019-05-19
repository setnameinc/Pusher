package com.setname.pusher.mvp.customviews.customsnackbar

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.design.snackbar.ContentViewCallback
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.SnackbarContentLayout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.setname.pusher.R
import com.setname.pusher.mvp.customviews.roundtimer.RoundTimerView


class CustomSnackbarWithTimerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    var textView:TextView
    var action:Button

    init {

        Log.i("CustomSnack", "context")

        View.inflate(context, R.layout.custom_view_snackbar_with_timer, this)
        this.textView = findViewById(R.id.custom_view_snackbar_with_timer_text)
        this.action = findViewById(R.id.custom_view_snackbar_with_timer_button_undo)

        clipToPadding = false

    }

    override fun animateContentOut(p0: Int, p1: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animateContentIn(p0: Int, p1: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}