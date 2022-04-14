package com.marko590.tabtestfinal.menu.settings

import android.content.Context
import android.os.Bundle
import android.text.InputType
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(com.marko590.tabtestfinal.R.xml.preferences, rootKey);

        var weight=findPreference<EditTextPreference>("user_weight")
        val sharedPref = requireContext().getSharedPreferences("UserInfoPref", Context.MODE_PRIVATE)
        weight!!.summary="Current weight is : " +sharedPref.getInt("usrWeight",0).toString()
//        sharedPref.getInt("usrWeight",0)

        val editTextPreference = preferenceManager.findPreference<EditTextPreference>("user_weight")
        editTextPreference!!.setOnBindEditTextListener { editText ->
            editText.inputType = InputType.TYPE_CLASS_NUMBER
        }

        weight!!.onPreferenceChangeListener=Preference.OnPreferenceChangeListener { preference, newValue ->

            val editor = sharedPref.edit()

//            editor.apply{
//                putInt("usrWeight",weight.text as Int)
//                apply()
//            }

            true

        }
    }


}