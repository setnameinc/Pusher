package com.setname.pusher.mvp.utils.roundtimer

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.RelativeLayout
import com.setname.pusher.R
import java.util.logging.Logger

class RoundTimer(val localContext: Context) : RelativeLayout(localContext) {

    //attrs from XML
    private var duration = 0
    private var circleStrokeWidth = 0

    //width and height of this view
    private var widthL = 0
    private var heightL = 0

    //default square size for this view
    private val desiredSize = 56

    //using for calculating circumference
    private var diameterOfCircle = -1

    //circle figure
    private lateinit var circleModel: RectF

    //constructor for XML
    constructor(context: Context, attrs: AttributeSet) : this(context) {

        val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RoundTimer)
        duration = typedArray.getInt(R.styleable.RoundTimer_duration, 5000)
        circleStrokeWidth = typedArray.getInt(R.styleable.RoundTimer_circle_stroke_width, 5)
        typedArray.recycle()

    }

    private val logger by lazy {
        Logger.getLogger("RoundTimer")
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        /**
         * assignments are used here, because onMeasure the size of the view is determined
         */

        diameterOfCircle = heightL //assign after onMeasure
        circleModel = RectF(0f, 0f, widthL.toFloat(), heightL.toFloat())

        addViews()

    }

    private fun addViews() {

        val drawCircle = DrawCircle(localContext)
        val drawText = DrawText(localContext)

        drawCircle.startCircle(duration.toLong())
        drawText.startText(duration.toLong())

        addView(drawCircle)
        addView(drawText)

    }

    inner class DrawCircle(localContext: Context) : View(localContext) {

        //circle length
        private var currentLength = diameterOfCircle * Math.PI.toFloat()

        private val paintCircle = Paint().apply {

            color = Color.GRAY
            this.strokeWidth = circleStrokeWidth.toFloat()
            style = Paint.Style.STROKE

        }

        fun startCircle(duration: Long) {
            setAnimRound(duration)
        }

        private fun setAnimRound(duration: Long) {

            ObjectAnimator.ofFloat(this, "length", 0f, 1f)
                .apply {
                    this.duration = duration
                    this.interpolator = LinearInterpolator()
                }.start()

        }

        //underhood fun
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

            path.addArc(circleModel, 270f, -359f) //from 270 to -360 start drawing from 0 angle instead of 270

            canvas?.drawPath(path, paintCircle)

        }

    }

    inner class DrawText(localContext: Context) : View(localContext) {

        private val paintText = Paint().apply {

            color = Color.BLACK
            textSize = 40f

        }

        private var currentTime = 0

        fun startText(duration: Long) {

            setTimer(duration)

        }

        private fun setTimer(duration: Long) {

            ValueAnimator.ofInt((duration / 1000).toInt(), 0).apply {

                interpolator = LinearInterpolator()
                this.duration = duration

                addUpdateListener {

                    currentTime = it.animatedValue as Int
                    invalidate()

                }

                addListener(object: AnimatorListenerAdapter(){

                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)

                    }

                })

            }.start()

        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)

            canvas?.drawText("$currentTime", circleModel.centerX(), circleModel.centerY(), paintText)

        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            widthL = widthSize
            heightL = heightSize
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            widthL = Math.min(desiredSize, widthSize)
            heightL = Math.min(desiredSize, heightSize)
        } else {
            widthL = desiredSize
            heightL = desiredSize
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

}