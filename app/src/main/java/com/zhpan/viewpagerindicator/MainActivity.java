package com.zhpan.viewpagerindicator;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.zhpan.indicator.IndicatorView;
import com.zhpan.indicator.enums.IndicatorSlideMode;
import com.zhpan.indicator.enums.IndicatorStyle;
import com.zhpan.indicator.option.IndicatorOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.banner_view);
        final IndicatorView indicatorView = findViewById(R.id.indicator_view);
        indicatorView.setIndicatorOptions(getIndicatorOption());
        viewPager.setAdapter(new ViewPagerAdapter(getData()));
        indicatorView.setupWithViewPager(viewPager);
    }

    private IndicatorOptions getIndicatorOption() {
        return new IndicatorOptions.Builder()
                .setPageSize(getData().size())
                .setSliderColor(getResColor(R.color.red_normal_color), getResColor(R.color.red_checked_color))
                .setSliderWidth(getResources().getDimensionPixelOffset(R.dimen.dp_20), getResources().getDimensionPixelOffset(R.dimen.dp_20))
                .setSlideMode(IndicatorSlideMode.WORM)
                .setIndicatorStyle(IndicatorStyle.ROUND_RECT).build();
    }

    protected List<Integer> getData() {
        List<Integer> mDrawableList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int drawable = getResources().getIdentifier("color" + i, "color", getPackageName());
            mDrawableList.add(drawable);
        }
        return mDrawableList;
    }

    private int getResColor(@ColorRes int color) {
        return getResources().getColor(color);
    }
}
