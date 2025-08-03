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

  private lateinit var mDrawerProxy: DrawerProxy

  init {
    AttrsController.initAttrs(context, attrs, mIndicatorOptions)
    // 延迟初始化，确保 this 可用
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    mDrawerProxy = DrawerProxy(mIndicatorOptions, this)
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
    mDrawerProxy.onDraw(canvas, this)
  }

  override fun setIndicatorOptions(options: IndicatorOptions) {
    super.setIndicatorOptions(options)
    mDrawerProxy.setIndicatorOptions(options)
  }

  override fun notifyDataChanged() {
    mDrawerProxy = DrawerProxy(mIndicatorOptions, this)
    super.notifyDataChanged()
  }

  fun setOrientation(@AIndicatorOrientation orientation: Int) {
    mIndicatorOptions.orientation = orientation
  }

  override fun animateAfterPageSelected(position: Int) {
    // 如果启用了延迟动画，则在此处触发动画
    mDrawerProxy.startAnimation(position)
  }
}
