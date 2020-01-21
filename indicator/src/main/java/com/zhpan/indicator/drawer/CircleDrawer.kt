package com.zhpan.indicator.drawer

import android.graphics.Canvas
import android.graphics.RectF
import com.zhpan.indicator.enums.IndicatorSlideMode

import com.zhpan.indicator.option.IndicatorOptions
import com.zhpan.indicator.utils.IndicatorUtils

/**
 * <pre>
 * Created by zhpan on 2019/11/23.
 * Description: Circle Indicator drawer.
</pre> *
 */
class CircleDrawer internal constructor(indicatorOptions: IndicatorOptions) : BaseDrawer(indicatorOptions) {

    private val rectF = RectF()

    override fun measureHeight(): Int {
        return maxWidth.toInt()
    }

    override fun onDraw(canvas: Canvas) {
        if (mIndicatorOptions.pageSize > 1) {
            drawNormal(canvas)
            drawSlider(canvas)
        }
    }

    private fun drawNormal(canvas: Canvas) {
        val normalIndicatorWidth = mIndicatorOptions.normalSliderWidth
        mPaint.color = mIndicatorOptions.normalSliderColor
        for (i in 0 until mIndicatorOptions.pageSize) {
            val coordinateX = IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, i)
            val coordinateY = IndicatorUtils.getCoordinateY(maxWidth)
            drawCircle(canvas, coordinateX, coordinateY, normalIndicatorWidth / 2)
        }
    }

    private fun drawSlider(canvas: Canvas) {
        mPaint.color = mIndicatorOptions.checkedSliderColor
        when (mIndicatorOptions.slideMode) {
            IndicatorSlideMode.NORMAL, IndicatorSlideMode.SMOOTH -> drawCircleSlider(canvas)
            IndicatorSlideMode.WORM -> drawWormSlider(canvas, mIndicatorOptions.normalSliderWidth)
        }
    }

    private fun drawCircleSlider(canvas: Canvas) {
        val currentPosition = mIndicatorOptions.currentPosition
        val startCoordinateX = IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, currentPosition)
        val endCoordinateX = IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, (currentPosition + 1) % mIndicatorOptions.pageSize)
        val coordinateX = startCoordinateX + (endCoordinateX - startCoordinateX) * mIndicatorOptions.slideProgress
        val coordinateY = IndicatorUtils.getCoordinateY(maxWidth)
        val radius = mIndicatorOptions.checkedSliderWidth / 2
        drawCircle(canvas, coordinateX, coordinateY, radius)
    }

    private fun drawWormSlider(canvas: Canvas, sliderHeight: Float) {
        val slideProgress = mIndicatorOptions.slideProgress
        val currentPosition = mIndicatorOptions.currentPosition
        val distance = mIndicatorOptions.sliderGap + mIndicatorOptions.normalSliderWidth
        val startCoordinateX = IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, currentPosition)
        val left = startCoordinateX + Math.max(distance * (slideProgress - 0.5f) * 2.0f, 0f) - mIndicatorOptions.normalSliderWidth / 2
        val right = startCoordinateX + Math.min(distance * slideProgress * 2f, distance) + mIndicatorOptions.normalSliderWidth / 2
        rectF.set(left, 0f, right, sliderHeight)
        canvas.drawRoundRect(rectF, sliderHeight, sliderHeight, mPaint)
    }

    private fun drawCircle(canvas: Canvas, coordinateX: Float, coordinateY: Float, radius: Float) {
        canvas.drawCircle(coordinateX, coordinateY, radius, mPaint)
    }
}
