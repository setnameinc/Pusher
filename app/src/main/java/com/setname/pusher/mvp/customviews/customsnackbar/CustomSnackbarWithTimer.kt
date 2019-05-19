package com.setname.pusher.mvp.customviews.customsnackbar

import android.support.design.widget.BaseTransientBottomBar
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.pusher.R
import com.setname.pusher.mvp.utils.extentional.findSuitableParent

class CustomSnackbarWithTimer(parent: ViewGroup, content: CustomSnackbarWithTimerView) :
    BaseTransientBottomBar<CustomSnackbarWithTimer>(parent, content, content) {

    init {
        getView().setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.transparent))
        getView().setPadding(0, 0, 0, 0)
    }

    companion object {

        fun make(view: View): CustomSnackbarWithTimer {
            // inflate custom layout

            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )

            val customView = LayoutInflater.from(view.context).inflate(
                R.layout.layout_snackbar_with_timer,
                parent,
                false
            ) as CustomSnackbarWithTimerView

            // We create and return our Snackbar
            return CustomSnackbarWithTimer(
                parent,
                customView
            )

        }

    }

    fun setAction(str: String, clickListener: View.OnClickListener): CustomSnackbarWithTimer {

        val customSnackbarWithTimerView = this.view.getChildAt(0) as CustomSnackbarWithTimerView
        val textView = customSnackbarWithTimerView.textView
        textView.text = str

        if (str.isNotEmpty()){

            customSnackbarWithTimerView.action.setOnClickListener {

                clickListener.onClick(view)
                this.dispatchDismiss(1)

            }

        }



        return this
    }


}