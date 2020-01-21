package com.zhpan.indicator.annotation

import androidx.annotation.IntDef
import com.zhpan.indicator.enums.IndicatorSlideMode


/**
 * <pre>
 * Created by zhangpan on 2019-10-18.
 * Description:
</pre> *
 */
@IntDef(IndicatorSlideMode.NORMAL, IndicatorSlideMode.SMOOTH, IndicatorSlideMode.WORM)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
annotation class AIndicatorSlideMode
