package com.marko590.tabtestfinal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val sharedPref = getSharedPreferences("UserInfoPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        // Check and display error message if input is missing
        fun validateFields():Boolean {
            if(itName.text.toString().isEmpty()){
                itName.error = "Name is required"
                return false
            }
            if(itHeight.text.toString().isEmpty()) {
                itHeight.error = "Height is required"
                return false
            }
            if(itWeight.text.toString().isEmpty()) {
                itWeight.error = "Weight is required"
                return false
            }
            if(itAge.text.toString().isEmpty()) {
                itAge.error = "Age is required"
                return false
            }
            return true
        }

        fabNext.setOnClickListener {
            if (validateFields()) {
                // Setting shared preference for user input
                editor.apply {
                    putString("usrName", itName.text.toString())
                    putInt("usrHeight", itHeight.text.toString().toInt())
                    putInt("usrWeight", itWeight.text.toString().toInt())
                    putInt("usrAge", itAge.text.toString().toInt())
                    putBoolean("isWoman", rbWoman.isSelected)
                    putBoolean("isMan", rbMan.isSelected)
                    apply()

                }
                finish()
            }
        }
    }
}