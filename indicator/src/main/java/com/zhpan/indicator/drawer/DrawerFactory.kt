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

import com.zhpan.indicator.enums.IndicatorStyle
import com.zhpan.indicator.option.IndicatorOptions

/**
 * <pre>
 * Created by zhpan on 2019/11/24.
 * Description: Indicator Drawer Factory.
</pre> *
 */
internal object DrawerFactory {
  fun createDrawer(indicatorOptions: IndicatorOptions): IDrawer {
    return when (indicatorOptions.indicatorStyle) {
        IndicatorStyle.DASH -> DashDrawer(indicatorOptions)
        IndicatorStyle.ROUND_RECT -> RoundRectDrawer(indicatorOptions)
      else -> CircleDrawer(indicatorOptions)
    }
  }
}
