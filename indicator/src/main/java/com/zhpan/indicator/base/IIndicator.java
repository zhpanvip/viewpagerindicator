package com.zhpan.indicator.base;

import androidx.viewpager.widget.ViewPager;

import com.zhpan.indicator.model.IndicatorOptions;


/**
 * <pre>
 *   Created by zhangpan on 2019-09-02.
 *   Description:
 * </pre>
 */
public interface IIndicator extends ViewPager.OnPageChangeListener{

    void setPageSize(int pageSize);

    void setIndicatorOptions(IndicatorOptions options);

}
