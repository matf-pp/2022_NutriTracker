package com.marko590.tabtestfinal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        val sharedPref = getSharedPreferences("UserInfoPref", Context.MODE_PRIVATE)
        if(sharedPref.contains("usrName")){
            moveToSecondary()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)



        // Creating spinner for activity level
        val listActivity = resources.getStringArray(R.array.activityLevel)
        val spinner = sActivity
        if(spinner != null){
            val adapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listActivity)
            spinner.adapter = adapter
        }

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

        val sharedPref = getSharedPreferences("UserInfoPref", Context.MODE_PRIVATE)
        var bmr = 0.0
        var calorieIntake = 0
        var proteinIntake = 0
        var fatIntake = 0
        var carbsIntake = 0

        fun calculateNutrients(){

            val height = itHeight.text.toString().toInt()
            val weight = itWeight.text.toString().toInt()
            val age = itAge.text.toString().toInt()
            val isWoman = rbWoman.isChecked
            val isMan = rbMan.isChecked
            val activityLevel = sActivity.selectedItem.toString()

            // Calculating user BMR using Mifflin St Jeor Equation
            if(isMan) {
                bmr = 10 * weight + 6.25 * height - 5 * age + 5
            } else if(isWoman) {
                bmr = 10 * weight + 6.25 * height - 5 * age - 161
            }

            // Calculating nutrients intake based on activity level
            // 4 kCal = 1g of protein and carbs
            // 9 kCal = 1g of fat
            val activity = resources.getStringArray(R.array.activityLevel)
            when(activityLevel){
                activity[0] -> { // Sedentary
                    calorieIntake = (bmr * 1.2).toInt()
                    proteinIntake = (calorieIntake * 0.1 / 4).toInt()
                    fatIntake = (calorieIntake * 0.25 / 9).toInt()
                    carbsIntake = (calorieIntake * 0.45 / 4).toInt()
                }
                activity[1] -> { // Lightly active
                    calorieIntake = (bmr * 1.375).toInt()
                    proteinIntake = (calorieIntake * 0.15 / 4).toInt()
                    fatIntake = (calorieIntake * 0.3 / 9).toInt()
                    carbsIntake = (calorieIntake * 0.5 / 4).toInt()
                }
                activity[2] -> { // Moderately active
                    calorieIntake = (bmr * 1.55).toInt()
                    proteinIntake = (calorieIntake * 0.2 / 4).toInt()
                    fatIntake = (calorieIntake * 0.3 / 9).toInt()
                    carbsIntake = (calorieIntake * 0.55 / 4).toInt()
                }
                activity[3] -> { // Active
                    calorieIntake = (bmr * 1.725).toInt()
                    proteinIntake = (calorieIntake * 0.25 / 4).toInt()
                    fatIntake = (calorieIntake * 0.3 / 9).toInt()
                    carbsIntake = (calorieIntake * 0.6 / 4).toInt()
                }
                activity[4] -> { // Very active
                    calorieIntake = (bmr * 1.9).toInt()
                    proteinIntake = (calorieIntake * 0.3 / 4).toInt()
                    fatIntake = (calorieIntake * 0.3 / 9).toInt()
                    carbsIntake = (calorieIntake * 0.65 / 4).toInt()
                }
            }
        }

        val editor = sharedPref.edit()

        fabNext.setOnClickListener {
            if (validateFields()) {
                // Setting shared preference for user input
                editor.apply {
                    putString("usrName", itName.text.toString())
                    putInt("usrHeight", itHeight.text.toString().toInt())
                    putInt("usrWeight", itWeight.text.toString().toInt())
                    putInt("usrAge", itAge.text.toString().toInt())
                    putBoolean("isWoman", rbWoman.isChecked)
                    putBoolean("isMan", rbMan.isChecked)
                    putString("activityLevel", sActivity.selectedItem.toString())

                    calculateNutrients()

                    putInt("calorieIntake", calorieIntake)
                    putInt("proteinIntake", proteinIntake)
                    putInt("fatIntake", fatIntake)
                    putInt("carbsIntake", carbsIntake)
                    apply()
                }
                moveToSecondary()
            }
        }
    }
    private fun moveToSecondary(){
        Intent(this, MainActivity::class.java).also{
            startActivity(it)
        }
    }
}