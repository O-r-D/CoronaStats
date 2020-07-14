package com.ord.coronastats.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ord.coronastats.R
import com.ord.coronastats.ui.country.CountryStatsFragment
import com.ord.coronastats.ui.world.WorldFragment
import kotlinx.android.synthetic.main.activity_main.*


private const val NUM_PAGES = 2

class MainActivity : AppCompatActivity() {


    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = pager

        viewPager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)


    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0)
            super.onBackPressed()
        else
            viewPager.currentItem == 0
    }

    class ScreenSlidePagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> CountryStatsFragment.newInstance()
                1 -> WorldFragment.newInstance()
                else -> WorldFragment.newInstance()
            }
        }

        override fun getCount(): Int = NUM_PAGES

    }
}
