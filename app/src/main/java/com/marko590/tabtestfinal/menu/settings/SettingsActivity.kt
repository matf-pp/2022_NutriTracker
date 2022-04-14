package com.marko590.tabtestfinal.menu.settings

import android.R
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity


class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, SettingsFragment())
            .commit()
    }
}