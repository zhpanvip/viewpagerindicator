package com.zhpan.indicatorview.drawer;

import android.graphics.Canvas;

import com.zhpan.indicatorview.model.IndicatorOptions;

/**
 * <pre>
 *   Created by zhpan on 2019/11/26.
 *   Description:
 * </pre>
 */
public class RoundRectDrawer extends RectDrawer {

    RoundRectDrawer(IndicatorOptions indicatorOptions) {
        super(indicatorOptions);
    }

    @Override
    protected void drawRoundRect(Canvas canvas, float rx, float ry) {
        canvas.drawRoundRect(mRectF, rx, ry, mPaint);
    }
}
