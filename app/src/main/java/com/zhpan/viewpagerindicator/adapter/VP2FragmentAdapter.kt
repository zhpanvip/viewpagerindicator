package com.zhpan.viewpagerindicator.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhpan.viewpagerindicator.fragmnet.VP2Fragment
import com.zhpan.viewpagerindicator.fragmnet.VPFragment

/**
 *
 * @author zhangpan
 * @date 2020/11/23
 */
class VP2FragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(
  fragmentActivity
) {

  override fun getItemCount(): Int {
    return 2
  }

  override fun createFragment(position: Int): Fragment {
   return when (position) {
        1-> VPFragment.getInstance()
      else ->
         VP2Fragment.getInstance()
    }
  }
}