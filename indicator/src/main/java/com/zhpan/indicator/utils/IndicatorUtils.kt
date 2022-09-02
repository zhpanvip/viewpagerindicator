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

package com.zhpan.indicator.utils

import android.content.res.Resources

import com.zhpan.indicator.option.IndicatorOptions

/**
 * <pre>
 * Created by zhangpan on 2020-01-20.
 * Description:
</pre> *
 */
object IndicatorUtils {

  @JvmStatic
  fun dp2px(dpValue: Float): Int {
    return (0.5f + dpValue * Resources.getSystem().displayMetrics.density).toInt()
  }

  fun getCoordinateX(
      indicatorOptions: IndicatorOptions,
      maxDiameter: Float,
      index: Int
  ): Float {
    val normalIndicatorWidth = indicatorOptions.normalSliderWidth
    return maxDiameter / 2 + (normalIndicatorWidth + indicatorOptions.sliderGap) * index
  }

  fun getCoordinateY(maxDiameter: Float): Float {
    return maxDiameter / 2
  }
}
