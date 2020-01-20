package com.zhpan.indicatorview.annotation;

import androidx.annotation.IntDef;

import com.zhpan.indicatorview.enums.IndicatorStyle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 *   Created by zhangpan on 2019-10-18.
 *   Description:
 * </pre>
 */
@IntDef({IndicatorStyle.CIRCLE, IndicatorStyle.DASH, IndicatorStyle.ROUND_RECT})
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PARAMETER)
public @interface AIndicatorStyle {
}
