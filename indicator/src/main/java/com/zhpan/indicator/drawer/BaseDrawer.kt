package com.zhpan.indicator.drawer

import android.animation.ArgbEvaluator
import android.graphics.Paint
import com.zhpan.indicator.enums.IndicatorSlideMode

import com.zhpan.indicator.option.IndicatorOptions


/**
 * <pre>
 * Created by zhpan on 2019/11/23.
 * Description:
</pre> *
 */
abstract class BaseDrawer internal constructor(internal var mIndicatorOptions: IndicatorOptions) : IDrawer {

    private val mMeasureResult: MeasureResult
    internal var maxWidth: Float = 0.toFloat()
    internal var minWidth: Float = 0.toFloat()
    internal var mPaint: Paint = Paint()
    internal var argbEvaluator: ArgbEvaluator? = null


    protected val isWidthEquals: Boolean
        get() = mIndicatorOptions.normalSliderWidth == mIndicatorOptions.checkedSliderWidth

    init {
        mPaint.isAntiAlias = true
        mMeasureResult = MeasureResult()
        if (mIndicatorOptions.slideMode == IndicatorSlideMode.SCALE
                || mIndicatorOptions.slideMode == IndicatorSlideMode.COLOR) {
            argbEvaluator = ArgbEvaluator()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int): MeasureResult {
        maxWidth = mIndicatorOptions.normalSliderWidth.coerceAtLeast(mIndicatorOptions.checkedSliderWidth)
        minWidth = mIndicatorOptions.normalSliderWidth.coerceAtMost(mIndicatorOptions.checkedSliderWidth)
        mMeasureResult.setMeasureResult(measureWidth(), measureHeight())
        return mMeasureResult
    }

    protected open fun measureHeight(): Int {
        return mIndicatorOptions.sliderHeight.toInt() + 1
    }

    private fun measureWidth(): Int {
        val pageSize = mIndicatorOptions.pageSize
        val indicatorGap = mIndicatorOptions.sliderGap
        return ((pageSize - 1) * indicatorGap + maxWidth + (pageSize - 1) * minWidth).toInt()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {

    }

    inner class MeasureResult {

        var measureWidth: Int = 0
            internal set

        var measureHeight: Int = 0
            internal set

        internal fun setMeasureResult(measureWidth: Int, measureHeight: Int) {
            this.measureWidth = measureWidth
            this.measureHeight = measureHeight
        }

    }
}
