package com.marko590.tabtestfinal

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.marko590.tabtestfinal.menu.about.AboutActivity
import com.marko590.tabtestfinal.menu.settings.SettingsActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout:TabLayout =findViewById(R.id.tab_layout)


        tabLayout.setSelectedTabIndicatorColor(Color.WHITE)
     //   tab_layout.setBackgroundColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_dark_default_color_primary_dark))
        tabLayout.tabTextColors = ContextCompat.getColorStateList(this, android.R.color.white)

        val numberOfTabs = 3

        tabLayout.tabMode = TabLayout.MODE_FIXED

        tabLayout.isInlineLabel = true

        val tabsViewpager : ViewPager2 =findViewById(R.id.tabs_viewpager)
        val adapter = TabsPagerAdapter(supportFragmentManager, lifecycle, numberOfTabs)
        tabsViewpager.adapter = adapter

        tabsViewpager.isUserInputEnabled = true

        TabLayoutMediator(tabLayout, tabsViewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_today_24, theme)
                    tab.text = "Today"

                }
                1 -> {
                    tab.text = "Water"
                    tab.icon = ResourcesCompat.getDrawable(resources,R.drawable.ic_ceed60763b5891b3a88277b7b40330c4,theme)

                }
                2 -> {
                    tab.text = "Stats"
                    tab.icon= ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_bar_chart_24, theme)
                }

            }

            tab.icon?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.WHITE,
                    BlendModeCompat.SRC_ATOP
                )
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "About selected", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}