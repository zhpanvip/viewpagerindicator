package com.zhpan.viewpagerindicator

import android.graphics.Color
import android.os.Bundle
import android.widget.RadioGroup
import com.zhpan.indicator.annotation.AIndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import com.zhpan.indicator.utils.IndicatorUtils
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : BaseDataActivity() {
    @AIndicatorSlideMode
    private var mSlideMode = IndicatorSlideMode.SMOOTH
    private var mCheckId = R.id.rb_circle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dp10 = resources.getDimensionPixelOffset(R.dimen.dp_10)
        view_pager2.adapter = ViewPager2Adapter(getData(4))

        figureIndicator.setRadius(IndicatorUtils.dp2px(18f))
        figureIndicator.setTextSize(IndicatorUtils.dp2px(13f))
                .setupWithViewPager(view_pager2)
        figureIndicator.setBackgroundColor(Color.parseColor("#aa118EEA"))

        drawableIndicator
                .setIndicatorGap(resources.getDimensionPixelOffset(R.dimen.dp_2_5))
                .setIndicatorDrawable(R.drawable.heart_empty, R.drawable.heart_red)
                .setIndicatorSize(dp10, dp10, dp10, dp10)
                .setupWithViewPager(view_pager2)
        indicatorView
                .setSliderColor(getResColor(R.color.red_normal_color), getResColor(R.color.red_checked_color))
                .setSliderWidth(resources.getDimension(R.dimen.dp_10))
                .setSliderHeight(resources.getDimension(R.dimen.dp_5))
                .setSlideMode(IndicatorSlideMode.WORM)
                .setIndicatorStyle(IndicatorStyle.CIRCLE)
                .setupWithViewPager(view_pager2)

        initRadioGroup()
    }

    private fun initRadioGroup() {
        radioGroupStyle.setOnCheckedChangeListener { _: RadioGroup?, checkedId: Int -> checkedChange(checkedId.also { mCheckId = it }) }
        rb_circle.performClick()
        radioGroupMode.setOnCheckedChangeListener { _: RadioGroup?, checkedId: Int ->
            when (checkedId) {
                R.id.rb_normal -> mSlideMode = IndicatorSlideMode.NORMAL
                R.id.rb_worm -> mSlideMode = IndicatorSlideMode.WORM
                R.id.rb_smooth -> mSlideMode = IndicatorSlideMode.SMOOTH
            }
            checkedChange(mCheckId)
        }
    }

    private fun checkedChange(checkedId: Int) {
        when (checkedId) {
            R.id.rb_circle -> setupCircleIndicator()
            R.id.rb_dash -> setupDashIndicator()
            R.id.rb_round_rect -> setupRoundRectIndicator()
            R.id.rb_tmall -> setupTMallIndicator()
        }
    }

    private fun setupRoundRectIndicator() {
        val checkedWidth = resources.getDimension(R.dimen.dp_10)
        val normalWidth = getNormalWidth()
        indicatorView.setSlideMode(IndicatorStyle.ROUND_RECT)
                .setSliderGap(IndicatorUtils.dp2px(4f).toFloat())
                .setSlideMode(mSlideMode)
                .setSliderHeight(resources.getDimensionPixelOffset(R.dimen.dp_4).toFloat())
                .setSliderColor(resources.getColor(R.color.red_normal_color), resources.getColor(R.color.red_checked_color))
                .setSliderWidth(normalWidth, checkedWidth)
        indicatorView.notifyDataChanged()
    }

    private fun setupDashIndicator() {
        val checkedWidth = resources.getDimension(R.dimen.dp_10)
        val normalWidth = getNormalWidth()
        indicatorView.setIndicatorStyle(IndicatorStyle.DASH)
                .setSliderHeight(resources.getDimensionPixelOffset(R.dimen.dp_3).toFloat())
                .setSlideMode(mSlideMode)
                .setSliderGap(resources.getDimension(R.dimen.dp_3))
                .setSliderWidth(normalWidth, checkedWidth)
                .setSliderColor(resources.getColor(R.color.red_normal_color), resources.getColor(R.color.red_checked_color))
        indicatorView.notifyDataChanged()
    }

    private fun setupCircleIndicator() {
        indicatorView.setIndicatorStyle(IndicatorStyle.CIRCLE)
                .setSlideMode(mSlideMode)
                .setSliderGap(resources.getDimension(R.dimen.dp_6))
                .setSliderHeight(resources.getDimension(R.dimen.dp_4))
                .setSliderColor(resources.getColor(R.color.red_normal_color), resources.getColor(R.color.red_checked_color))
                .setSliderWidth(resources.getDimension(R.dimen.dp_4) * 2)
        indicatorView.notifyDataChanged()
    }

    private fun setupTMallIndicator() {
        indicatorView
                .setIndicatorStyle(IndicatorStyle.DASH)
                .setSliderGap(0f)
                .setSlideMode(mSlideMode)
                .setSliderColor(resources.getColor(R.color.red_normal_color), resources.getColor(R.color.red_checked_color))
                .setSliderWidth(resources.getDimensionPixelOffset(R.dimen.dp_15).toFloat())
                .setSliderHeight(resources.getDimensionPixelOffset(R.dimen.dp_3).toFloat())
        indicatorView.notifyDataChanged()
    }

    private fun getNormalWidth(): Float {
        return if (mSlideMode == IndicatorSlideMode.SMOOTH || mSlideMode == IndicatorSlideMode.WORM) {
            resources.getDimension(R.dimen.dp_10)
        } else {
            resources.getDimension(R.dimen.dp_4)
        }
    }

}
