/*
Copyright (C) 2020 zhpanvip, ViewPagerIndicator Open Source Project

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

package com.zhpan.indicator.annotation

import androidx.annotation.IntDef
import com.zhpan.indicator.enums.IndicatorOrientation
import com.zhpan.indicator.enums.IndicatorSlideMode

/**
 *
 * @author zhangpan
 * @date 2021/1/21
 */
@IntDef(
  IndicatorOrientation.INDICATOR_HORIZONTAL, IndicatorOrientation.INDICATOR_VERTICAL,
  IndicatorOrientation.INDICATOR_RTL
)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
annotation class AIndicatorOrientation()
