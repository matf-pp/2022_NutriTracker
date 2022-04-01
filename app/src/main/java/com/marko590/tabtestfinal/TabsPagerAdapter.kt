package com.marko590.tabtestfinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var numberOfTabs: Int) : FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                // First fragment, today's stats
                val bundle = Bundle()
                bundle.putString("fragmentName", "Today Fragment")
                val todayFragment = DailyFragment()
                todayFragment.arguments = bundle
                return todayFragment
            }
            1 -> {
                // Second fragment, water intake and step count
                val bundle = Bundle()
                bundle.putString("fragmentName", "Water Fragment")
                val waterFragment = WaterFragment()
                waterFragment.arguments = bundle
                return waterFragment
            }
            2 -> {
                // Third fragment, statistics
                val bundle = Bundle()
                bundle.putString("fragmentName", "Stats Fragment")
                val statsFragment = StatsFragment()
                statsFragment.arguments = bundle
                return statsFragment
            }
            else -> return DailyFragment()
        }
    }

    override fun getItemCount(): Int {
        return numberOfTabs
    }
}