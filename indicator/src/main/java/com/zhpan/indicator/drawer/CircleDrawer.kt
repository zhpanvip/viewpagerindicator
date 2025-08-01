package com.zhpan.indicator.drawer

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
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
import android.view.View

class CircleDrawer internal constructor(indicatorOptions: IndicatorOptions, private val view: View) : BaseDrawer(
  indicatorOptions
) {    
  private var animationProgress: Float = 0f
  private var animator: ValueAnimator? = null
  private var isAnimating: Boolean = false
  private var targetPosition: Int = 0

  private val rectF = RectF()

  override fun measureHeight(): Int {
    return maxWidth.toInt() + INDICATOR_PADDING_ADDITION
  }

  override fun onDraw(canvas: Canvas) {
    val pageSize = mIndicatorOptions.pageSize
    if (pageSize > 1 || mIndicatorOptions.showIndicatorOneItem && pageSize == 1) {
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
      IndicatorSlideMode.WORM -> drawWormSlider(canvas)
      IndicatorSlideMode.SCALE -> drawScaleSlider(canvas)
      IndicatorSlideMode.COLOR -> drawColor(canvas)
    }
  }

  /**
   * 开始指示器动画
   * @param fromPosition 起始位置
   * @param toPosition 目标位置
   */
  fun startAnimation(fromPosition: Int, toPosition: Int) {
    if (!mIndicatorOptions.animateAfterPageChange || isAnimating) {
      return
    }

    targetPosition = toPosition
    animationProgress = 0f
    isAnimating = true

    animator?.cancel()
    animator = ValueAnimator.ofFloat(0f, 1f)
    animator?.duration = mIndicatorOptions.animationDuration.toLong()
    animator?.addUpdateListener {
      animationProgress = it.animatedValue as Float
      invalidate()
    }
    animator?.addListener(object : android.animation.Animator.AnimatorListener {
      override fun onAnimationStart(animation: android.animation.Animator) {}

      override fun onAnimationEnd(animation: android.animation.Animator) {
        isAnimating = false
        animationProgress = 0f
        mIndicatorOptions.currentPosition = targetPosition
      }

      override fun onAnimationCancel(animation: android.animation.Animator) {
        isAnimating = false
      }

      override fun onAnimationRepeat(animation: android.animation.Animator) {}
    })
    animator?.start()
  }

  private fun invalidate() {
    // 触发重绘
    view.postInvalidate()
  }

  private fun drawColor(canvas: Canvas) {
    if (argbEvaluator == null) {
      argbEvaluator = ArgbEvaluator()
    }
    val currentPosition = if (mIndicatorOptions.animateAfterPageChange && isAnimating) {
      targetPosition - 1
    } else {
      mIndicatorOptions.currentPosition
    }
    val slideProgress = if (mIndicatorOptions.animateAfterPageChange && isAnimating) {
      animationProgress
    } else {
      mIndicatorOptions.slideProgress
    }
    val coordinateX = IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, currentPosition)
    val coordinateY = IndicatorUtils.getCoordinateY(maxWidth)

    argbEvaluator?.apply {
      val evaluate = evaluate(
        slideProgress, mIndicatorOptions.checkedSliderColor, mIndicatorOptions.normalSliderColor
      )
      mPaint.color = (evaluate as Int)
    }
    drawCircle(canvas, coordinateX, coordinateY, mIndicatorOptions.normalSliderWidth / 2)

    argbEvaluator?.apply {
      // 绘制可循环的ViewPager指示器渐变
      val evaluate = evaluate(
        1 - slideProgress, mIndicatorOptions.checkedSliderColor, mIndicatorOptions.normalSliderColor
      )
      mPaint.color = evaluate as Int
    }
    val nextCoordinateX = if (currentPosition == mIndicatorOptions.pageSize - 1) {
      IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, 0)
    } else {
      coordinateX + mIndicatorOptions.sliderGap + mIndicatorOptions.normalSliderWidth
    }
    drawCircle(canvas, nextCoordinateX, coordinateY, mIndicatorOptions.checkedSliderWidth / 2)
  }

  private fun drawScaleSlider(canvas: Canvas) {
    val currentPosition = if (mIndicatorOptions.animateAfterPageChange && isAnimating) {
      targetPosition - 1
    } else {
      mIndicatorOptions.currentPosition
    }
    val slideProgress = if (mIndicatorOptions.animateAfterPageChange && isAnimating) {
      animationProgress
    } else {
      mIndicatorOptions.slideProgress
    }
    val coordinateX = IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, currentPosition)
    val coordinateY = IndicatorUtils.getCoordinateY(maxWidth)
    if (argbEvaluator == null) {
      argbEvaluator = ArgbEvaluator()
    }
    if (slideProgress < 1) {
      argbEvaluator?.apply {
        val evaluate = evaluate(
          slideProgress, mIndicatorOptions.checkedSliderColor, mIndicatorOptions.normalSliderColor
        )
        mPaint.color = (evaluate as Int)
      }
      val radius =
        mIndicatorOptions.checkedSliderWidth / 2 - (mIndicatorOptions.checkedSliderWidth / 2 - mIndicatorOptions.normalSliderWidth / 2) * slideProgress
      drawCircle(canvas, coordinateX, coordinateY, radius)
    }

    if (currentPosition == mIndicatorOptions.pageSize - 1) {
      val evaluate = argbEvaluator?.evaluate(
        slideProgress, mIndicatorOptions.normalSliderColor, mIndicatorOptions.checkedSliderColor
      )
      mPaint.color = evaluate as Int
      val nextCoordinateX = maxWidth / 2
      val nextRadius = minWidth / 2 + (maxWidth / 2 - minWidth / 2) * (slideProgress)
      drawCircle(canvas, nextCoordinateX, coordinateY, nextRadius)
    } else {
      if (slideProgress > 0) {
        val evaluate = argbEvaluator?.evaluate(
          slideProgress, mIndicatorOptions.normalSliderColor, mIndicatorOptions.checkedSliderColor
        )
        mPaint.color = evaluate as Int
        val nextCoordinateX =
          coordinateX + mIndicatorOptions.sliderGap + mIndicatorOptions.normalSliderWidth
        val nextRadius =
          mIndicatorOptions.normalSliderWidth / 2 + (mIndicatorOptions.checkedSliderWidth / 2 - mIndicatorOptions.normalSliderWidth / 2) * slideProgress
        drawCircle(canvas, nextCoordinateX, coordinateY, nextRadius)
      }
    }
  }

  private fun drawCircleSlider(canvas: Canvas) {
    val currentPosition = if (mIndicatorOptions.animateAfterPageChange && isAnimating) {
      targetPosition - 1
    } else {
      mIndicatorOptions.currentPosition
    }
    val slideProgress = if (mIndicatorOptions.animateAfterPageChange && isAnimating) {
      animationProgress
    } else {
      mIndicatorOptions.slideProgress
    }
    val startCoordinateX = 
      IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, currentPosition)
    val endCoordinateX = IndicatorUtils.getCoordinateX(
      mIndicatorOptions, maxWidth, (currentPosition + 1) % mIndicatorOptions.pageSize
    )
    val coordinateX = 
      startCoordinateX + (endCoordinateX - startCoordinateX) * slideProgress
    val coordinateY = IndicatorUtils.getCoordinateY(maxWidth)
    val radius = mIndicatorOptions.checkedSliderWidth / 2
    drawCircle(canvas, coordinateX, coordinateY, radius)
  }

  private fun drawWormSlider(canvas: Canvas) {
    val sliderHeight = mIndicatorOptions.normalSliderWidth
    val currentPosition = if (mIndicatorOptions.animateAfterPageChange && isAnimating) {
      targetPosition - 1
    } else {
      mIndicatorOptions.currentPosition
    }
    val slideProgress = if (mIndicatorOptions.animateAfterPageChange && isAnimating) {
      animationProgress
    } else {
      mIndicatorOptions.slideProgress
    }
    val distance = mIndicatorOptions.sliderGap + mIndicatorOptions.normalSliderWidth
    val startCoordinateX = 
      IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, currentPosition)
    val left = startCoordinateX + (distance * (slideProgress - 0.5f) * 2.0f).coerceAtLeast(
      0f
    ) - mIndicatorOptions.normalSliderWidth / 2 + INDICATOR_PADDING
    val right = startCoordinateX + (distance * slideProgress * 2f).coerceAtMost(
      distance
    ) + mIndicatorOptions.normalSliderWidth / 2 + INDICATOR_PADDING
    rectF.set(left, INDICATOR_PADDING.toFloat(), right, sliderHeight + INDICATOR_PADDING)
    canvas.drawRoundRect(rectF, sliderHeight, sliderHeight, mPaint)
  }

  private fun drawCircle(
    canvas: Canvas, coordinateX: Float, coordinateY: Float, radius: Float
  ) {
    canvas.drawCircle(
      coordinateX + INDICATOR_PADDING, coordinateY + INDICATOR_PADDING, radius, mPaint
    )
  }
}
