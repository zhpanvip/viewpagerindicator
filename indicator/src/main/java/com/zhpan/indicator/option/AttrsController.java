/*
Copyright (C) 2020 zhpanvip,ViewPagerIndicator Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.zhpan.indicator.option;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zhpan.indicator.R;
import com.zhpan.indicator.utils.IndicatorUtils;

public class AttrsController {

  public static void initAttrs(@NonNull Context context, @Nullable AttributeSet attrs,
      IndicatorOptions indicatorOptions) {
    if (attrs != null) {
      TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);
      int indicatorSlideMode = typedArray.getInt(R.styleable.IndicatorView_vpi_slide_mode, 0);
      int indicatorStyle = typedArray.getInt(R.styleable.IndicatorView_vpi_style, 0);
      int checkedColor = typedArray.getColor(R.styleable.IndicatorView_vpi_slider_checked_color,
          Color.parseColor("#6C6D72"));
      int normalColor = typedArray.getColor(R.styleable.IndicatorView_vpi_slider_normal_color,
          Color.parseColor("#8C18171C"));
      int orientation = typedArray.getInt(R.styleable.IndicatorView_vpi_orientation, 0);
      float radius = typedArray.getDimension(R.styleable.IndicatorView_vpi_slider_radius,
          IndicatorUtils.dp2px(8));
      indicatorOptions.setCheckedColor(checkedColor);
      indicatorOptions.setNormalSliderColor(normalColor);
      indicatorOptions.setOrientation(orientation);
      indicatorOptions.setIndicatorStyle(indicatorStyle);
      indicatorOptions.setSlideMode(indicatorSlideMode);
      indicatorOptions.setSliderWidth(radius * 2, radius * 2);
      typedArray.recycle();
    }
  }
}
