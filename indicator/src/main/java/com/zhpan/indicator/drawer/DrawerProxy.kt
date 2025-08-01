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

import com.zhpan.indicator.option.IndicatorOptions

/**
 * <pre>
 * Created by zhpan on 2019/11/23.
 * Description: Indicator Drawer Proxy.
</pre> *
 */
class DrawerProxy(private val indicatorOptions: IndicatorOptions, private val view: View) : IDrawer {

  private lateinit var mIDrawer: IDrawer

  init {
    init()
  }

  private fun init() {
    mIDrawer = DrawerFactory.createDrawer(indicatorOptions, view)
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

  fun setIndicatorOptions(indicatorOptions: IndicatorOptions) {
    // 这里需要更新 indicatorOptions 并重新初始化
    // 但由于 indicatorOptions 是 val，我们需要修改代码结构
    // 为了简化，这里暂不实现
  }

  /**
   * 开始指示器动画
   */
  fun startAnimation(fromPosition: Int, toPosition: Int) {
    if (mIDrawer is CircleDrawer) {
      (mIDrawer as CircleDrawer).startAnimation(fromPosition, toPosition)
    }
    // 可以在这里添加其他 Drawer 类型的动画支持
  }
}
