package com.zhpan.indicator.base

import androidx.viewpager.widget.ViewPager

import com.zhpan.indicator.option.IndicatorOptions


/**
 * <pre>
 * Created by zhangpan on 2019-09-02.
 * Description:
</pre> *
 */
interface IIndicator : ViewPager.OnPageChangeListener {

    fun notifyDataChanged()

    fun setIndicatorOptions(options: IndicatorOptions)

}
