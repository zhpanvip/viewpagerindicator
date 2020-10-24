package com.zhpan.indicator.base

import android.content.Context
import android.util.AttributeSet
import android.view.View

import androidx.annotation.ColorInt
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

import com.zhpan.indicator.annotation.AIndicatorSlideMode
import com.zhpan.indicator.annotation.AIndicatorStyle
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.option.IndicatorOptions

/**
 * <pre>
 * Created by zhangpan on 2019-09-04.
 * Description:IndicatorView基类，处理了页面滑动。
 * </pre>
 */
open class BaseIndicatorView constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr), IIndicator {

    var mIndicatorOptions: IndicatorOptions

    private var mViewPager: ViewPager? = null
    private var mViewPager2: ViewPager2? = null

    private val mOnPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            this@BaseIndicatorView.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            this@BaseIndicatorView.onPageSelected(position)
        }

        override fun onPageScrollStateChanged(state: Int) {
            this@BaseIndicatorView.onPageScrollStateChanged(state)
        }
    }

    var pageSize: Int
        get() = mIndicatorOptions.pageSize
        private set(pageSize) {
            mIndicatorOptions.pageSize = pageSize
        }

    val normalColor: Int
        get() = mIndicatorOptions.normalSliderColor

    val checkedColor: Int
        get() = mIndicatorOptions.checkedSliderColor

    val indicatorGap: Float
        get() = mIndicatorOptions.sliderGap

    var slideProgress: Float
        get() = mIndicatorOptions.slideProgress
        private set(slideProgress) {
            mIndicatorOptions.slideProgress = slideProgress
        }

    var currentPosition: Int
        get() = mIndicatorOptions.currentPosition
        private set(currentPosition) {
            mIndicatorOptions.currentPosition = currentPosition
        }

    val slideMode: Int
        get() = mIndicatorOptions.slideMode

    val normalSliderWidth: Float
        get() = mIndicatorOptions.normalSliderWidth

    val checkedSliderWidth: Float
        get() = mIndicatorOptions.checkedSliderWidth

    init {
        mIndicatorOptions = IndicatorOptions()
    }

    override fun onPageSelected(position: Int) {
        if (slideMode == IndicatorSlideMode.NORMAL) {
            currentPosition = position
            slideProgress = 0f
            invalidate()
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (slideMode != IndicatorSlideMode.NORMAL && pageSize > 1) {
            scrollSlider(position, positionOffset)
            invalidate()
        }
    }

    private fun scrollSlider(position: Int, positionOffset: Float) {
        if (mIndicatorOptions.slideMode == IndicatorSlideMode.SCALE
                || mIndicatorOptions.slideMode == IndicatorSlideMode.COLOR) {
            currentPosition = position
            slideProgress = positionOffset
        } else {
            if (position % pageSize == pageSize - 1) { //   最后一个页面与第一个页面
                if (positionOffset < 0.5) {
                    currentPosition = position
                    slideProgress = 0f
                } else {
                    currentPosition = 0
                    slideProgress = 0f
                }
            } else {    //  中间页面
                currentPosition = position
                slideProgress = positionOffset
            }
        }

    }

    override fun notifyDataChanged() {
        setupViewPager()
        requestLayout()
        invalidate()
    }

    private fun setupViewPager() {
        mViewPager?.let {
            mViewPager?.removeOnPageChangeListener(this)
            mViewPager?.addOnPageChangeListener(this)
            mViewPager?.adapter?.let {
                pageSize = mViewPager!!.adapter!!.count
            }
        }

        mViewPager2?.let {
            mViewPager2?.unregisterOnPageChangeCallback(mOnPageChangeCallback)
            mViewPager2?.registerOnPageChangeCallback(mOnPageChangeCallback)
            mViewPager2?.adapter?.let {
                pageSize = mViewPager2?.adapter?.itemCount!!
            }
        }
    }

    fun setSliderColor(@ColorInt normalColor: Int, @ColorInt selectedColor: Int): BaseIndicatorView {
        mIndicatorOptions.setSliderColor(normalColor, selectedColor)
        return this
    }

    fun setSliderWidth(sliderWidth: Float): BaseIndicatorView {
        mIndicatorOptions.setSliderWidth(sliderWidth)
        return this
    }

    fun setSliderWidth(normalSliderWidth: Float, selectedSliderWidth: Float): BaseIndicatorView {
        mIndicatorOptions.setSliderWidth(normalSliderWidth, selectedSliderWidth)
        return this
    }

    fun setSliderGap(sliderGap: Float): BaseIndicatorView {
        mIndicatorOptions.sliderGap = sliderGap
        return this
    }

    fun setSlideMode(@AIndicatorSlideMode slideMode: Int): BaseIndicatorView {
        mIndicatorOptions.slideMode = slideMode
        return this
    }

    fun setIndicatorStyle(@AIndicatorStyle indicatorStyle: Int): BaseIndicatorView {
        mIndicatorOptions.indicatorStyle = indicatorStyle
        return this
    }

    fun setSliderHeight(sliderHeight: Float): BaseIndicatorView {
        mIndicatorOptions.sliderHeight = sliderHeight
        return this
    }

    fun setupWithViewPager(viewPager: ViewPager) {
        mViewPager = viewPager
        notifyDataChanged()
    }

    fun setupWithViewPager(viewPager2: ViewPager2) {
        mViewPager2 = viewPager2
        notifyDataChanged()
    }

    fun getIndicatorOptions(): IndicatorOptions? {
        return mIndicatorOptions
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun setIndicatorOptions(options: IndicatorOptions) {
        mIndicatorOptions = options
    }
}
