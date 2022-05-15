package com.marko590.tabtestfinal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.marko590.tabtestfinal.data.Food
import com.marko590.tabtestfinal.data.UserViewFood
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : AppCompatActivity() {

    private lateinit var mUserViewModel : UserViewFood

    var bmr = 0.0
    var calorieIntake = 0
    var proteinIntake = 0
    var fatIntake = 0
    var carbsIntake = 0

    private fun addDatabase(){
        mUserViewModel= ViewModelProvider(this)[UserViewFood::class.java]
        mUserViewModel.addFood(Food(0, "Banana", 92f, 1.1f,0.33f ,22.84f))
        mUserViewModel.addFood(Food(0, "Apple", 52f,0.03f, 0.17f, 13.8f))
        mUserViewModel.addFood(Food(0, "Fries", 220f,3.4f, 5.48f, 25.55f))
        mUserViewModel.addFood(Food(0, "Cabbage", 25f,1.28f, 0.1f,5.8f))
        mUserViewModel.addFood(Food(0, "Onion", 40f,1.1f,0.1f,9.3f ))
        mUserViewModel.addFood(Food(0, "Yogurt", 61f,3.5f, 3.3f, 4.7f ))
        mUserViewModel.addFood(Food(0, "Milk", 64f, 3.3f, 3.8f, 4.7f))
        mUserViewModel.addFood(Food(0, "Tomato", 19f,0.9f, 0.2f,3.9f))
        mUserViewModel.addFood(Food(0, "Pork", 297f, 25.7f, 20.8f, 0f))
        mUserViewModel.addFood(Food(0, "Chicken breast", 118f,22.5f, 2.62f,0f ))
        mUserViewModel.addFood(Food(0, "Omelette", 167f, 11.2f, 12.1f,2.1f))
        mUserViewModel.addFood(Food(0, "Salmon", 206f,22f,12f,0f))
        mUserViewModel.addFood(Food(0, "Bread", 265f, 9f, 3.2f,49f ))
        mUserViewModel.addFood(Food(0, "Donut", 452f, 4.9f, 25f, 51f ))
        mUserViewModel.addFood(Food(0, "Cereals", 389f, 16.89f, 6.9f, 66.27f ))
        mUserViewModel.addFood(Food(0, "Rice", 360f,6.61f,0.58f,79.34f))
        mUserViewModel.addFood(Food(0, "Pizza", 266f, 11f, 10f,33f ))
        mUserViewModel.addFood(Food(0, "Spaghetti", 157f, 6f, 0.9f, 31f ))
        mUserViewModel.addFood(Food(0, "Chocolate", 545f, 4.9f, 31f, 61f ))
        mUserViewModel.addFood(Food(0, "Ice cream", 207f, 3.5f, 11f,24f))
        mUserViewModel.addFood(Food(0, "Coca Cola", 44f,0f, 0f, 12f))
        mUserViewModel.addFood(Food(0, "Red wine",85f, 0.1f, 0f, 2.6f ))
    }

    private fun calculateNutrients(){

        val height = itHeight.text.toString().toDouble()
        val weight = itWeight.text.toString().toDouble()
        val age = itAge.text.toString().toDouble()
        val isWoman = rbWoman.isChecked
        val isMan = rbMan.isChecked
        val activityLevel = sActivity.selectedItem.toString()

        // Calculating user BMR using Mifflin St Jeor Equation
        if(isMan) {
            bmr = 10.0 * weight + 6.25 * height - 5 * age + 5
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

    // Check and display error message if input is missing
    private fun validateFields():Boolean {
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

        addDatabase()

        // Creating spinner for activity level
        val listActivity = resources.getStringArray(R.array.activityLevel)
        val spinner = sActivity
        if(spinner != null){
            val adapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listActivity)
            spinner.adapter = adapter
        }

        val sharedPref = getSharedPreferences("UserInfoPref", Context.MODE_PRIVATE)
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