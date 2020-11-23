package com.zhpan.viewpagerindicator.fragmnet

import androidx.fragment.app.Fragment
import java.util.ArrayList

/**
 *
 * @author zhangpan
 * @date 2020/11/23
 */
open class BaseFragment : Fragment() {

    protected fun getData(j: Int): List<Int> {
        val mDrawableList = ArrayList<Int>()
        for (i in 0 until j) {
            val drawable = resources.getIdentifier("color$i", "color", context?.packageName)
            mDrawableList.add(drawable)
        }
        return mDrawableList
    }
}