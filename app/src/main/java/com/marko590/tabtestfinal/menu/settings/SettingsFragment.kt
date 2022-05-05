package com.marko590.tabtestfinal.menu.settings

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(com.marko590.tabtestfinal.R.xml.preferences, rootKey);

        var weight=findPreference<EditTextPreference>("user_weight")
        val sharedPref = requireContext().getSharedPreferences("UserInfoPref", Context.MODE_PRIVATE)
        weight!!.summary="Current weight is : " +sharedPref.getInt("usrWeight",0).toString()


        val editTextPreference = preferenceManager.findPreference<EditTextPreference>("user_weight")
        editTextPreference!!.setOnBindEditTextListener { editText ->
            editText.inputType = InputType.TYPE_CLASS_NUMBER
        }
        val editor = sharedPref.edit()
        weight!!.onPreferenceChangeListener=Preference.OnPreferenceChangeListener { preference, newValue ->


        if(newValue.toString().toInt()<300){
           editor.apply{
               putInt("usrWeight",newValue.toString().toInt())
                apply()
          }
            weight!!.summary="Current weight is : " +sharedPref.getInt("usrWeight",0).toString()
        }
            else{
            Toast.makeText(
                requireView().context,
                "Please enter a valid value.",
                Toast.LENGTH_SHORT
            ).show()
        }
            true

        }
    }


}