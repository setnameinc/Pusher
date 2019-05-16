package com.setname.pusher.mvp.utils.swipe_controller

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_SWIPE
import android.support.v7.widget.helper.ItemTouchHelper.Callback
import android.util.Log

class SwipeController(private val controller: SwipeControllerActions) :
    Callback() {

    private val rightSidePaint = Paint().apply { this.color = Color.BLACK }
    private val leftSidePaint = Paint().apply { this.color = Color.BLUE }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        if (actionState == ACTION_STATE_SWIPE) {

            val item = viewHolder.itemView

            /*Log.i("SwipeController", "dX = $dX")*/

            if (dX > 0) {

                //left to right

                val rect = Rect(
                    item.left,
                    item.top,
                    item.left + dX.toInt(),
                    item.bottom
                )

                c.drawRect(
                    rect,
                    leftSidePaint
                )

                c.drawText("FORCE PUSH", rect.centerX().toFloat(), rect.centerY().toFloat(), Paint().apply {
                    color = Color.RED
                    textSize = 60f
                })

            } else {

                //right to left

                val rect = Rect(
                    item.right + dX.toInt(),
                    item.top,
                    item.right,
                    item.bottom
                )

                c.drawRect(
                    rect,
                    rightSidePaint
                )

                c.drawText("DELETE", rect.centerX().toFloat(), rect.centerY().toFloat(), Paint().apply {
                    color = Color.RED
                    textSize = 60f
                })

            }

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

/*
            Log.i("SwipeController", "swiping")
*/

        }

    }

    override fun getMovementFlags(p0: RecyclerView, p1: RecyclerView.ViewHolder): Int {

        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END

        return makeMovementFlags(0, swipeFlags)

    }

    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {

        return true

    }

    override fun onSwiped(p0: RecyclerView.ViewHolder, notListPos: Int) {

        Log.i("SwipeController", "swiped")

        controller.delete(p0.adapterPosition)

    }

}

interface SwipeControllerActions {

    fun delete(pos: Int)
    fun forcePush(pos: Int)

}