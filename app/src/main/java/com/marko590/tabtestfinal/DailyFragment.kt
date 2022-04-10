package com.marko590.tabtestfinal
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.marko590.tabtestfinal.databinding.DailyFragmentBinding



class DailyFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<DailyFragmentBinding>(inflater,
            R.layout.daily_fragment,container,false)

        binding.tvPercentage.text = binding.pbCaloriesTracker.progress.toString()
        binding.pbCaloriesTracker.progress = binding.pbCaloriesTracker.progress

        binding.btnAdd.setOnClickListener {
            Intent(context, UserInfoActivity::class.java).also {
                startActivity(it)
            }
        }

        // Taking user input values from UserInfoPref
        val sharedPref = requireContext().getSharedPreferences("UserInfoPref", Context.MODE_PRIVATE)
        val height = sharedPref.getInt("usrHeight", 0)
        val weight = sharedPref.getInt("usrWeight", 0)
        val age = sharedPref.getInt("usrAge", 0)
        val isWoman = sharedPref.getBoolean("isWoman", false)
        val isMan = sharedPref.getBoolean("isMan", false)
        val activityLevel = sharedPref.getString("activityLevel", null)


        // Setting daily intake of nutrients according to user input
        var bmr = 0.0
        var proteinIntake = 0
        var fatIntake = 0
        var carbsIntake = 0

        // Calculating user BMR using Mifflin St Jeor Equation
        if(isMan) {
            bmr = 10 * weight + 6.25 * height - 5 * age + 5
        } else if(isWoman) {
            bmr = 10 * weight + 6.25 * height - 5 * age - 161
        }

        // Calculating nutrients intake based on activity level
        // 4 kCal = 1g of protein and carbs
        // 9 kCal = 1g of fat
        var calorieIntake = 0
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

        var calorieProgress = 0
        var proteinProgress = 0
        var fatProgress = 0
        var carbsProgress = 0

        // Setting the values of text views according to current progress
        binding.tvCaloriesCounter.text = getString(R.string.kCalProgress, calorieProgress, calorieIntake)
        binding.tvProteinIntake.text = getString(R.string.nutrientsProgress, proteinProgress, proteinIntake, 0)
        binding.tvFatIntake.text = getString(R.string.nutrientsProgress, fatProgress, fatIntake, 0)
        binding.tvCarbsIntake.text = getString(R.string.nutrientsProgress, carbsProgress, carbsIntake, 0)

        return binding.root
    }

}
