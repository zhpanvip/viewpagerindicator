/*
Copyright (C) 2020 zhpanvip,ViewPagerIndicator Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.zhpan.indicator.drawer

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Paint
import android.view.View
import com.zhpan.indicator.enums.IndicatorOrientation
import com.zhpan.indicator.enums.IndicatorSlideMode

import com.zhpan.indicator.option.IndicatorOptions

/**
 * <pre>
 * Created by zhpan on 2019/11/23.
 * Description:
</pre> *
 */
abstract class BaseDrawer internal constructor(
  internal var mIndicatorOptions: IndicatorOptions,
  val view: View
) :
  IDrawer {

  private val mMeasureResult: MeasureResult
  internal var maxWidth: Float = 0.toFloat()
  internal var minWidth: Float = 0.toFloat()
  internal var mPaint: Paint = Paint()
  internal var argbEvaluator: ArgbEvaluator? = null

  protected var animationProgress: Float = 0f
  protected var animator: ValueAnimator? = null
  protected var isAnimating: Boolean = false
  protected var targetPosition: Int = 0

  companion object {
    const val INDICATOR_PADDING_ADDITION = 6
    const val INDICATOR_PADDING = 3
  }

  protected val isWidthEquals: Boolean
    get() = mIndicatorOptions.normalSliderWidth == mIndicatorOptions.checkedSliderWidth

  init {
    mPaint.isAntiAlias = true
    mMeasureResult = MeasureResult()
    if (mIndicatorOptions.slideMode == IndicatorSlideMode.SCALE
      || mIndicatorOptions.slideMode == IndicatorSlideMode.COLOR
    ) {
      argbEvaluator = ArgbEvaluator()
    }
  }

  override fun onMeasure(
    widthMeasureSpec: Int,
    heightMeasureSpec: Int
  ): MeasureResult {
    maxWidth =
      mIndicatorOptions.normalSliderWidth.coerceAtLeast(mIndicatorOptions.checkedSliderWidth)
    minWidth =
      mIndicatorOptions.normalSliderWidth.coerceAtMost(mIndicatorOptions.checkedSliderWidth)
    if (mIndicatorOptions.orientation == IndicatorOrientation.INDICATOR_VERTICAL) {
      mMeasureResult.setMeasureResult(measureHeight(), measureWidth())
    } else {
      mMeasureResult.setMeasureResult(measureWidth(), measureHeight())
    }
    return mMeasureResult
  }

  protected open fun measureHeight(): Int {
    return mIndicatorOptions.sliderHeight.toInt() + INDICATOR_PADDING
  }

  private fun measureWidth(): Int {
    val pageSize = mIndicatorOptions.pageSize
    val indicatorGap = mIndicatorOptions.sliderGap
    return ((pageSize - 1) * indicatorGap + maxWidth + (pageSize - 1) * minWidth).toInt() + INDICATOR_PADDING_ADDITION
  }

  override fun onLayout(
    changed: Boolean,
    left: Int,
    top: Int,
    right: Int,
    bottom: Int
  ) {
  }

  /**
   * 开始指示器动画
   * @param fromPosition 起始位置
   * @param toPosition 目标位置
   */
  override fun startAnimation(fromPosition: Int, toPosition: Int) {
    if (!mIndicatorOptions.animateAfterPageChanged() || fromPosition == toPosition) {
      return
    }

    if (isAnimating) {
      animator?.cancel()
    }

    targetPosition = calTargetPosition(fromPosition, toPosition)
    animationProgress = 0f
    isAnimating = true

    animator = createValueAnimator(object : AnimatorListenerAdapter() {
      override fun onAnimationEnd(animation: Animator) {
        isAnimating = false
        animationProgress = 0f
        mIndicatorOptions.currentPosition = toPosition
      }

      override fun onAnimationCancel(animation: Animator) {
        isAnimating = false
      }

    })
    if (fromPosition < toPosition) {
      animator?.start()
    } else {
      animator?.reverse()
    }
  }

  private fun createValueAnimator(animatorListener: Animator.AnimatorListener): ValueAnimator? {
    return ValueAnimator.ofFloat(0f, 1f)?.apply {
      duration = mIndicatorOptions.animationDuration.toLong()
      addUpdateListener {
        animationProgress = it.animatedValue as Float
        invalidate()
      }
      addListener(animatorListener)
    }
  }

  fun invalidate() {
    // 触发重绘
    view.invalidate()
  }

  private fun calTargetPosition(fromPosition: Int, toPosition: Int): Int {
    return if (fromPosition < toPosition) {
      toPosition - 1
    } else {
      toPosition
    }
  }

  protected fun calSlideProgress(): Float {
    return if (mIndicatorOptions.animateAfterPageChanged() && isAnimating) {
      animationProgress
    } else {
      mIndicatorOptions.slideProgress
    }
  }

  protected fun calSlidePosition(): Int {
    return if (mIndicatorOptions.animateAfterPageChanged() && isAnimating) {
      targetPosition
    } else {
      mIndicatorOptions.currentPosition
    }
  }

  inner class MeasureResult {

    var measureWidth: Int = 0
      internal set

    var measureHeight: Int = 0
      internal set

    internal fun setMeasureResult(
      measureWidth: Int,
      measureHeight: Int
    ) {
      this.measureWidth = measureWidth
      this.measureHeight = measureHeight
    }
  }
}
