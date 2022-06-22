# ViewPagerIndicator
[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
![MinSdk](https://img.shields.io/badge/API-19%2B-brightgreen)
[![latestVersion](https://jitpack.io/v/zhpanvip/viewpagerindicator.svg)](https://jitpack.io/#zhpanvip/viewpagerindicator)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ViewPagerIndicator-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/8096)

![公众号:玩转安卓Dev](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/group/wechat_dyh.png)

## English | [中文](https://github.com/zhpanvip/viewpagerindicator/blob/master/README_CN.md)

## [Use case of ViewPagerIndicator](https://github.com/zhpanvip/BannerViewPager)

## Preview

 ### [Click here or scan the QR code to download demo apk](https://github.com/zhpanvip/viewpagerindicator/raw/master/app/release/app-release.apk)

![QRCode](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/qrcode.png)

Various Indicator Styles and various Indicator Slide mode was supported now.It's also support drawable indicator and custom indicator.

### IndicatorView

| Attrs | CIRCLE | DASH | ROUND_RECT |
|--|--|--|--|
| NORMAL| ![CIRCLE_NORMAL](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/slide_circle_normal.gif) | ![DASH_NORMAL](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/style_dash_normal.gif) | ![ROUND_RECT_NORMAL](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/style_round_rect_normal.gif) |
| SMOOTH| ![CIRCLE_SMOOTH](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/slide_circle_smooth.gif) | ![DASH_SMOOTH](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/style_dash_smooth.gif) | ![ROUND_RECT_SMOOTH](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/style_round_rect_smooth.gif) |
| WORM| ![CIRCLE_WORM](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/slide_circle_worm.gif) | ![DASH_WORM](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/style_dash_worm.gif) | ![ROUND_WORM](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/style_round_rect_worm.gif) |
| COLOR| ![CIRCLE_COLOR](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/slide_circle_color.gif) | ![DASH_COLOR](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/style_dash_color.gif) | ![ROUND_COLOR](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/style_round_rect_color.gif) |
| SCALE| ![CIRCLE_SCALE](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/slide_circle_scale.gif) | ![DASH_SCALE](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/style_dash_scale.gif) | ![ROUND_SCALE](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/style_round_rect_scale.gif) |

## API

| Method | Description | Default |
|--|--|--|
| setOrientation(int) | set indicator orientation,enum(INDICATOR_HORIZONTAL/INDICATOR_VERTICAL/INDICATOR_RTL) | Default value is INDICATOR_HORIZONTAL |
| setIndicatorStyle(Int) | set indicator style | enum(CIRCLE；DASH；ROUND_RECT) default CIRCLE  |
| setSliderColor(normalColor: Int,selectedColor: Int)| set indicator slider color |normalColor：color of indicator dot not selected, default value  "#8C6C6D72"， checkedColor：color of indicator selected default value is "#8C18171C" |
| setSlideMode(slideMode: Int)  | set indicator slide mode | enum（NORMAL;SMOOTH;WORM;COLOR;SCALE），default value NORMAL  |
| setSliderWidth(indicatorWidth:Int) | set indicator slider width，if it's Circle indicator the parameter is diameter of circle | default value is 8dp|
| setSliderWidth(normalWidth Int , checkWidth Int) | set indicator slider width，if is circle style，the width is diameter of circle | default is 8dp |
| setIndicatorHeight(indicatorHeight Int) | set indicator hight，it's only used when the indicator style is DASH or ROUND_RECT | default value is normalIndicatorWidth/2 |
| setSliderGap(indicatorMargin Int ) | set the gap of indicator slider| default value is indicator slider width（or the diameter of circle）|
| setupWithViewPager(ViewPager) | To link IndicatorView with ViewPager together. |  |
| setupWithViewPager(ViewPager2) | To link IndicatorView with ViewPager2 together. |  |


## XML Attrs

| Attributes | format | description |
|--|--|--|
| vpi_slider_checked_color | color | set slider checked color,default value is "#6C6D72" |
| vpi_slider_normal_color | color | set slider normal color,default value is "#8C18171C" |
| vpi_slider_radius | dimension | set slider radius or width. if it's circle style the value is radius of circle,if the indicator style is DASH or ROUND_RECT the value is width/2 |
| vpi_orientation | enum | set orientation(horizontal/vertical/rtl) |
| vpi_slide_mode | enum | set indicator slide mode（normal/smooth/worm/scale/color） |
| vpi_style | enum | set indicator slider style (circle/dash/round_rect) |


## Usage

### Gradle dependency
   
Please add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
```
Then add the dependency in your app build.gradle

```groovy
implementation 'com.github.zhpanvip:viewpagerindicator:latestVersion'

```

latestVersion:[![latestVersion](https://jitpack.io/v/zhpanvip/viewpagerindicator.svg)](https://jitpack.io/#zhpanvip/viewpagerindicator)

### 1.IndicatorView

Three indicator styles and five slide mode supported with IndicatorView as so far.

#### (1).Add IndicatorView in layout.xml

```xml
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

#### (2).Use IndicatorView with ViewPager/ViewPager2:

```kotlin
         indicatorView.apply {
                             setSliderColor(normalColor, checkedColor)
                             setSliderWidth(resources.getDimension(R.dimen.dp_10))
                             setSliderHeight(resources.getDimension(R.dimen.dp_5))
                             setSlideMode(IndicatorSlideMode.WORM)
                             setIndicatorStyle(IndicatorStyle.CIRCLE)
                             setPageSize(view_pager2!!.adapter!!.itemCount)
                             notifyDataChanged()
                         }
         view_pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
             override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                 super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                 indicatorView.onPageScrolled(position, positionOffset, positionOffsetPixels)
             }

             override fun onPageSelected(position: Int) {
                 super.onPageSelected(position)
                 indicatorView.onPageSelected(position)
             }
         })
```

Or you can do like the following code:

```kotlin
        val indicatorView = findViewById<IndicatorView>(R.id.indicator_view2)
        indicatorView.apply {
                    setSliderColor(normalColor, checkedColor)
                    setSliderWidth(resources.getDimension(R.dimen.dp_10))
                    setSliderHeight(resources.getDimension(R.dimen.dp_5))
                    setSlideMode(IndicatorSlideMode.WORM)
                    setIndicatorStyle(IndicatorStyle.CIRCLE)
                    setupWithViewPager(view_pager2)
                }

```

### 2.Custom IndicatorView 


#### DrawableIndicator

| Bitmap Drawable| Vector Drawable|
|--|--|
| ![NORMAL](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/bitmap_drawable_indicator.gif) | ![NORMAL](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/vector_drawable_indicator.gif) |

You can set bitmap drawable indicator and vector drawable indicator by DrawableIndicator,also you can resize the drawable easily.

#####  Add IndicatorView in layout.xml
```xml
    <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="@dimen/dp_200"
            tools:context=".MainActivity">

            <androidx.viewpager.widget.ViewPager
                android:id="@+id/banner_view"
                android:layout_width="match_parent"
                android:layout_height="match_parent" />

            <com.zhpan.viewpagerindicator.DrawableIndicator
                android:id="@+id/indicator_view"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentBottom="true"
                android:layout_centerHorizontal="true"
                android:layout_margin="@dimen/dp_10" />

    </RelativeLayout>
```

#####  Use DrawableIndicator with ViewPager/ViewPager2:

```kotlin
        val drawableIndicator = findViewById<DrawableIndicator>(R.id.indicator_view)
        val dp20 = resources.getDimensionPixelOffset(R.dimen.dp_20)
        drawableIndicator.apply {
                            setIndicatorGap(resources.getDimensionPixelOffset(R.dimen.dp_2_5))
                            setIndicatorDrawable(R.drawable.heart_empty, R.drawable.heart_red)
                            setIndicatorSize(dp20, dp20, dp20, dp20)
                            setupWithViewPager(view_pager2)
                        }
```

#### FigureIndicatorView

The example will implement an custom IndicatorView as the follow gif.

| Custom IndicatorView Style|
|--|
| ![NORMAL](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/indicator/style_custum.gif) |

#### (1)Custom View and extends BaseIndicatorView

```java
public class FigureIndicatorView extends BaseIndicatorView {

    private int radius = DpUtils.dp2px(20);

    private int backgroundColor = Color.parseColor("#88FF5252");

    private int textColor = Color.WHITE;

    private int textSize=DpUtils.dp2px(13);

    public FigureIndicatorView(Context context) {
        this(context, null);
    }

    public FigureIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FigureIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(2 * radius, 2 * radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(backgroundColor);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        String text = currentPosition + 1 + "/" + pageSize;
        int textWidth = (int) mPaint.measureText(text);
        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetricsInt.bottom + fontMetricsInt.top) / 2 - fontMetricsInt.top;
        canvas.drawText(text, (getWidth() - textWidth) / 2, baseline, mPaint);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
```
#### (2)Use custom indicator with ViewPager/ViewPager2

```kotlin
    val indicatorView = findViewById<FigureIndicatorView>(R.id.indicator_view)
    indicatorView.setBackgroundColor(Color.parseColor("#aa118EEA"))
    indicatorView.setTextSize(IndicatorUtils.dp2px(13f))
    indicatorView.setupWithViewPager(viewPager)
```

## Contact
 **If you have any question regard to ViewPagerIndicator, please scan the QR code and join the QQ group to communicate.**

![QQ交流群60902509](https://cdn.jsdelivr.net/gh/zhpanvip/images/project/group/qq_group.png)



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






