package com.android.killqwerty.myapp.myapp3.android_2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.view.ViewPager.OnPageChangeListener
import com.android.killqwerty.myapp.myapp3.R

class Lesson6 : FragmentActivity(){
    lateinit var viewPager : ViewPager
    lateinit var pagerAdapter : PagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.android2_lesson6)
        viewPager = findViewById(R.id.and2_les6_viewpager)
        pagerAdapter = MyFragmentPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter

        viewPager.addOnPageChangeListener (object : OnPageChangeListener{
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageScrollStateChanged(position : Int) {

            }

            override fun onPageSelected(position: Int) {
            }
        })
    }
    companion object {
        val PAGE_COUNT = 3
    }
}
private class MyFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return Lesson6PageFragment.create(position)
    }
    override fun getCount(): Int {
        return Lesson6.PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "sms"
            1 -> return "sensors"
            2 -> return "bluetooth"
        }
        return null
    }
}