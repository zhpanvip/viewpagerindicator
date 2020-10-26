# ViewPagerIndicator
[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
![MinSdk](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)
[![latestVersion](https://jitpack.io/v/zhpanvip/viewpagerindicator.svg)](https://jitpack.io/#zhpanvip/viewpagerindicator)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ViewPagerIndicator-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/8096)

## English | [中文](https://github.com/zhpanvip/viewpagerindicator/blob/master/README_CN.md)

## [Use case of ViewPagerIndicator](https://github.com/zhpanvip/BannerViewPager)

## Preview

 ### [Click here or scan the QR code to download demo apk](https://github.com/zhpanvip/viewpagerindicator/raw/master/app/release/app-release.apk)

![QRCode](https://gitee.com/zhpanvip/images/raw/master/project/indicator/qrcode.png)

Various Indicator Styles and various Indicator Slide mode was supported now.It's also support drawable indicator and custom indicator.

### IndicatorView

| Attrs | CIRCLE | DASH | ROUND_RECT |
|--|--|--|--|
| NORMAL| ![CIRCLE_NORMAL](https://gitee.com/zhpanvip/images/raw/master/project/indicator/slide_circle_normal.gif) | ![DASH_NORMAL](https://gitee.com/zhpanvip/images/raw/master/project/indicator/style_dash_normal.gif) | ![ROUND_RECT_NORMAL](https://gitee.com/zhpanvip/images/raw/master/project/indicator/style_round_rect_normal.gif) |
| SMOOTH| ![CIRCLE_SMOOTH](https://gitee.com/zhpanvip/images/raw/master/project/indicator/slide_circle_smooth.gif) | ![DASH_SMOOTH](https://gitee.com/zhpanvip/images/raw/master/project/indicator/style_dash_smooth.gif) | ![ROUND_RECT_SMOOTH](https://gitee.com/zhpanvip/images/raw/master/project/indicator/style_round_rect_smooth.gif) |
| WORM| ![CIRCLE_WORM](https://gitee.com/zhpanvip/images/raw/master/project/indicator/slide_circle_worm.gif) | ![DASH_WORM](https://gitee.com/zhpanvip/images/raw/master/project/indicator/style_dash_worm.gif) | ![ROUND_WORM](https://gitee.com/zhpanvip/images/raw/master/project/indicator/style_round_rect_worm.gif) |
| COLOR| ![CIRCLE_COLOR](https://gitee.com/zhpanvip/images/raw/master/project/indicator/slide_circle_color.gif) | ![DASH_COLOR](https://gitee.com/zhpanvip/images/raw/master/project/indicator/style_dash_color.gif) | ![ROUND_COLOR](https://gitee.com/zhpanvip/images/raw/master/project/indicator/style_round_rect_color.gif) |
| SCALE| ![CIRCLE_SCALE](https://gitee.com/zhpanvip/images/raw/master/project/indicator/slide_circle_scale.gif) | ![DASH_SCALE](https://gitee.com/zhpanvip/images/raw/master/project/indicator/style_dash_scale.gif) | ![ROUND_SCALE](https://gitee.com/zhpanvip/images/raw/master/project/indicator/style_round_rect_scale.gif) |

### DrawableIndicator

| Bitmap Drawable| Vector Drawable|
|--|--|
| ![NORMAL](https://gitee.com/zhpanvip/images/raw/master/project/indicator/bitmap_drawable_indicator.gif) | ![NORMAL](https://gitee.com/zhpanvip/images/raw/master/project/indicator/vector_drawable_indicator.gif) |


## API

| Method | Description | Default |
|--|--|--|
| setIndicatorStyle(Int) | set indicator style | enum(CIRCLE；DASH；ROUND_RECT) default CIRCLE  |
| setSliderColor(normalColor: Int,selectedColor: Int)| set indicator slider color |normalColor：color of indicator dot not selected, default value  "#8C6C6D72"， checkedColor：color of indicator selected default value is "#8C18171C" |
| setSlideMode(slideMode: Int)  | set indicator slide mode | enum（NORMAL;SMOOTH;WORM;COLOR;SCALE），default value NORMAL  |
| setSliderWidth(indicatorWidth:Int) | set indicator slider width，if it's Circle indicator the parameter is diameter of circle | default value is 8dp|
| setSliderWidth(normalWidth Int , checkWidth Int) | set indicator slider width，if is circle style，the width is diameter of circle | default is 8dp |
| setIndicatorHeight(indicatorHeight Int) | set indicator hight，it's only used when the indicator style is DASH or ROUND_RECT | default value is normalIndicatorWidth/2 |
| setSliderGap(indicatorMargin Int ) | set the gap of indicator slider| default value is indicator slider width（or the diameter of circle）|
| setupWithViewPager(ViewPager) | To link IndicatorView with ViewPager together. |  |
| setupWithViewPager(ViewPager2) | To link IndicatorView with ViewPager2 together. |  |


## Usage

### Gradle dependency
   
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

### 1.IndicatorView

Three indicator styles and five slide mode supported with IndicatorView as so far.

#### (1).Add IndicatorView in layout.xml

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

#### (2).Use IndicatorView with ViewPager/ViewPager2:

```
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

Or you can do like this:

```
         indicatorView.apply {
                             setSliderColor(normalColor, checkedColor)
                             setSliderWidth(resources.getDimension(R.dimen.dp_10))
                             setSliderHeight(resources.getDimension(R.dimen.dp_5))
                             setSlideMode(IndicatorSlideMode.WORM)
                             setIndicatorStyle(IndicatorStyle.CIRCLE)
                             setPageSize(view_pager2!!.adapter!!.itemCount)
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

### 2.DrawableIndicator

You can set bitmap drawable indicator and vector drawable indicator by DrawableIndicator,also you can resize the drawable easily.

#### (1) Add IndicatorView in layout.xml
```
    <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="@dimen/dp_200"
            tools:context=".MainActivity">

            <androidx.viewpager.widget.ViewPager
                android:id="@+id/banner_view"
                android:layout_width="match_parent"
                android:layout_height="match_parent" />

            <com.zhpan.indicator.DrawableIndicator
                android:id="@+id/indicator_view"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentBottom="true"
                android:layout_centerHorizontal="true"
                android:layout_margin="@dimen/dp_10" />

    </RelativeLayout>
```

#### (2) Use DrawableIndicator with ViewPager/ViewPager2:

```
        val indicatorView = findViewById<DrawableIndicator>(R.id.indicator_view)
        val dp10 = resources.getDimensionPixelOffset(R.dimen.dp_10)
        indicatorView
              .setIndicatorGap(resources.getDimensionPixelOffset(R.dimen.dp_2_5))
              .setIndicatorDrawable(R.drawable.heart_empty, R.drawable.heart_red)
              .setIndicatorSize(dp10, dp10, dp10, dp10)
              .setupWithViewPager(viewPager)
```

### 3.Custom IndicatorView Supported

The example will implement an custom IndicatorView as the follow gif.

| Custom IndicatorView Style|
|--|
| ![NORMAL](https://github.com/zhpanvip/Resource/blob/master/image/banner/style_custum.gif) |

#### (1)Custom View and extends BaseIndicatorView

```
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

```
    val indicatorView = findViewById<FigureIndicatorView>(R.id.indicator_view)
            indicatorView.setBackgroundColor(Color.parseColor("#aa118EEA"))
            indicatorView.setTextSize(IndicatorUtils.dp2px(13f))
                    .setupWithViewPager(viewPager)
```

## FAQ

 **If you have any question regard to ViewPagerIndicator, please scan the QR code and join the QQ group to communicate.**

![QQ交流群60902509](https://github.com/zhpanvip/Resource/blob/master/image/group/qq_group.png)

| Alipay | WeChat |
|--|--|
| ![NORMAL](https://github.com/zhpanvip/Resource/blob/master/image/pay/pay_alipay.jpg) |  ![SMOOTH](https://github.com/zhpanvip/Resource/blob/master/image/pay/pay_wechat.png) |

##  More details

[《聊一聊ViewPagerIndicator重构的一些经验》](https://juejin.im/post/5dda0b6d518825731f569a8c)


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






