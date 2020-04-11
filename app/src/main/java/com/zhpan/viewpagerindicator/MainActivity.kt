package com.zhpan.viewpagerindicator

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.zhpan.indicator.DrawableIndicator
import com.zhpan.indicator.IndicatorView
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

open class MainActivity : BaseDataActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = findViewById<ViewPager>(R.id.banner_view)
        val indicatorView = findViewById<DrawableIndicator>(R.id.indicator_view)
        viewPager.adapter = ViewPagerAdapter(getData(4))
        val dp10 = resources.getDimensionPixelOffset(R.dimen.dp_10)
        indicatorView
                .setIndicatorGap(resources.getDimensionPixelOffset(R.dimen.dp_2_5))
                .setIndicatorDrawable(R.drawable.heart_empty, R.drawable.heart_red)
                .setIndicatorSize(dp10, dp10, dp10, dp10)
                .setupWithViewPager(viewPager)

        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager2)
        val indicatorView2 = findViewById<IndicatorView>(R.id.indicator_view2)
        viewPager2.adapter = ViewPager2Adapter(getData(4))
        indicatorView2
                .setSliderColor(getResColor(R.color.red_normal_color), getResColor(R.color.red_checked_color))
                .setSliderWidth(resources.getDimension(R.dimen.dp_17))
                .setSliderHeight(resources.getDimension(R.dimen.dp_5))
                .setSlideMode(IndicatorSlideMode.WORM)
                .setIndicatorStyle(IndicatorStyle.CIRCLE)
                .setupWithViewPager(viewPager2)
    }

}
