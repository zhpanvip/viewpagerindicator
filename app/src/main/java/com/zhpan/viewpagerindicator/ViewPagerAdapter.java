package com.zhpan.viewpagerindicator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *   Created by zhangpan on 2020-01-20.
 *   Description:
 * </pre>
 */
public class ViewPagerAdapter extends PagerAdapter {
    private final List<Integer> mDrawableList = new ArrayList<>();

    public ViewPagerAdapter(List<Integer> drawableList) {
        mDrawableList.addAll(drawableList);
    }

    @Override
    public int getCount() {
        return mDrawableList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        super.finishUpdate(container);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_slide_mode, container, false);
        ImageView imageView = view.findViewById(R.id.banner_image);
        imageView.setBackgroundColor(container.getContext().getResources().getColor(mDrawableList.get(position)));
        container.addView(view);
        return view;
    }
}
