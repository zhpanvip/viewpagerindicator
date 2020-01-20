package com.zhpan.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.zhpan.indicator.base.BaseIndicatorView;
import com.zhpan.indicator.base.IIndicator;
import com.zhpan.indicator.drawer.BaseDrawer;
import com.zhpan.indicator.drawer.DrawerProxy;
import com.zhpan.indicator.option.IndicatorOptions;


/**
 * <pre>
 *   Created by zhpan on 2019/11/23.
 *   Description:The Indicator in BannerViewPager，this include three indicator styles,as below:
 *  {@link com.zhpan.indicator.enums.IndicatorStyle#CIRCLE }
 *  {@link com.zhpan.indicator.enums.IndicatorStyle#DASH}
 *  {@link com.zhpan.indicator.enums.IndicatorStyle#ROUND_RECT}
 * </pre>
 */
public class IndicatorView extends BaseIndicatorView implements IIndicator {

    private DrawerProxy mDrawerProxy;

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDrawerProxy = new DrawerProxy(getIndicatorOptions());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mDrawerProxy.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        BaseDrawer.MeasureResult measureResult = mDrawerProxy.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureResult.getMeasureWidth(), measureResult.getMeasureHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDrawerProxy.onDraw(canvas);
    }

    @Override
    public void setIndicatorOptions(IndicatorOptions indicatorOptions) {
        super.setIndicatorOptions(indicatorOptions);
        mDrawerProxy.setIndicatorOptions(indicatorOptions);
    }

    @Override
    public void notifyDataChanged() {
        mDrawerProxy = new DrawerProxy(getIndicatorOptions());
        super.notifyDataChanged();
    }
}
