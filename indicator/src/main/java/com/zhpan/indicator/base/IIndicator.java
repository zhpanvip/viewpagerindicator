package com.zhpan.indicator.base;

import androidx.viewpager.widget.ViewPager;

import com.zhpan.indicator.option.IndicatorOptions;


/**
 * <pre>
 *   Created by zhangpan on 2019-09-02.
 *   Description:
 * </pre>
 */
public interface IIndicator extends ViewPager.OnPageChangeListener{

    void notifyDataChanged();

    void setIndicatorOptions(IndicatorOptions options);

}
