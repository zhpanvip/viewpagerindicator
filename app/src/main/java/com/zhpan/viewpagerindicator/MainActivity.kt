package com.zhpan.viewpagerindicator

import android.os.Bundle
import com.zhpan.viewpagerindicator.adapter.VP2FragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : BaseDataActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    viewPager.apply {
      isUserInputEnabled = false
      adapter = VP2FragmentAdapter(this@MainActivity)
      offscreenPageLimit = 2
    }

    bottomNav.setOnNavigationItemSelectedListener { item ->
      when (item.itemId) {
          R.id.page1 -> {
              viewPager.setCurrentItem(0, true)
              true
          }
          R.id.page2 -> {
              viewPager.setCurrentItem(1, true)
              true
          }
        else -> {
          false
        }
      }

    }
  }
}
