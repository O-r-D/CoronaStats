package com.ord.coronastats.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ord.coronastats.R
import com.ord.coronastats.ui.country.CountryStatsFragment
import com.ord.coronastats.ui.world.WorldFragment
import kotlinx.android.synthetic.main.activity_main.*


private const val NUM_PAGES = 2

class MainActivity : AppCompatActivity() {


    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = pager

        viewPager.adapter = ScreenSlidePagerAdapter(this)


    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0)
            super.onBackPressed()
        else
            viewPager.currentItem == 0
    }

    class ScreenSlidePagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> CountryStatsFragment.newInstance()
                1  -> WorldFragment.newInstance()
                else -> WorldFragment.newInstance()
            }
        }

    }
}
