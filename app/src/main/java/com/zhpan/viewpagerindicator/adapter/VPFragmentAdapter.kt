package com.zhpan.viewpagerindicator.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.zhpan.viewpagerindicator.fragmnet.BaseFragment
import com.zhpan.viewpagerindicator.fragmnet.VP2Fragment

/**
 *
 * @author zhangpan
 * @date 2020/11/23
 */
class VPFragmentAdapter(
    fragmentManager: FragmentManager,
    behavior: Int
) : FragmentStatePagerAdapter(fragmentManager, behavior) {

  private val fragments: SparseArray<BaseFragment> = SparseArray()

  companion object {
    const val PAGE_FIRST_TAB = 0
    const val PAGE_SECOND_TAB = 1
  }

  override fun getCount(): Int {
    return 2
  }

  override fun getItem(position: Int): Fragment {
    val fragment: Fragment
    when (position) {
        PAGE_FIRST_TAB -> {
            if (fragments.get(PAGE_FIRST_TAB) == null) {
                fragment = VP2Fragment.getInstance()
                fragments.put(PAGE_FIRST_TAB, fragment)
            } else {
                fragment = fragments.get(PAGE_FIRST_TAB)
            }
        }
        PAGE_SECOND_TAB -> {
            if (fragments.get(PAGE_SECOND_TAB) == null) {
                fragment = VP2Fragment.getInstance();
                fragments.put(PAGE_SECOND_TAB, fragment)
            } else {
                fragment = fragments.get(PAGE_SECOND_TAB)
            }
        }
      else -> {
        if (fragments.get(PAGE_FIRST_TAB) == null) {
          fragment = VP2Fragment.getInstance();
          fragments.put(PAGE_FIRST_TAB, fragment)
        } else {
          fragment = fragments.get(PAGE_FIRST_TAB)
        }
      }
    }
    return fragment
  }
}