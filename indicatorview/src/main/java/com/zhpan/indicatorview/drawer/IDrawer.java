package com.zhpan.indicatorview.drawer;

import android.graphics.Canvas;

/**
 * <pre>
 *   Created by zhpan on 2019/11/23.
 *   Description:
 * </pre>
 */
public interface IDrawer {

    void onLayout(boolean changed, int left, int top, int right, int bottom);

    BaseDrawer.MeasureResult onMeasure(int widthMeasureSpec, int heightMeasureSpec);

    void onDraw(Canvas canvas);
}
