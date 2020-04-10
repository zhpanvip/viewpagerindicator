# ViewPagerIndicator

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
![MinSdk](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)
[![latestVersion](https://jitpack.io/v/zhpanvip/viewpagerindicator.svg)](https://jitpack.io/#zhpanvip/viewpagerindicator)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ViewPagerIndicator-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/8096)

## Preview

IndicatorView supports three Indicator Styles and three Indicator Slide mode now.


| Attrs | CIRCLE | DASH | ROUND_RECT |
|--|--|--|--|
| NORMAL| ![CIRCLE_NORMAL](https://github.com/zhpanvip/Resource/blob/master/image/indicator/slide_circle_normal.gif) | ![DASH_NORMAL](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_dash_normal.gif) | ![ROUND_RECT_NORMAL](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_round_rect_normal.gif) |
| SMOOTH| ![CIRCLE_SMOOTH](https://github.com/zhpanvip/Resource/blob/master/image/indicator/slide_circle_smooth.gif) | ![DASH_SMOOTH](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_dash_smooth.gif) | ![ROUND_RECT_SMOOTH](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_round_rect_smooth.gif) |
| WORM| ![CIRCLE_WORM](https://github.com/zhpanvip/Resource/blob/master/image/indicator/slide_circle_worm.gif) | ![DASH_WORM](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_dash_worm.gif) | ![ROUND_WORM](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_round_rect_worm.gif) |

[More information click here.](https://github.com/zhpanvip/BannerViewPager)

## API

| Method | Description | Default |
|--|--|--|
| setIndicatorStyle(Int) | set indicator style | enum(CIRCLE, DASH、ROUND_RECT) default CIRCLE  |
| setSliderColor(normalColor: Int,selectedColor: Int)| set indicator slider color |normalColor：color of indicator dot not selected, default value  "#8C6C6D72"， checkedColor：color of indicator selected default value is "#8C18171C" |
| setSlideMode(slideMode: Int)  | set indicator slide mode | enum（NORMAL;SMOOTH;WORM），default value NORMAL  |
| setSliderWidth(indicatorWidth:Int) | set indicator slider width，if it's Circle indicator the parameter is diameter of circle | default value is 8dp|
| setSliderWidth(normalWidth Int , checkWidth Int) | set indicator slider width，if is circle style，the width is diameter of circle | default is 8dp |
| setIndicatorHeight(indicatorHeight Int) | set indicator hight，it's only used when the indicator style is DASH or ROUND_RECT | default value is normalIndicatorWidth/2 |
| setSliderGap(indicatorMargin Int ) | set the gap of indicator slider| default value is indicator slider width（or the diameter of circle）|
| setupWithViewPager(ViewPager) | To link a IndicatorView with a ViewPager together. |  |
| setupWithViewPager(ViewPager2) | To link a IndicatorView with a ViewPager2 together. |  |


## Usage

### 1.Gradle dependency
   
Please add it in your root build.gradle at the end of repositories:

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
```
Then add the dependency in your app build.gradle

```
implementation 'com.github.zhpanvip:viewpagerindicator:latestVersion'

```

latestVersion:[![latestVersion](https://jitpack.io/v/zhpanvip/viewpagerindicator.svg)](https://jitpack.io/#zhpanvip/viewpagerindicator)


### 2.Add IndicatorView in layout.xml

```
    <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="@dimen/dp_200"
            tools:context=".MainActivity">

            <androidx.viewpager.widget.ViewPager
                android:id="@+id/banner_view"
                android:layout_width="match_parent"
                android:layout_height="match_parent" />

            <com.zhpan.indicator.IndicatorView
                android:id="@+id/indicator_view"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentBottom="true"
                android:layout_centerHorizontal="true"
                android:layout_margin="@dimen/dp_10" />

    </RelativeLayout>
```



### 3.Use IndicatorView with ViewPager/ViewPager2:


```
        indicatorView
                    .setSliderColor(getResColor(R.color.red_normal_color), getResColor(R.color.red_checked_color))
                    .setSliderWidth(resources.getDimension(R.dimen.dp_17))
                    .setSliderHeight(resources.getDimension(R.dimen.dp_5))
                    .setSlideMode(IndicatorSlideMode.WORM)
                    .setIndicatorStyle(IndicatorStyle.CIRCLE)
                    .setupWithViewPager(viewPager)

```

## FAQ

If you have any question you can scan the QR code to join the QQ group to communicate.

 ![QQ交流群60902509](https://github.com/zhpanvip/BannerViewPager/blob/master/image/qq_group.png)


## <span id="Sponsor"> Sponsor </span>

**开源不易 随心赞赏**

| Alipay | WeChat |
|--|--|
| ![Alipay](https://github.com/zhpanvip/viewpagerindicator/blob/master/image/pay_alipay.jpg) |  ![WeChat](https://github.com/zhpanvip/viewpagerindicator/blob/master/image/pay_wechat.png) |


License
-------

    Copyright 2020 zhpanvip

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.






