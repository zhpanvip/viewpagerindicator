package com.zhpan.viewpagerindicator;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *   Created by zhpan on 2020/4/11.
 *   Description:
 * </pre>
 */
public class PagerViewHolder extends RecyclerView.ViewHolder {
  public PagerViewHolder(@NonNull View itemView) {
    super(itemView);
  }

  public void bind(int res, int position) {
    ImageView imageView = itemView.findViewById(R.id.banner_image);
    imageView.setBackgroundColor(itemView.getContext().getResources().getColor(res));
  }
}
