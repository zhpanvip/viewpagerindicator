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

/**
 * <pre>
 * Created by zhpan on 2019/11/23.
 * Description:
</pre> *
 */
interface IDrawer {

  fun onLayout(
      changed: Boolean,
      left: Int,
      top: Int,
      right: Int,
      bottom: Int
  )

  fun onMeasure(
      widthMeasureSpec: Int,
      heightMeasureSpec: Int
  ): BaseDrawer.MeasureResult

  fun onDraw(canvas: Canvas)
}
