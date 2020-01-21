package com.zhpan.indicator.drawer

import android.graphics.Canvas

import com.zhpan.indicator.option.IndicatorOptions

/**
 * <pre>
 * Created by zhpan on 2019/11/23.
 * Description: Dash Indicator Drawer.
</pre> *
 */
class DashDrawer internal constructor(indicatorOptions: IndicatorOptions) : RectDrawer(indicatorOptions) {

    override fun drawDash(canvas: Canvas) {
        canvas.drawRect(mRectF, mPaint)
    }
}
