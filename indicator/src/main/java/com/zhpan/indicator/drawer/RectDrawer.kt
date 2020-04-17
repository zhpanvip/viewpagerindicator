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
                    if (mIndicatorOptions.slideMode == IndicatorSlideMode.SCALE) {
                        drawScaleSlider(canvas, i)
                    } else {
                        drawInequalitySlider(canvas, i)
                    }
                }


            }
        }
    }

    private fun drawScaleSlider(canvas: Canvas, i: Int) {
        val normalColor = mIndicatorOptions.normalSliderColor
        val checkedColor = mIndicatorOptions.checkedSliderColor
        val indicatorGap = mIndicatorOptions.sliderGap
        val sliderHeight = mIndicatorOptions.sliderHeight
        val currentPosition = mIndicatorOptions.currentPosition
        when {
            i < currentPosition -> {
                mPaint.color = normalColor
                val left = i * minWidth + i * indicatorGap
                mRectF.set(left, 0f, left + minWidth, sliderHeight)
                drawRoundRect(canvas, sliderHeight, sliderHeight)
            }
            i == currentPosition -> {
                mPaint.color = mIndicatorOptions.checkedSliderColor
                val slideProgress = mIndicatorOptions.slideProgress
                if (slideProgress < 1) {
                    val evaluate = argbEvaluator?.evaluate(slideProgress, mIndicatorOptions.checkedSliderColor, mIndicatorOptions.normalSliderColor)
                    mPaint.color = (evaluate as Int)
                    val left = i * minWidth + i * indicatorGap
                    val right = left + minWidth + (maxWidth - minWidth) * (1 - slideProgress)
                    mRectF.set(left, 0f, right, sliderHeight)
                    drawRoundRect(canvas, sliderHeight, sliderHeight)
                }

                if (currentPosition == mIndicatorOptions.pageSize - 1) {
                    if (slideProgress > 0) {
                        val evaluate = argbEvaluator?.evaluate(1 - slideProgress, mIndicatorOptions.checkedSliderColor, mIndicatorOptions.normalSliderColor)
                        mPaint.color = evaluate as Int
                        val left = 0f
                        val right = left + minWidth + (maxWidth - minWidth) * slideProgress

                        mRectF.set(left, 0f, right, sliderHeight)
                        drawRoundRect(canvas, sliderHeight, sliderHeight)
                    }
                } else {
                    if (slideProgress > 0) {
                        val evaluate = argbEvaluator?.evaluate(1 - slideProgress, mIndicatorOptions.checkedSliderColor, mIndicatorOptions.normalSliderColor)
                        mPaint.color = evaluate as Int
                        val right = i * minWidth + i * indicatorGap + minWidth + (indicatorGap + maxWidth)
                        val left = right - (minWidth) - (maxWidth - minWidth) * (slideProgress)
                        mRectF.set(left, 0f, right, sliderHeight)
                        drawRoundRect(canvas, sliderHeight, sliderHeight)
                    }
                }
            }
            else -> {
                if ((currentPosition + 1 != i || mIndicatorOptions.slideProgress == 0f)) {
                    mPaint.color = normalColor
                    val left = i * minWidth + i * indicatorGap + (maxWidth - minWidth)
                    mRectF.set(left, 0f, left + minWidth, sliderHeight)
                    drawRoundRect(canvas, sliderHeight, sliderHeight)
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
        when {
            i < currentPosition -> {
                mPaint.color = normalColor
                val left = i * minWidth + i * indicatorGap
                mRectF.set(left, 0f, left + minWidth, sliderHeight)
                drawRoundRect(canvas, sliderHeight, sliderHeight)
            }
            i == currentPosition -> {
                mPaint.color = mIndicatorOptions.checkedSliderColor
                val left = i * minWidth + i * indicatorGap
                mRectF.set(left, 0f, left + minWidth + (maxWidth - minWidth), sliderHeight)
                drawRoundRect(canvas, sliderHeight, sliderHeight)
            }
            else -> {
                mPaint.color = normalColor
                val left = i * minWidth + i * indicatorGap + (maxWidth - minWidth)
                mRectF.set(left, 0f, left + minWidth, sliderHeight)
                drawRoundRect(canvas, sliderHeight, sliderHeight)
            }
        }
    }

    private fun drawCheckedSlider(canvas: Canvas) {
        mPaint.color = mIndicatorOptions.checkedSliderColor
        when (mIndicatorOptions.slideMode) {
            IndicatorSlideMode.SMOOTH -> drawSmoothSlider(canvas)
            IndicatorSlideMode.WORM -> drawWormSlider(canvas)
            IndicatorSlideMode.COLOR -> drawColorSlider(canvas)
        }
    }

    private fun drawColorSlider(canvas: Canvas) {
        val currentPosition = mIndicatorOptions.currentPosition
        val slideProgress = mIndicatorOptions.slideProgress
        val left = currentPosition * minWidth + currentPosition * mIndicatorOptions.sliderGap
        if (slideProgress < 0.99) {
            val evaluate = argbEvaluator?.evaluate(slideProgress, mIndicatorOptions.checkedSliderColor, mIndicatorOptions.normalSliderColor)
            mPaint.color = (evaluate as Int)
            mRectF.set(left, 0f, left + minWidth, mIndicatorOptions.sliderHeight)
            drawRoundRect(canvas, mIndicatorOptions.sliderHeight, mIndicatorOptions.sliderHeight)
        }
        if (currentPosition == mIndicatorOptions.pageSize - 1) {

        } else {
            if (slideProgress > 0.01) {
                val evaluate = argbEvaluator?.evaluate(1 - slideProgress, mIndicatorOptions.checkedSliderColor, mIndicatorOptions.normalSliderColor)
                mPaint.color = evaluate as Int
                val nextSliderLeft = left + mIndicatorOptions.sliderGap + mIndicatorOptions.normalSliderWidth
                mRectF.set(nextSliderLeft, 0f, nextSliderLeft + minWidth, mIndicatorOptions.sliderHeight)
                drawRoundRect(canvas, mIndicatorOptions.sliderHeight, mIndicatorOptions.sliderHeight)
            }
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
