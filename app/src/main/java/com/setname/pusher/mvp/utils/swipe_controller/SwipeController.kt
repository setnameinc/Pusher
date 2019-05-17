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

    private enum class SwipeState {
        LEFT_VISIBLE,
        ALL_GONE,
        RIGHT_VISIBLE
    }

    private val rightSidePaint = Paint().apply { this.color = Color.RED }
    private val leftSidePaint = Paint().apply { this.color = Color.parseColor("#43A047") }

    private val imageWidth = 100
    private val textSize = 40f

    private var currentState = SwipeState.ALL_GONE

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

            if (dX > 0) {

                if (currentState != SwipeState.LEFT_VISIBLE) {
                    currentState = SwipeState.LEFT_VISIBLE
                }

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

                c.drawText(
                    "FORCE PUSH",
                    rect.centerX().toFloat(),
                    rect.centerY().toFloat() + textSize / 4,
                    Paint().apply {
                        color = Color.WHITE
                        textAlign = Paint.Align.RIGHT
                        textSize = this@SwipeController.textSize
                    })

            } else if (dX<0){

                if (currentState != SwipeState.RIGHT_VISIBLE) {
                    currentState = SwipeState.RIGHT_VISIBLE
                }

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

                c.drawText("DELETE",
                    rect.centerX().toFloat(),
                    rect.centerY().toFloat() + textSize / 4,
                    Paint().apply {
                        color = Color.WHITE
                        textAlign = Paint.Align.LEFT
                        textSize = this@SwipeController.textSize
                    })

            } else {

                currentState = SwipeState.ALL_GONE

            }

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        }

    }

    override fun getMovementFlags(p0: RecyclerView, p1: RecyclerView.ViewHolder): Int {

        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END

        return makeMovementFlags(0, swipeFlags)

    }

    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean = false

    override fun onSwiped(p0: RecyclerView.ViewHolder, notListPos: Int) {

        Log.i("SwipeController", "swiped")

        when (currentState){

            SwipeState.LEFT_VISIBLE -> {

                controller.forcePush(p0.adapterPosition)

            }

            SwipeState.RIGHT_VISIBLE -> {

                controller.delete(p0.adapterPosition)

            }

        }

    }

}

interface SwipeControllerActions {

    fun delete(pos: Int)
    fun forcePush(pos: Int)

}