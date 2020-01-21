package com.zhpan.indicator.drawer

import android.graphics.Canvas

/**
 * <pre>
 * Created by zhpan on 2019/11/23.
 * Description:
</pre> *
 */
interface IDrawer {

    fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int)

    fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int): BaseDrawer.MeasureResult

    fun onDraw(canvas: Canvas)
}
