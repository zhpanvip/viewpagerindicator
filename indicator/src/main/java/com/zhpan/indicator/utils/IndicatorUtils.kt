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

    fun getCoordinateX(indicatorOptions: IndicatorOptions, maxDiameter: Float, index: Int): Float {
        val normalIndicatorWidth = indicatorOptions.normalSliderWidth
        return maxDiameter / 2 + (normalIndicatorWidth + indicatorOptions.sliderGap) * index
    }

    fun getCoordinateY(maxDiameter: Float): Float {
        return maxDiameter / 2
    }
}
