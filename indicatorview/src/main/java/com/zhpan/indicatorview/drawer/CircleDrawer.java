package com.zhpan.indicatorview.drawer;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.zhpan.indicatorview.model.IndicatorOptions;
import com.zhpan.indicatorview.utils.IndicatorUtils;

import static com.zhpan.indicatorview.enums.IndicatorSlideMode.NORMAL;
import static com.zhpan.indicatorview.enums.IndicatorSlideMode.SMOOTH;
import static com.zhpan.indicatorview.enums.IndicatorSlideMode.WORM;

/**
 * <pre>
 *   Created by zhpan on 2019/11/23.
 *   Description: Circle Indicator drawer.
 * </pre>
 */
public class CircleDrawer extends BaseDrawer {

    CircleDrawer(IndicatorOptions indicatorOptions) {
        super(indicatorOptions);
    }

    private RectF rectF = new RectF();

    @Override
    protected int measureHeight() {
        return (int) maxWidth;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (mIndicatorOptions.getPageSize() > 1) {
            drawNormal(canvas);
            drawSlider(canvas);
        }
    }

    private void drawNormal(Canvas canvas) {
        float normalIndicatorWidth = mIndicatorOptions.getNormalIndicatorWidth();
        mPaint.setColor(mIndicatorOptions.getNormalColor());
        for (int i = 0; i < mIndicatorOptions.getPageSize(); i++) {
            float coordinateX = IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, i);
            float coordinateY = IndicatorUtils.getCoordinateY(maxWidth);
            drawCircle(canvas, coordinateX, coordinateY, normalIndicatorWidth / 2);
        }
    }

    private void drawSlider(Canvas canvas) {
        mPaint.setColor(mIndicatorOptions.getCheckedColor());
        switch (mIndicatorOptions.getSlideMode()) {
            case NORMAL:
            case SMOOTH:
                drawCircleSlider(canvas);
                break;
            case WORM:
                drawWormSlider(canvas, mIndicatorOptions.getNormalIndicatorWidth());
                break;
        }
    }

    private void drawCircleSlider(Canvas canvas) {
        int currentPosition = mIndicatorOptions.getCurrentPosition();
        float startCoordinateX = IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, currentPosition);
        float endCoordinateX = IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, (currentPosition + 1) % mIndicatorOptions.getPageSize());
        float coordinateX = startCoordinateX + (endCoordinateX - startCoordinateX) * mIndicatorOptions.getSlideProgress();
        float coordinateY = IndicatorUtils.getCoordinateY(maxWidth);
        float radius = mIndicatorOptions.getCheckedIndicatorWidth() / 2;
        drawCircle(canvas, coordinateX, coordinateY, radius);
    }

    private void drawWormSlider(Canvas canvas, float sliderHeight) {
        float slideProgress = mIndicatorOptions.getSlideProgress();
        int currentPosition = mIndicatorOptions.getCurrentPosition();
        float distance = mIndicatorOptions.getIndicatorGap() + mIndicatorOptions.getNormalIndicatorWidth();
        float startCoordinateX = IndicatorUtils.getCoordinateX(mIndicatorOptions, maxWidth, currentPosition);
        float left = startCoordinateX + Math.max(distance * (slideProgress - 0.5f) * 2.0f, 0) - mIndicatorOptions.getNormalIndicatorWidth() / 2;
        float right = startCoordinateX + Math.min((distance * slideProgress * 2), distance) + mIndicatorOptions.getNormalIndicatorWidth() / 2;
        rectF.set(left, 0, right, sliderHeight);
        canvas.drawRoundRect(rectF, sliderHeight, sliderHeight, mPaint);
    }

    private void drawCircle(Canvas canvas, float coordinateX, float coordinateY, float radius) {
        canvas.drawCircle(coordinateX, coordinateY, radius, mPaint);
    }
}
