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

import android.graphics.Canvas
import android.view.View
import com.zhpan.indicator.enums.IndicatorOrientation

import com.zhpan.indicator.option.IndicatorOptions

/**
 * <pre>
 * Created by zhpan on 2019/11/23.
 * Description: Indicator Drawer Proxy.
</pre> *
 */
class DrawerProxy(private val indicatorOptions: IndicatorOptions, private val view: View) :
  IDrawer {

  private lateinit var mIDrawer: IDrawer

  private var mCurrentPosition: Int = 0

  init {
    init(indicatorOptions)
  }

  private fun init(indicatorOptions: IndicatorOptions) {
    mIDrawer = DrawerFactory.createDrawer(indicatorOptions, view)
    mCurrentPosition = indicatorOptions.currentPosition
  }

  override fun onLayout(
    changed: Boolean,
    left: Int,
    top: Int,
    right: Int,
    bottom: Int
  ) {
  }

  override fun onMeasure(
    widthMeasureSpec: Int,
    heightMeasureSpec: Int
  ): BaseDrawer.MeasureResult {
    return mIDrawer.onMeasure(widthMeasureSpec, heightMeasureSpec)
  }

  override fun onDraw(canvas: Canvas) {
    mIDrawer.onDraw(canvas)
  }

  fun onDraw(canvas: Canvas, view: View) {
    rotateCanvasIfNeed(canvas, view)
    onDraw(canvas)
  }

  private fun rotateCanvasIfNeed(canvas: Canvas, view: View) {
    if (indicatorOptions.orientation == IndicatorOrientation.INDICATOR_VERTICAL) {
      canvas.rotate(90f, view.width / 2f, view.width / 2f)
    } else if (indicatorOptions.orientation == IndicatorOrientation.INDICATOR_RTL) {
      canvas.rotate(180f, view.width / 2f, view.height / 2f)
    }
  }

  fun setIndicatorOptions(indicatorOptions: IndicatorOptions) {
    init(indicatorOptions)
  }

  /**
   * 开始指示器动画
   */
   fun startAnimation(toPosition: Int) {
    val fromPosition = mCurrentPosition
    mCurrentPosition = toPosition
    mIDrawer.startAnimation(fromPosition, toPosition)
  }
}
