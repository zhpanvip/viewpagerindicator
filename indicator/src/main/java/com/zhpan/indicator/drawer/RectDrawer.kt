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

import android.animation.ArgbEvaluator
import android.graphics.Canvas
import android.graphics.RectF
import android.view.View

import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.option.IndicatorOptions
import com.zhpan.indicator.utils.IndicatorUtils

/**
 * <pre>
 * Created by zhpan on 2020/1/17.
 * Description:
</pre> *
 */
abstract class RectDrawer internal constructor(indicatorOptions: IndicatorOptions) : BaseDrawer(
  indicatorOptions
) {
  internal var mRectF: RectF = RectF()

  override fun onDraw(canvas: Canvas) {
    val pageSize = mIndicatorOptions.pageSize
    if (pageSize > 1 || mIndicatorOptions.showIndicatorOneItem && pageSize == 1) {
      if (isWidthEquals && mIndicatorOptions.slideMode != IndicatorSlideMode.NORMAL) {
        drawUncheckedSlider(canvas, pageSize)
        drawCheckedSlider(canvas)
      } else {    // 单独处理normalWidth与checkedWidth不一致的情况
        if (mIndicatorOptions.slideMode == IndicatorSlideMode.SCALE) {
          for (i in 0 until pageSize) {
            drawScaleSlider(canvas, i)
          }
        } else {
          drawInequalitySlider(canvas, pageSize)
        }
      }
    }
  }

  private fun drawScaleSlider(
    canvas: Canvas, i: Int
  ) {
    val checkedColor = mIndicatorOptions.checkedSliderColor
    val indicatorGap = mIndicatorOptions.sliderGap
    val sliderHeight = mIndicatorOptions.sliderHeight
    val currentPosition = mIndicatorOptions.currentPosition
    val normalWidth = mIndicatorOptions.normalSliderWidth
    val checkedWidth = mIndicatorOptions.checkedSliderWidth
    if (argbEvaluator == null) {
      argbEvaluator = ArgbEvaluator()
    }
    when {
      i < currentPosition -> {
        mPaint.color = mIndicatorOptions.normalSliderColor
        val left: Float = if (currentPosition == mIndicatorOptions.pageSize - 1) {
          (i * normalWidth + i * indicatorGap) + (checkedWidth - normalWidth) * mIndicatorOptions.slideProgress
        } else {
          (i * normalWidth + i * indicatorGap)
        }
        mRectF.set(left, 0f, left + normalWidth, sliderHeight)
        drawRect(canvas, sliderHeight, sliderHeight)
      }

      i == currentPosition -> {
        mPaint.color = checkedColor
        val slideProgress = mIndicatorOptions.slideProgress
        if (currentPosition == mIndicatorOptions.pageSize - 1) {
          argbEvaluator?.apply {
            val evaluate = evaluate(
              slideProgress, checkedColor, mIndicatorOptions.normalSliderColor
            )
            mPaint.color = (evaluate as Int)
          }
          val right =
            (mIndicatorOptions.pageSize - 1) * (normalWidth + mIndicatorOptions.sliderGap) + checkedWidth
          val left = right - checkedWidth + (checkedWidth - normalWidth) * (slideProgress)
          mRectF.set(left, 0f, right, sliderHeight)
          drawRect(canvas, sliderHeight, sliderHeight)
        } else {
          if (slideProgress < 1) {
            argbEvaluator?.apply {
              val evaluate = evaluate(
                slideProgress, checkedColor, mIndicatorOptions.normalSliderColor
              )
              mPaint.color = (evaluate as Int)
            }
            val left = i * normalWidth + i * indicatorGap
            val right = left + normalWidth + (checkedWidth - normalWidth) * (1 - slideProgress)
            mRectF.set(left, 0f, right, sliderHeight)
            drawRect(canvas, sliderHeight, sliderHeight)
          }
        }

        if (currentPosition == mIndicatorOptions.pageSize - 1) {
          if (slideProgress > 0) {
            argbEvaluator?.apply {
              val evaluate = evaluate(
                1 - slideProgress, checkedColor, mIndicatorOptions.normalSliderColor
              )
              mPaint.color = evaluate as Int
            }
            val left = 0f
            val right = left + normalWidth + (checkedWidth - normalWidth) * slideProgress

            mRectF.set(left, 0f, right, sliderHeight)
            drawRect(canvas, sliderHeight, sliderHeight)
          }
        } else {
          if (slideProgress > 0) {
            argbEvaluator?.apply {
              val evaluate = evaluate(
                1 - slideProgress, checkedColor, mIndicatorOptions.normalSliderColor
              )
              mPaint.color = evaluate as Int
            }
            val right =
              i * normalWidth + i * indicatorGap + normalWidth + (indicatorGap + checkedWidth)
            val left = right - (normalWidth) - (checkedWidth - normalWidth) * (slideProgress)
            mRectF.set(left, 0f, right, sliderHeight)
            drawRect(canvas, sliderHeight, sliderHeight)
          }
        }
      }

      else -> {
        if ((currentPosition + 1 != i || mIndicatorOptions.slideProgress == 0f)) { // 避免多余绘制
          mPaint.color = mIndicatorOptions.normalSliderColor
          val left = i * minWidth + i * indicatorGap + (checkedWidth - minWidth)
          mRectF.set(left, 0f, left + minWidth, sliderHeight)
          drawRect(canvas, sliderHeight, sliderHeight)
        }
      }
    }
  }

  private fun drawUncheckedSlider(
    canvas: Canvas, pageSize: Int
  ) {
    for (i in 0 until pageSize) {
      mPaint.color = mIndicatorOptions.normalSliderColor
      val left = i * maxWidth + i * +mIndicatorOptions.sliderGap + (maxWidth - minWidth)
      mRectF.set(left, 0f, left + minWidth, mIndicatorOptions.sliderHeight)
      drawRect(canvas, mIndicatorOptions.sliderHeight, mIndicatorOptions.sliderHeight)
    }
  }

  private fun drawInequalitySlider(
    canvas: Canvas, pageSize: Int
  ) {
    var left = 0f
    for (i in 0 until pageSize) {
      val sliderWidth = (if (i == mIndicatorOptions.currentPosition) maxWidth else minWidth)
      mPaint.color =
        if (i == mIndicatorOptions.currentPosition) mIndicatorOptions.checkedSliderColor else mIndicatorOptions.normalSliderColor
      mRectF.set(left, 0f, left + sliderWidth, mIndicatorOptions.sliderHeight)
      drawRect(canvas, mIndicatorOptions.sliderHeight, mIndicatorOptions.sliderHeight)
      left += sliderWidth + mIndicatorOptions.sliderGap
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
    if (argbEvaluator == null) {
      argbEvaluator = ArgbEvaluator()
    }
    if (slideProgress < 0.99) {
      argbEvaluator?.apply {
        val evaluate = evaluate(
          slideProgress, mIndicatorOptions.checkedSliderColor, mIndicatorOptions.normalSliderColor
        )
        mPaint.color = (evaluate as Int)
      }
      mRectF.set(left, 0f, left + minWidth, mIndicatorOptions.sliderHeight)
      drawRect(canvas, mIndicatorOptions.sliderHeight, mIndicatorOptions.sliderHeight)
    }

    var nextSliderLeft = left + mIndicatorOptions.sliderGap + mIndicatorOptions.normalSliderWidth
    if (currentPosition == mIndicatorOptions.pageSize - 1) {
      nextSliderLeft = 0f
    }

    argbEvaluator?.apply {
      val evaluate = evaluate(
        1 - slideProgress, mIndicatorOptions.checkedSliderColor, mIndicatorOptions.normalSliderColor
      )
      mPaint.color = evaluate as Int
    }
    mRectF.set(nextSliderLeft, 0f, nextSliderLeft + minWidth, mIndicatorOptions.sliderHeight)
    drawRect(canvas, mIndicatorOptions.sliderHeight, mIndicatorOptions.sliderHeight)
  }

  private fun drawWormSlider(canvas: Canvas) {
    val sliderHeight = mIndicatorOptions.sliderHeight
    val slideProgress = mIndicatorOptions.slideProgress
    val currentPosition = mIndicatorOptions.currentPosition
    val distance = mIndicatorOptions.sliderGap + mIndicatorOptions.normalSliderWidth
    val startCoordinateX =
      IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, currentPosition)
    val left = startCoordinateX + (distance * (slideProgress - 0.5f) * 2.0f).coerceAtLeast(
      0f
    ) - mIndicatorOptions.normalSliderWidth / 2
    val right = startCoordinateX + (distance * slideProgress * 2f).coerceAtMost(
      distance
    ) + mIndicatorOptions.normalSliderWidth / 2
    mRectF.set(left, 0f, right, sliderHeight)
    drawRect(canvas, sliderHeight, sliderHeight)
  }

  private fun drawSmoothSlider(canvas: Canvas) {
    val currentPosition = mIndicatorOptions.currentPosition
    val indicatorGap = mIndicatorOptions.sliderGap
    val sliderHeight = mIndicatorOptions.sliderHeight
    val left =
      currentPosition * maxWidth + currentPosition * +indicatorGap + (maxWidth + indicatorGap) * mIndicatorOptions.slideProgress
    mRectF.set(left, 0f, left + maxWidth, sliderHeight)
    drawRect(canvas, sliderHeight, sliderHeight)
  }

  abstract fun drawRect(
    canvas: Canvas, rx: Float, ry: Float
  )
}
