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

package com.zhpan.indicator

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.zhpan.indicator.annotation.AIndicatorOrientation

import com.zhpan.indicator.base.BaseIndicatorView
import com.zhpan.indicator.drawer.DrawerProxy
import com.zhpan.indicator.enums.IndicatorOrientation
import com.zhpan.indicator.enums.IndicatorOrientation.Companion.INDICATOR_HORIZONTAL
import com.zhpan.indicator.option.AttrsController
import com.zhpan.indicator.option.IndicatorOptions

/**
 * <pre>
 * Created by zhpan on 2019/11/23.
 * Description:The Indicator in BannerViewPager，this include three indicator styles,as below:
 * [com.zhpan.indicator.enums.IndicatorStyle.CIRCLE]
 * [com.zhpan.indicator.enums.IndicatorStyle.DASH]
 * [com.zhpan.indicator.enums.IndicatorStyle.ROUND_RECT]
</pre> *
 */
class IndicatorView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : BaseIndicatorView(context, attrs, defStyleAttr) {

  private var mDrawerProxy: DrawerProxy

  init {
    AttrsController.initAttrs(context, attrs, mIndicatorOptions)
    mDrawerProxy = DrawerProxy(mIndicatorOptions)
  }

  override fun onLayout(
    changed: Boolean,
    left: Int,
    top: Int,
    right: Int,
    bottom: Int
  ) {
    super.onLayout(changed, left, top, right, bottom)
    mDrawerProxy.onLayout(changed, left, top, right, bottom)
  }

  override fun onMeasure(
    widthMeasureSpec: Int,
    heightMeasureSpec: Int
  ) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    val measureResult = mDrawerProxy.onMeasure(widthMeasureSpec, heightMeasureSpec)
    setMeasuredDimension(measureResult.measureWidth, measureResult.measureHeight)
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    rotateCanvas(canvas)
    mDrawerProxy.onDraw(canvas)
  }

  override fun setIndicatorOptions(options: IndicatorOptions) {
    super.setIndicatorOptions(options)
    mDrawerProxy.setIndicatorOptions(options)
  }

  override fun notifyDataChanged() {
    mDrawerProxy = DrawerProxy(mIndicatorOptions)
    super.notifyDataChanged()
  }

  private fun rotateCanvas(canvas: Canvas) {
    if (mIndicatorOptions.orientation == IndicatorOrientation.INDICATOR_VERTICAL) {
      canvas.rotate(90f, width / 2f, width / 2f)
    } else if (mIndicatorOptions.orientation == IndicatorOrientation.INDICATOR_RTL) {
      canvas.rotate(180f, width / 2f, height / 2f)
    }
  }

  fun setOrientation(@AIndicatorOrientation orientation: Int) {
    mIndicatorOptions.orientation = orientation;
  }
}
