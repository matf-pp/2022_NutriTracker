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
        setPreferencesFromResource(com.marko590.tabtestfinal.R.xml.preferences, rootKey)

        val weight=findPreference<EditTextPreference>("user_weight")
        val height=findPreference<EditTextPreference>("user_height")
        val age=findPreference<EditTextPreference>("user_age")
        val sharedPref = requireContext().getSharedPreferences("UserInfoPref", Context.MODE_PRIVATE)
        weight!!.summary="Current weight is : " +sharedPref.getInt("usrWeight",0).toString()
        height!!.summary="Current height is : " +sharedPref.getInt("usrHeight",0).toString()
        age!!.summary="Current age is : " +sharedPref.getInt("usrAge",0).toString()

        val editor = sharedPref.edit()



        val editTextPreferenceWeight = preferenceManager.findPreference<EditTextPreference>("user_weight")
        editTextPreferenceWeight!!.setOnBindEditTextListener { editText ->
            editText.inputType = InputType.TYPE_CLASS_NUMBER
        }
        weight.onPreferenceChangeListener=Preference.OnPreferenceChangeListener { preference, newValue ->


        if(newValue.toString().toInt()<300&&newValue.toString().toInt()>0){
           editor.apply{
               putInt("usrWeight",newValue.toString().toInt())
                apply()
          }
            weight.summary="Current weight is : " +sharedPref.getInt("usrWeight",0).toString()
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

        val editTextPreferenceHeight = preferenceManager.findPreference<EditTextPreference>("user_height")
        editTextPreferenceHeight!!.setOnBindEditTextListener { editText ->
            editText.inputType = InputType.TYPE_CLASS_NUMBER
        }
        height.onPreferenceChangeListener=Preference.OnPreferenceChangeListener { preference, newValue ->


            if(newValue.toString().toInt()<230&&newValue.toString().toInt()>50){
                editor.apply{
                    putInt("usrHeight",newValue.toString().toInt())
                    apply()
                }
                height.summary="Current height is : " +sharedPref.getInt("usrHeight",0).toString()
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

        val editTextPreferenceAge = preferenceManager.findPreference<EditTextPreference>("user_age")
        editTextPreferenceAge!!.setOnBindEditTextListener { editText ->
            editText.inputType = InputType.TYPE_CLASS_NUMBER
        }
        age.onPreferenceChangeListener=Preference.OnPreferenceChangeListener { preference, newValue ->


            if(newValue.toString().toInt()<100&&newValue.toString().toInt()>0){
                editor.apply{
                    putInt("usrAge",newValue.toString().toInt())
                    apply()
                }
                age.summary="Current age is : " +sharedPref.getInt("usrAge",0).toString()
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