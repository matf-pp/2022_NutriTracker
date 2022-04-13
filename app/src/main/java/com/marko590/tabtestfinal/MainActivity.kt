package com.marko590.tabtestfinal

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tab_layout:TabLayout =findViewById(R.id.tab_layout)


        tab_layout.setSelectedTabIndicatorColor(Color.WHITE)
     //   tab_layout.setBackgroundColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_dark_default_color_primary_dark))
        tab_layout.tabTextColors = ContextCompat.getColorStateList(this, android.R.color.white)

        val numberOfTabs = 3

        tab_layout.tabMode = TabLayout.MODE_FIXED

        tab_layout.isInlineLabel = true

        val tabs_viewpager : ViewPager2 =findViewById(R.id.tabs_viewpager)
        val adapter = TabsPagerAdapter(supportFragmentManager, lifecycle, numberOfTabs)
        tabs_viewpager.adapter = adapter

        tabs_viewpager.isUserInputEnabled = true

        TabLayoutMediator(tab_layout, tabs_viewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Today"

                }
                1 -> {
                    tab.text = "Water&Steps"


                }
                2 -> {
                    tab.text = "Stats"

                }

            }

            tab.icon?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.GREEN,
                    BlendModeCompat.SRC_ATOP
                )
        }.attach()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings -> Toast.makeText(this,"Settings selected",Toast.LENGTH_SHORT).show()
            R.id.about ->Toast.makeText(this,"About selected",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

}