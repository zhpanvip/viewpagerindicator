package com.zhpan.viewpagerindicator

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.zhpan.indicator.base.BaseIndicatorView
import androidx.core.graphics.createBitmap

/**
 * @ author : zhouweibin
 * @ time: 2019/12/18 17:04.
 * @ desc: 选中与未选中的图片长宽可能不一样
 */
class DrawableIndicator @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseIndicatorView(context!!, attrs, defStyleAttr) {
  // 选中时的Bitmap
  private var mCheckedBitmap: Bitmap? = null
  // 未选中时的Bitmap
  private var mNormalBitmap: Bitmap? = null
  // 图片之间的间距
  private var mIndicatorPadding = 0
  // 选中图片的宽度
  private var mCheckedBitmapWidth = 0
  // 选中图片的高度
  private var mCheckedBitmapHeight = 0
  //未选中图片的宽高
  private var mNormalBitmapWidth = 0
  private var mNormalBitmapHeight = 0
  private var mIndicatorSize: IndicatorSize? = null
  private var normalCanResize = true
  private var checkCanResize = true
  override fun onMeasure(
      widthMeasureSpec: Int,
      heightMeasureSpec: Int
  ) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    val maxHeight = mCheckedBitmapHeight.coerceAtLeast(mNormalBitmapHeight)
    val realWidth =
      mCheckedBitmapWidth + (mNormalBitmapWidth + mIndicatorPadding) * (getPageSize() - 1)
    setMeasuredDimension(realWidth, maxHeight)
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    if (getPageSize() > 1 && mCheckedBitmap != null && mNormalBitmap != null) {
      for (i in 0 until getPageSize()) {
        var left: Int
        var top: Int
        var bitmap = mNormalBitmap
        when {
          i < getCurrentPosition() -> {
            left = (i) * (mNormalBitmapWidth + mIndicatorPadding)
            top = measuredHeight / 2 - mNormalBitmapHeight / 2
          }
          i == getCurrentPosition() -> {
            left = (i) * (mNormalBitmapWidth + mIndicatorPadding)
            top = measuredHeight / 2 - mCheckedBitmapHeight / 2
            bitmap = mCheckedBitmap
          }
          else -> {
            left = (i) * mIndicatorPadding + (i - 1) * mNormalBitmapWidth + mCheckedBitmapWidth
            top = measuredHeight / 2 - mNormalBitmapHeight / 2
          }
        }
        drawIcon(canvas, left, top, bitmap)
      }
    }
  }

  private fun drawIcon(
      canvas: Canvas,
      left: Int,
      top: Int,
      icon: Bitmap?
  ) {
    if (icon == null) {
      return
    }
    canvas.drawBitmap(icon, left.toFloat(), top.toFloat(), null)
  }

  private fun initIconSize() {
    mCheckedBitmap?.let {
      mIndicatorSize?.let {
        if (mCheckedBitmap!!.isMutable && checkCanResize) {
          mCheckedBitmap!!.width = mIndicatorSize!!.checkedWidth
          mCheckedBitmap!!.height = mIndicatorSize!!.checkedHeight
        } else {
          val width = mCheckedBitmap!!.width
          val height = mCheckedBitmap!!.height
          val scaleWidth = mIndicatorSize!!.checkedWidth.toFloat() / width
          val scaleHeight = mIndicatorSize!!.checkedHeight.toFloat() / height
          val matrix = Matrix()
          matrix.postScale(scaleWidth, scaleHeight)
          mCheckedBitmap = Bitmap.createBitmap(mCheckedBitmap!!, 0, 0, width, height, matrix, true)
        }
      }
      mCheckedBitmapWidth = mCheckedBitmap!!.width
      mCheckedBitmapHeight = mCheckedBitmap!!.height
    }
    mNormalBitmap?.let {
      mIndicatorSize?.let {
        if (mNormalBitmap!!.isMutable && normalCanResize) {
          mNormalBitmap!!.width = mIndicatorSize!!.normalWidth
          mNormalBitmap!!.height = mIndicatorSize!!.normalHeight
        } else {
          val width = mNormalBitmap!!.width
          val height = mNormalBitmap!!.height
          val scaleWidth = mIndicatorSize!!.normalWidth.toFloat() / mNormalBitmap!!.width
          val scaleHeight = mIndicatorSize!!.normalHeight.toFloat() / mNormalBitmap!!.height
          val matrix = Matrix()
          matrix.postScale(scaleWidth, scaleHeight)
          mNormalBitmap = Bitmap.createBitmap(mNormalBitmap!!, 0, 0, width, height, matrix, true)
        }
      }
      mNormalBitmapWidth = mNormalBitmap!!.width
      mNormalBitmapHeight = mNormalBitmap!!.height
    }
  }

  fun setIndicatorDrawable(
      @DrawableRes normalDrawable: Int,
      @DrawableRes checkedDrawable: Int
  ): DrawableIndicator {
    mNormalBitmap = BitmapFactory.decodeResource(resources, normalDrawable)
    mCheckedBitmap = BitmapFactory.decodeResource(resources, checkedDrawable)
    if (mNormalBitmap == null) {
      mNormalBitmap = getBitmapFromVectorDrawable(context, normalDrawable)
      normalCanResize = false
    }
    if (mCheckedBitmap == null) {
      mCheckedBitmap = getBitmapFromVectorDrawable(context, checkedDrawable)
      checkCanResize = false
    }
    initIconSize()
    postInvalidate()
    return this
  }

  fun setIndicatorSize(
      normalWidth: Int,
      normalHeight: Int,
      checkedWidth: Int,
      checkedHeight: Int
  ): DrawableIndicator {
    mIndicatorSize = IndicatorSize(normalWidth, normalHeight, checkedWidth, checkedHeight)
    initIconSize()
    postInvalidate()
    return this
  }

  fun setIndicatorGap(padding: Int): DrawableIndicator {
    if (padding >= 0) {
      mIndicatorPadding = padding
      postInvalidate()
    }
    return this
  }

  internal class IndicatorSize(
      var normalWidth: Int,
      var normalHeight: Int,
      var checkedWidth: Int,
      var checkedHeight: Int
  )

  private fun getBitmapFromVectorDrawable(
      context: Context,
      drawableId: Int
  ): Bitmap? {
    ContextCompat.getDrawable(context, drawableId)?.apply {
      val bitmap = createBitmap(intrinsicWidth, intrinsicHeight)
      val canvas = Canvas(bitmap)
      setBounds(0, 0, canvas.width, canvas.height)
      draw(canvas)
      return bitmap
    }
    return null
  }
}