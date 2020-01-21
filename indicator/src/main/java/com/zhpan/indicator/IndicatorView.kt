package com.zhpan.indicator

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet

import com.zhpan.indicator.base.BaseIndicatorView
import com.zhpan.indicator.base.IIndicator
import com.zhpan.indicator.drawer.BaseDrawer
import com.zhpan.indicator.drawer.DrawerProxy
import com.zhpan.indicator.option.IndicatorOptions


/**
 * <pre>
 * Created by zhpan on 2019/11/23.
 * Description:The Indicator in BannerViewPager，this include three indicator styles,as below:
 * [com.zhpan.indicator.enums.IndicatorStyle.CIRCLE]
 * [com.zhpan.indicator.enums.IndicatorStyle.DASH]
 * [com.zhpan.indicator.enums.IndicatorStyle.ROUND_RECT]
</pre> *
 */
class IndicatorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : BaseIndicatorView(context, attrs, defStyleAttr), IIndicator {

    private var mDrawerProxy: DrawerProxy? = null

    init {
        mDrawerProxy = DrawerProxy(mIndicatorOptions!!)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mDrawerProxy?.onLayout(changed, left, top, right, bottom)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureResult = mDrawerProxy!!.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measureResult.measureWidth, measureResult.measureHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mDrawerProxy?.onDraw(canvas)
    }

    override fun setIndicatorOptions(indicatorOptions: IndicatorOptions) {
        super.setIndicatorOptions(indicatorOptions)
        mDrawerProxy?.setIndicatorOptions(indicatorOptions)
    }

    override fun notifyDataChanged() {
        mDrawerProxy = DrawerProxy(mIndicatorOptions!!)
        super.notifyDataChanged()
    }
}
