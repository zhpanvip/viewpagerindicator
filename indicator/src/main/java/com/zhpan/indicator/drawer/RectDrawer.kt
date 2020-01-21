package com.zhpan.indicator.drawer

import android.graphics.Canvas

import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.option.IndicatorOptions
import com.zhpan.indicator.utils.IndicatorUtils

/**
 * <pre>
 * Created by zhpan on 2020/1/17.
 * Description:
</pre> *
 */
open class RectDrawer internal constructor(indicatorOptions: IndicatorOptions) : BaseDrawer(indicatorOptions) {

    override fun onDraw(canvas: Canvas) {
        val pageSize = mIndicatorOptions.pageSize
        if (pageSize > 1) {
            if (isWidthEquals && mIndicatorOptions.slideMode != IndicatorSlideMode.NORMAL) {
                drawUncheckedSlider(canvas, pageSize)
                drawCheckedSlider(canvas)
            } else {    // 单独处理normalWidth与checkedWidth不一致的情况
                for (i in 0 until pageSize) {
                    drawInequalitySlider(canvas, i)
                }
            }
        }
    }

    private fun drawUncheckedSlider(canvas: Canvas, pageSize: Int) {
        for (i in 0 until pageSize) {
            mPaint.color = mIndicatorOptions.normalSliderColor
            val sliderHeight = mIndicatorOptions.sliderHeight
            val left = i * maxWidth + i * +mIndicatorOptions.sliderGap + (maxWidth - minWidth)
            mRectF.set(left, 0f, left + minWidth, sliderHeight)
            drawRoundRect(canvas, sliderHeight, sliderHeight)
        }
    }

    private fun drawInequalitySlider(canvas: Canvas, i: Int) {
        val normalColor = mIndicatorOptions.normalSliderColor
        val indicatorGap = mIndicatorOptions.sliderGap
        val sliderHeight = mIndicatorOptions.sliderHeight
        val currentPosition = mIndicatorOptions.currentPosition
        if (i < currentPosition) {
            mPaint.color = normalColor
            val left = i * minWidth + i * indicatorGap
            mRectF.set(left, 0f, left + minWidth, sliderHeight)
            drawRoundRect(canvas, sliderHeight, sliderHeight)
        } else if (i == currentPosition) {
            mPaint.color = mIndicatorOptions.checkedSliderColor
            val left = i * minWidth + i * indicatorGap
            mRectF.set(left, 0f, left + minWidth + (maxWidth - minWidth), sliderHeight)
            drawRoundRect(canvas, sliderHeight, sliderHeight)
        } else {
            mPaint.color = normalColor
            val left = i * minWidth + i * indicatorGap + (maxWidth - minWidth)
            mRectF.set(left, 0f, left + minWidth, sliderHeight)
            drawRoundRect(canvas, sliderHeight, sliderHeight)
        }
    }

    private fun drawCheckedSlider(canvas: Canvas) {
        mPaint.color = mIndicatorOptions.checkedSliderColor
        when (mIndicatorOptions.slideMode) {
            IndicatorSlideMode.SMOOTH -> drawSmoothSlider(canvas)
            IndicatorSlideMode.WORM -> drawWormSlider(canvas)
        }
    }

    private fun drawWormSlider(canvas: Canvas) {
        val sliderHeight = mIndicatorOptions.sliderHeight
        val slideProgress = mIndicatorOptions.slideProgress
        val currentPosition = mIndicatorOptions.currentPosition
        val distance = mIndicatorOptions.sliderGap + mIndicatorOptions.normalSliderWidth
        val startCoordinateX = IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, currentPosition)
        val left = startCoordinateX + Math.max(distance * (slideProgress - 0.5f) * 2.0f, 0f) - mIndicatorOptions.normalSliderWidth / 2
        val right = startCoordinateX + Math.min(distance * slideProgress * 2f, distance) + mIndicatorOptions.normalSliderWidth / 2
        mRectF.set(left, 0f, right, sliderHeight)
        drawRoundRect(canvas, sliderHeight, sliderHeight)
    }

    private fun drawSmoothSlider(canvas: Canvas) {
        val currentPosition = mIndicatorOptions.currentPosition
        val indicatorGap = mIndicatorOptions.sliderGap
        val sliderHeight = mIndicatorOptions.sliderHeight
        val left = currentPosition * maxWidth + currentPosition * +indicatorGap + (maxWidth + indicatorGap) * mIndicatorOptions.slideProgress
        mRectF.set(left, 0f, left + maxWidth, sliderHeight)
        drawRoundRect(canvas, sliderHeight, sliderHeight)
    }

    protected open fun drawRoundRect(canvas: Canvas, rx: Float, ry: Float) {
        drawDash(canvas)
    }

    protected open fun drawDash(canvas: Canvas) {}
}
