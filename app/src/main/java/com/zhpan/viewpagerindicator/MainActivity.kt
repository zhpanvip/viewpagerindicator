package com.zhpan.viewpagerindicator

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zhpan.viewpagerindicator.adapter.VP2FragmentAdapter

open class MainActivity : BaseDataActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.apply {
            isUserInputEnabled = false
            adapter = VP2FragmentAdapter(this@MainActivity)
            offscreenPageLimit = 2
        }
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
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
