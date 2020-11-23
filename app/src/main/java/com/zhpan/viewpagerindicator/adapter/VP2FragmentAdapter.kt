package com.zhpan.viewpagerindicator.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhpan.viewpagerindicator.fragmnet.BaseFragment
import com.zhpan.viewpagerindicator.fragmnet.VP2Fragment
import com.zhpan.viewpagerindicator.fragmnet.VPFragment

/**
 *
 * @author zhangpan
 * @date 2020/11/23
 */
class VP2FragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments: SparseArray<BaseFragment> = SparseArray()

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment
        when (position) {
            0 -> {
                if (fragments.get(PAGE_FIRST_TAB) == null) {
                    fragment = VP2Fragment.getInstance()
                    fragments.put(PAGE_FIRST_TAB, fragment)
                } else {
                    fragment = fragments.get(PAGE_FIRST_TAB)
                }
            }
            1 -> {
                if (fragments.get(PAGE_SECOND_TAB) == null) {
                    fragment = VPFragment.getInstance();
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

    companion object {
        const val PAGE_FIRST_TAB = 0
        const val PAGE_SECOND_TAB = 1
    }

}