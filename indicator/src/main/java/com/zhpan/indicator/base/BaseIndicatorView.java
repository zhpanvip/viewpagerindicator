package com.zhpan.indicator.base;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.zhpan.indicator.enums.IndicatorSlideMode;
import com.zhpan.indicator.option.IndicatorOptions;

/**
 * <pre>
 *   Created by zhangpan on 2019-09-04.
 *   Description:IndicatorView基类，处理了页面滑动。
 * </pre>
 */
public class BaseIndicatorView extends View implements IIndicator {

    private IndicatorOptions mIndicatorOptions;

    protected Paint mPaint;

    public BaseIndicatorView(Context context) {
        super(context);
    }

    public BaseIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mIndicatorOptions = new IndicatorOptions.Builder().build();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onPageSelected(int position) {
        if (getSlideMode() == IndicatorSlideMode.NORMAL) {
            setCurrentPosition(position);
            setSlideProgress(0);
            invalidate();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (getSlideMode() != IndicatorSlideMode.NORMAL && getPageSize() > 1) {
            scrollSlider(position, positionOffset);
            invalidate();
        }
    }

    private void scrollSlider(int position, float positionOffset) {
        if (position % getPageSize() == getPageSize() - 1) { //   最后一个页面与第一个页面
            if (positionOffset < 0.5) {
                setCurrentPosition(position);
                setSlideProgress(0);
            } else {
                setCurrentPosition(0);
                setSlideProgress(0);
            }
        } else {    //  中间页面
            setCurrentPosition(position);
            setSlideProgress(positionOffset);
        }
    }

    @Override
    public void setPageSize(int pageSize) {
        mIndicatorOptions.setPageSize(pageSize);
        requestLayout();
    }


    public int getPageSize() {
        return mIndicatorOptions.getPageSize();
    }

    public int getNormalColor() {
        return mIndicatorOptions.getNormalSliderColor();
    }

    public int getCheckedColor() {
        return mIndicatorOptions.getCheckedSliderColor();
    }

    public float getIndicatorGap() {
        return mIndicatorOptions.getSliderSpace();
    }

    public float getSlideProgress() {
        return mIndicatorOptions.getSlideProgress();
    }

    public int getCurrentPosition() {
        return mIndicatorOptions.getCurrentPosition();
    }

    public int getSlideMode() {
        return mIndicatorOptions.getSlideMode();
    }

    public float getNormalSliderWidth() {
        return mIndicatorOptions.getNormalSliderWidth();
    }

    public float getCheckedSliderWidth() {
        return mIndicatorOptions.getCheckedSliderWidth();
    }

    private void setSlideProgress(float slideProgress) {
        mIndicatorOptions.setSlideProgress(slideProgress);
    }

    protected void setCurrentPosition(int currentPosition) {
        mIndicatorOptions.setCurrentPosition(currentPosition);
    }

    public void setupWithViewPager(ViewPager viewPager) {
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(this);
        }
    }

    public void setupWithViewPager2(ViewPager2 viewPager2) {
        if (viewPager2 != null) {
            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    BaseIndicatorView.this.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }

                @Override
                public void onPageSelected(int position) {
                    BaseIndicatorView.this.onPageSelected(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    BaseIndicatorView.this.onPageScrollStateChanged(state);
                }
            });
        }
    }

    public IndicatorOptions getIndicatorOptions() {
        return mIndicatorOptions;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public void setIndicatorOptions(IndicatorOptions indicatorOptions) {
        mIndicatorOptions = indicatorOptions;
    }
}
