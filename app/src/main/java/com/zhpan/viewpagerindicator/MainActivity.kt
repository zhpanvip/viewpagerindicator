package com.zhpan.viewpagerindicator

import android.graphics.Color
import android.os.Bundle
import android.widget.RadioGroup
import com.zhpan.indicator.annotation.AIndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorSlideMode.Companion.NORMAL
import com.zhpan.indicator.enums.IndicatorSlideMode.Companion.SCALE
import com.zhpan.indicator.enums.IndicatorStyle
import com.zhpan.indicator.utils.IndicatorUtils
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : BaseDataActivity() {
    @AIndicatorSlideMode
    private var mSlideMode = IndicatorSlideMode.SMOOTH
    private var mCheckId = R.id.rb_circle
    private var normalWidth: Float = 0f
    private var checkedWidth: Float = 0f
    @androidx.annotation.ColorInt
    private var normalColor: Int = 0
    @androidx.annotation.ColorInt
    private var checkedColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dp20 = resources.getDimensionPixelOffset(R.dimen.dp_20)
        view_pager2.adapter = ViewPager2Adapter(getData(4))

        figureIndicator.setRadius(IndicatorUtils.dp2px(15f))
        figureIndicator.setTextSize(IndicatorUtils.dp2px(12f))
                .setupWithViewPager(view_pager2)
        figureIndicator.setBackgroundColor(Color.parseColor("#aa118EEA"))
        normalColor = getResColor(R.color.red_normal_color)
        checkedColor = getResColor(R.color.red_checked_color)
        normalWidth = resources.getDimension(R.dimen.dp_20)
        checkedWidth = normalWidth
        val dp12 = resources.getDimensionPixelOffset(R.dimen.dp_12)
        drawableIndicator
                .setIndicatorGap(resources.getDimensionPixelOffset(R.dimen.dp_2_5))
                .setIndicatorDrawable(R.drawable.heart_empty, R.drawable.heart_red)
                .setIndicatorSize(dp20, dp20, dp20, dp20)
                .setupWithViewPager(view_pager2)
        vectorIndicator.setIndicatorGap(resources.getDimensionPixelOffset(R.dimen.dp_2_5))
                .setIndicatorDrawable(R.drawable.banner_indicator_nornal, R.drawable.banner_indicator_focus)
                .setIndicatorSize(dp12, dp12, resources.getDimensionPixelOffset(R.dimen.dp_26), dp12)
                .setupWithViewPager(view_pager2)

        indicatorView
                .setSliderColor(normalColor, checkedColor)
                .setSliderWidth(resources.getDimension(R.dimen.dp_10))
                .setSliderHeight(resources.getDimension(R.dimen.dp_5))
                .setSlideMode(IndicatorSlideMode.WORM)
                .setIndicatorStyle(IndicatorStyle.CIRCLE)
                .setupWithViewPager(view_pager2)

        initRadioGroup()
    }

    private fun initRadioGroup() {
        radioGroupStyle.setOnCheckedChangeListener { _, checkedId -> checkedChange(checkedId) }
        radioGroupMode.setOnCheckedChangeListener { _: RadioGroup?, checkedId: Int ->
            when (checkedId) {
                R.id.rb_normal -> {
                    normalWidth = resources.getDimension(R.dimen.dp_10)
                    checkedWidth = normalWidth;
                    mSlideMode = IndicatorSlideMode.NORMAL
                }
                R.id.rb_worm -> {
                    normalWidth = resources.getDimension(R.dimen.dp_10)
                    checkedWidth = normalWidth;
                    mSlideMode = IndicatorSlideMode.WORM
                }
                R.id.rb_smooth -> {
                    normalWidth = resources.getDimension(R.dimen.dp_10)
                    checkedWidth = normalWidth;
                    mSlideMode = IndicatorSlideMode.SMOOTH
                }
                R.id.rb_scale -> {
                    normalWidth = resources.getDimension(R.dimen.dp_10)
                    checkedWidth = normalWidth * 1.2.toFloat()
                    mSlideMode = SCALE
                }
                R.id.rb_color -> {
                    normalWidth = resources.getDimension(R.dimen.dp_10)
                    checkedWidth = normalWidth * 1.2.toInt()
                    mSlideMode = IndicatorSlideMode.COLOR
                }
            }
            checkedChange(mCheckId)
        }
        rb_circle.performClick()
    }

    private fun checkedChange(checkedId: Int) {
        mCheckId=checkedId
        when (checkedId) {
            R.id.rb_circle -> setupCircleIndicator()
            R.id.rb_dash -> setupDashIndicator()
            R.id.rb_round_rect -> setupRoundRectIndicator()
        }
    }

    private fun setupRoundRectIndicator() {
        if (mSlideMode == SCALE || mSlideMode == NORMAL) {
            normalWidth = resources.getDimension(R.dimen.dp_4)
            checkedWidth = resources.getDimension(R.dimen.dp_10)
        } else {
            normalWidth = checkedWidth
        }
        indicatorView.setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                .setSliderGap(IndicatorUtils.dp2px(4f).toFloat())
                .setSlideMode(mSlideMode)
                .setSliderHeight(resources.getDimensionPixelOffset(R.dimen.dp_4).toFloat())
                .setSliderColor(normalColor, checkedColor)
                .setSliderWidth(normalWidth, checkedWidth)
        indicatorView.notifyDataChanged()
    }

    private fun setupDashIndicator() {
        if (mSlideMode == SCALE || mSlideMode == NORMAL) {
            normalWidth = resources.getDimension(R.dimen.dp_4)
            checkedWidth = resources.getDimension(R.dimen.dp_10)
        } else {
            normalWidth = checkedWidth
        }
        indicatorView.setIndicatorStyle(IndicatorStyle.DASH)
                .setSliderHeight(resources.getDimensionPixelOffset(R.dimen.dp_3).toFloat())
                .setSlideMode(mSlideMode)
                .setSliderGap(resources.getDimension(R.dimen.dp_3))
                .setSliderWidth(normalWidth, checkedWidth)
                .setSliderColor(normalColor, checkedColor)
        indicatorView.notifyDataChanged()
    }

    private fun setupCircleIndicator() {
        if (mSlideMode == SCALE || mSlideMode == NORMAL) {
            normalWidth = resources.getDimension(R.dimen.dp_8)
            checkedWidth = normalWidth * 1.4.toFloat()
        } else {
            normalWidth = checkedWidth
        }
        indicatorView.setIndicatorStyle(IndicatorStyle.CIRCLE)
                .setSlideMode(mSlideMode)
                .setSliderGap(resources.getDimension(R.dimen.dp_6))
                .setSliderHeight(resources.getDimension(R.dimen.dp_4))
                .setSliderColor(normalColor, checkedColor)
                .setSliderWidth(normalWidth, checkedWidth)
        indicatorView.notifyDataChanged()
    }
}
