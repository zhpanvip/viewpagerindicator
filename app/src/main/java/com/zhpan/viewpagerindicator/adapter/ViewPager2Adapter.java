package com.zhpan.viewpagerindicator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhpan.viewpagerindicator.PagerViewHolder;
import com.zhpan.viewpagerindicator.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *   Created by zhangpan on 2020-01-20.
 *   Description:
 * </pre>
 */
public class ViewPager2Adapter extends RecyclerView.Adapter<PagerViewHolder> {
  private final List<Integer> mDrawableList = new ArrayList<>();

  public ViewPager2Adapter(List<Integer> drawableList) {
    mDrawableList.addAll(drawableList);
  }

  @NonNull
  @Override
  public PagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slide_mode, parent, false);
    return new PagerViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull PagerViewHolder holder, int position) {
    holder.bind(mDrawableList.get(position), position);
  }

  @Override
  public int getItemCount() {
    return mDrawableList.size();
  }
}
