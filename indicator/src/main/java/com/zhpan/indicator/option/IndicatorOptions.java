package com.zhpan.indicator.option;

import android.graphics.Color;

import com.zhpan.indicator.annotation.AIndicatorSlideMode;
import com.zhpan.indicator.annotation.AIndicatorStyle;
import com.zhpan.indicator.enums.IndicatorSlideMode;
import com.zhpan.indicator.utils.IndicatorUtils;


/**
 * <pre>
 *   Created by zhpan on 2019/11/20.
 *   Description:Indicator的配置参数
 * </pre>
 */
public class IndicatorOptions {

    private IndicatorOptions(Builder builder) {
        this.slideMode = builder.slideMode;
        this.mIndicatorStyle = builder.mIndicatorStyle;
        this.pageSize = builder.pageSize;
        this.normalColor = builder.normalColor;
        this.checkedColor = builder.checkedColor;
        this.sliderSpace = builder.sliderSpace;
        this.sliderHeight = builder.sliderHeight;
        this.normalSliderWidth = builder.normalSliderWidth;
        this.checkedSliderWidth = builder.checkedSliderWidth;
    }

    private @AIndicatorStyle
    int mIndicatorStyle;

    /**
     * Indicator滑动模式，目前仅支持两种
     *
     * @see IndicatorSlideMode#NORMAL
     * @see IndicatorSlideMode#SMOOTH
     */
    private @AIndicatorSlideMode
    int slideMode;

    /**
     * 页面size
     */
    private int pageSize;
    /**
     * 未选中时Indicator颜色
     */
    private int normalColor;
    /**
     * 选中时Indicator颜色
     */
    private int checkedColor;
    /**
     * Indicator间距
     */
    private float sliderSpace;

    private float sliderHeight;

    private float normalSliderWidth;

    private float checkedSliderWidth;

    /**
     * 指示器当前位置
     */
    private int currentPosition;

    /**
     * 从一个点滑动到另一个点的进度
     */
    private float slideProgress;

    public int getPageSize() {
        return pageSize;
    }

    public int getNormalSliderColor() {
        return normalColor;
    }

    public int getCheckedSliderColor() {
        return checkedColor;
    }

    public void setCheckedColor(int checkedColor) {
        this.checkedColor = checkedColor;
    }

    public float getSliderSpace() {
        return sliderSpace;
    }

    public void setSliderSpace(float sliderSpace) {
        this.sliderSpace = sliderSpace;
    }

    public float getSlideProgress() {
        return slideProgress;
    }

    public void setSlideProgress(float slideProgress) {
        this.slideProgress = slideProgress;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getSlideMode() {
        return slideMode;
    }

    public void setSlideMode(int slideMode) {
        this.slideMode = slideMode;
    }

    public float getNormalSliderWidth() {
        return normalSliderWidth;
    }

    public float getCheckedSliderWidth() {
        return checkedSliderWidth;
    }


    public float getSliderHeight() {
        return sliderHeight > 0 ? sliderHeight : normalSliderWidth / 2;
    }

    public void setSliderHeight(float sliderHeight) {
        this.sliderHeight = sliderHeight;
    }

    public int getIndicatorStyle() {
        return mIndicatorStyle;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public static class Builder {

        public Builder() {
            normalSliderWidth = IndicatorUtils.dp2px(8);
            checkedSliderWidth = normalSliderWidth;
            sliderSpace = normalSliderWidth;
            normalColor = Color.parseColor("#8C18171C");
            checkedColor = Color.parseColor("#8C6C6D72");
            slideMode = IndicatorSlideMode.NORMAL;
        }

        @AIndicatorStyle
        int mIndicatorStyle;
        @AIndicatorSlideMode
        int slideMode;
        int pageSize;
        int normalColor;
        int checkedColor;
        float sliderSpace;
        float sliderHeight;
        float normalSliderWidth;
        float checkedSliderWidth;

        public Builder setIndicatorStyle(int indicatorStyle) {
            mIndicatorStyle = indicatorStyle;
            return this;
        }

        public Builder setSlideMode(int slideMode) {
            this.slideMode = slideMode;
            return this;
        }

        public Builder setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder setSliderSpace(float sliderSpace) {
            this.sliderSpace = sliderSpace;
            return this;
        }

        public Builder setSliderHeight(float sliderHeight) {
            this.sliderHeight = sliderHeight;
            return this;
        }

        public IndicatorOptions build() {
            return new IndicatorOptions(this);
        }

        public Builder setSliderWidth(float normalIndicatorWidth, float checkedIndicatorWidth) {
            this.normalSliderWidth = normalIndicatorWidth;
            this.checkedSliderWidth = checkedIndicatorWidth;
            return this;
        }

        public Builder setSliderWidth(float sliderWidth) {
            return setSliderWidth(sliderWidth, sliderWidth);
        }

        public Builder setSliderColor(int normalColor, int checkedColor) {
            this.normalColor = normalColor;
            this.checkedColor = checkedColor;
            return this;
        }
    }
}
