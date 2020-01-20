package com.zhpan.indicator.drawer;

import android.graphics.Canvas;

import com.zhpan.indicator.option.IndicatorOptions;

/**
 * <pre>
 *   Created by zhpan on 2019/11/23.
 *   Description: Dash Indicator Drawer.
 * </pre>
 */
public class DashDrawer extends RectDrawer {

    DashDrawer(IndicatorOptions indicatorOptions) {
        super(indicatorOptions);
    }

    @Override
    protected void drawDash(Canvas canvas) {
        canvas.drawRect(mRectF, mPaint);
    }
}
