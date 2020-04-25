# ViewPagerIndicator

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
![MinSdk](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)
[![latestVersion](https://jitpack.io/v/zhpanvip/viewpagerindicator.svg)](https://jitpack.io/#zhpanvip/viewpagerindicator)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ViewPagerIndicator-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/8096)

## [English](https://github.com/zhpanvip/viewpagerindicator) | 中文

## [ViewPagerIndicator使用案例](https://github.com/zhpanvip/BannerViewPager)

## 预览

 ### [点击链接或扫描二维码下载demo APK](https://github.com/zhpanvip/viewpagerindicator/raw/master/app/release/app-release.apk)

![QRCode](https://github.com/zhpanvip/Resource/blob/master/image/indicator/qrcode.png)

目前，该库已经可以支持多种指示器样式和多种滑动模式，同时，也支持Drawable指示器和自定义指示器

### IndicatorView

| 属性 | CIRCLE | DASH | ROUND_RECT |
|--|--|--|--|
| NORMAL| ![CIRCLE_NORMAL](https://github.com/zhpanvip/Resource/blob/master/image/indicator/slide_circle_normal.gif) | ![DASH_NORMAL](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_dash_normal.gif) | ![ROUND_RECT_NORMAL](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_round_rect_normal.gif) |
| SMOOTH| ![CIRCLE_SMOOTH](https://github.com/zhpanvip/Resource/blob/master/image/indicator/slide_circle_smooth.gif) | ![DASH_SMOOTH](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_dash_smooth.gif) | ![ROUND_RECT_SMOOTH](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_round_rect_smooth.gif) |
| WORM| ![CIRCLE_WORM](https://github.com/zhpanvip/Resource/blob/master/image/indicator/slide_circle_worm.gif) | ![DASH_WORM](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_dash_worm.gif) | ![ROUND_WORM](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_round_rect_worm.gif) |
| COLOR| ![CIRCLE_COLOR](https://github.com/zhpanvip/Resource/blob/master/image/indicator/slide_circle_color.gif) | ![DASH_COLOR](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_dash_color.gif) | ![ROUND_COLOR](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_round_rect_color.gif) |
| SCALE| ![CIRCLE_SCALE](https://github.com/zhpanvip/Resource/blob/master/image/indicator/slide_circle_scale.gif) | ![DASH_SCALE](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_dash_scale.gif) | ![ROUND_SCALE](https://github.com/zhpanvip/Resource/blob/master/image/indicator/style_round_rect_scale.gif) |

### DrawableIndicator

| Bitmap Drawable| Vector Drawable|
|--|--|
| ![NORMAL](https://github.com/zhpanvip/Resource/blob/master/image/indicator/bitmap_drawable_indicator.gif) | ![NORMAL](https://github.com/zhpanvip/Resource/blob/master/image/indicator/vector_drawable_indicator.gif) |


## API

| Method | Description | Default |
|--|--|--|
| setIndicatorStyle(Int) | 设置指示器样式 | 枚举类型(CIRCLE；DASH；ROUND_RECT) 默认值：CIRCLE  |
| setSliderColor(normalColor: Int,selectedColor: Int)| 设置指示器滑块颜色 |normalColor：未选中时的滑块颜色, 默认值："#8C6C6D72"， checkedColor：选中时滑块颜色，默认值："#8C18171C" |
| setSlideMode(slideMode: Int)  | 设置滑块滑动模式 | 枚举类型（NORMAL;SMOOTH;WORM;COLOR;SCALE），默认值：NORMAL  |
| setSliderWidth(indicatorWidth:Int) | 设置滑块宽度，如果是圆形指示器则为滑块直径 | 默认值：8dp|
| setSliderWidth(normalWidth Int , checkWidth Int) | 设置滑块宽度，如果是圆形指示器则为滑块直径 | 默认值 8dp |
| setIndicatorHeight(indicatorHeight Int) | 设置指示器高度，只有在DASH和ROUND_RECT样式下有效 | 默认值为：normalIndicatorWidth/2 |
| setSliderGap(indicatorMargin Int ) | 设置滑块之间的间距| 默认值为滑块宽度或者直径|
| setupWithViewPager(ViewPager) | indicator与ViewPager关联 |  |
| setupWithViewPager(ViewPager2) | indicator与ViewPager2关联 |  |


## 如何使用

### 添加依赖
   
在项目的gradle中添加如下依赖：

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
```
在模块的gradle中添加如下依赖：

```
implementation 'com.github.zhpanvip:viewpagerindicator:latestVersion'

```

latestVersion:[![latestVersion](https://jitpack.io/v/zhpanvip/viewpagerindicator.svg)](https://jitpack.io/#zhpanvip/viewpagerindicator)

### 1.IndicatorView

到目前为止IndicatorView已经可以支持三种indicator样式以及五种滑动样式，具体使用步骤如下：

#### (1).在layout.xml文件中添加如下代码：

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

#### (2) 为IndicatorView配置参数以及关联ViewPager/ViewPager2:

```
        val indicatorView = findViewById<IndicatorView>(R.id.indicator_view2)
        indicatorView
                    .setSliderColor(getResColor(R.color.red_normal_color), getResColor(R.color.red_checked_color))
                    .setSliderWidth(resources.getDimension(R.dimen.dp_17))
                    .setSliderHeight(resources.getDimension(R.dimen.dp_5))
                    .setSlideMode(IndicatorSlideMode.WORM)
                    .setIndicatorStyle(IndicatorStyle.CIRCLE)
                    .setupWithViewPager(viewPager)

```
### 2.DrawableIndicator

使用DrawableIndicator可以设置bitmap drawable或者vector drawable,可以自定义drawable的大小,具体使用步骤如下：

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

#### (2) DrawableIndicator与ViewPager/ViewPager2关联:

```
        val indicatorView = findViewById<DrawableIndicator>(R.id.indicator_view)
        val dp10 = resources.getDimensionPixelOffset(R.dimen.dp_10)
        indicatorView
              .setIndicatorGap(resources.getDimensionPixelOffset(R.dimen.dp_2_5))
              .setIndicatorDrawable(R.drawable.heart_empty, R.drawable.heart_red)
              .setIndicatorSize(dp10, dp10, dp10, dp10)
              .setupWithViewPager(viewPager)
```

### 3.自定义Indicator样式

例子将实现一个如下图的indicator样式

| Custom IndicatorView Style|
|--|
| ![NORMAL](https://github.com/zhpanvip/Resource/blob/master/image/banner/style_custum.gif) |

#### (1)自定义View并继承BaseIndicatorView

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
#### (2)自定义的indicator与ViewPager/ViewPager2关联

```
    val indicatorView = findViewById<FigureIndicatorView>(R.id.indicator_view)
            indicatorView.setBackgroundColor(Color.parseColor("#aa118EEA"))
            indicatorView.setTextSize(IndicatorUtils.dp2px(13f))
                    .setupWithViewPager(viewPager)
```

## FAQ

 **如有问题可以加入QQ群交流**

![QQ交流群60902509](https://github.com/zhpanvip/Resource/blob/master/image/group/qq_group.png)


## <span id="Sponsor"> Sponsor </span>

**开源不易 随心赞赏**

| Alipay | WeChat |
|--|--|
| ![NORMAL](https://github.com/zhpanvip/Resource/blob/master/image/pay/pay_alipay.jpg) |  ![SMOOTH](https://github.com/zhpanvip/Resource/blob/master/image/pay/pay_wechat.png) |


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






