package com.zhpan.viewpagerindicator

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.zhpan.indicator.DrawableIndicator
import com.zhpan.indicator.IndicatorView
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

open class MainActivity : BaseDataActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = findViewById<ViewPager>(R.id.banner_view)
        val indicatorView = findViewById<IndicatorView>(R.id.indicator_view)
        viewPager.adapter = ViewPagerAdapter(getData(4))
        indicatorView
                .setSliderColor(getResColor(R.color.red_normal_color), getResColor(R.color.red_checked_color))
                .setSliderWidth(resources.getDimension(R.dimen.dp_17))
                .setSliderHeight(resources.getDimension(R.dimen.dp_5))
                .setSlideMode(IndicatorSlideMode.WORM)
                .setIndicatorStyle(IndicatorStyle.CIRCLE)
                .setupWithViewPager(viewPager)

//        viewPager.adapter = ViewPagerAdapter(getData(3))
//        indicatorView.notifyDataChanged()
    }

}
