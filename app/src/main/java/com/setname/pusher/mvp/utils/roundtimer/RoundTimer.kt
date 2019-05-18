package com.setname.pusher.mvp.utils.roundtimer

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.support.design.widget.CoordinatorLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.RelativeLayout


class RoundTimer(val localContext: Context) : RelativeLayout(localContext) {

    private val sizeOfRound = 140

    private val PROPERTY_LENGHT = sizeOfRound * Math.PI.toFloat()

    inner class DrawCircle(localContext: Context):View(localContext){

        private var currentLength = PROPERTY_LENGHT * 2

        private val paintCircle = Paint().apply {

            color = Color.GRAY
            strokeWidth = 5f
            style = Paint.Style.STROKE

        }

        fun startCircle(duration: Long){
            setAnimRound(duration)
        }

        private fun setAnimRound(duration: Long) {

            val animator = ObjectAnimator.ofFloat(this, "length", 0f, 1f)
            animator.interpolator = LinearInterpolator()
            animator.duration = duration
            animator.start()

            Log.i("RoundTimer", "Start drawing")

        }

        private fun setLength(length: Float) {
            paintCircle.pathEffect =
                createPathEffect(currentLength, length, 0.0f) //offset it's by how much it does not finished
            invalidate()
        }

        private fun createPathEffect(pathLength: Float, phase: Float, offset: Float): PathEffect {
            return DashPathEffect(
                floatArrayOf(pathLength, pathLength),
                Math.max(phase * pathLength, offset)
            )
        }

        private val path = Path()

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)

            path.addArc(300f, 300f,500f, 500f, 270f, -359f) //from 270 to -359 start drawing from 0 angle

            canvas?.drawPath(path, paintCircle)

        }

    }

    inner class DrawText(localContext: Context):View(localContext){

        private val paintText = Paint().apply {

            color = Color.BLACK
            textSize = 40f

        }

        private var currentTime = 0

        fun startText(duration:Long){

            setTimer(duration)

        }

        private fun setTimer(duration: Long){

            val animator = ValueAnimator.ofInt((duration/1000).toInt(), 0)
            animator.addUpdateListener {

                currentTime = it.animatedValue as Int
                invalidate()

            }
            animator.interpolator = LinearInterpolator()
            animator.duration = duration
            animator.start()

        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)

            canvas?.drawText("$currentTime", 500f, 500f, paintText)

        }
    }

    init {

        val drawCircle = DrawCircle(localContext)
        drawCircle.startCircle(5000)

        val drawText = DrawText(localContext)
        drawText.startText(5000)

        addView(drawCircle)
        addView(drawText)


        /*setTimer(5000)*/

    }

}