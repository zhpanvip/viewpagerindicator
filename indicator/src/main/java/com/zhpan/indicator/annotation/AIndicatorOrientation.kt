package com.zhpan.indicator.annotation

import androidx.annotation.IntDef
import com.zhpan.indicator.enums.IndicatorOrientation
import com.zhpan.indicator.enums.IndicatorSlideMode

/**
 *
 * @author zhangpan
 * @date 2021/1/21
 */
@IntDef(IndicatorOrientation.INDICATOR_HORIZONTAL, IndicatorOrientation.INDICATOR_VERTICAL, IndicatorOrientation.INDICATOR_RTL)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
annotation class AIndicatorOrientation()
