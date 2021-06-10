package com.zhpan.viewpagerindicator

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.annotation.ColorRes
import java.util.ArrayList

open class BaseDataActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_base_data)
  }

  protected fun getData(j: Int): List<Int> {
    val mDrawableList = ArrayList<Int>()
    for (i in 0 until j) {
      val drawable = resources.getIdentifier("color$i", "color", packageName)
      mDrawableList.add(drawable)
    }
    return mDrawableList
  }

  protected fun getResColor(@ColorRes color: Int): Int {
    return resources.getColor(color)
  }
}
