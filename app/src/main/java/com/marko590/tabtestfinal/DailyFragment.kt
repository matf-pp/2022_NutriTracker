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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt


class DailyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<DailyFragmentBinding>(inflater,
            R.layout.daily_fragment,container,false)


        binding.fabHistory.setOnClickListener {
            Intent(context, UserInfoActivity::class.java).also {
                startActivity(it)
            }
        }


        val sharedPrefIntake = requireContext().getSharedPreferences("UserInfoPref", Context.MODE_PRIVATE)
        val calorieIntake = sharedPrefIntake.getInt("calorieIntake", 0)
        val proteinIntake = sharedPrefIntake.getInt("proteinIntake", 0)
        val fatIntake = sharedPrefIntake.getInt("fatIntake", 0)
        val carbsIntake = sharedPrefIntake.getInt("carbsIntake", 0)

        val sharedPrefProgress = requireContext().getSharedPreferences("NutrientsRef", Context.MODE_PRIVATE)
        val editorProgress = sharedPrefProgress.edit()

        var calorieProgress = sharedPrefProgress.getInt("calorieProgress", 0)
        var proteinProgress = sharedPrefProgress.getInt("proteinProgress", 0)
        var fatProgress = sharedPrefProgress.getInt("fatProgress", 0)
        var carbsProgress = sharedPrefProgress.getInt("carbsProgress", 0)



        fun calculatePercentage(curr: Int, target: Int) : Int {
            return (curr / (target / 100.0)).roundToInt()
        }

        // Setting the values of text views according to current progress
        fun text(){
            binding.tvCaloriesCounter.text = getString(R.string.kCalProgress, calorieProgress, calorieIntake)
            binding.pbCaloriesTracker.progress = calculatePercentage(calorieProgress, calorieIntake)
            binding.tvCaloriesIntake.text = calculatePercentage(calorieProgress, calorieIntake).toString()

            binding.tvProteinIntake.text = getString(R.string.nutrientsProgress, proteinProgress, proteinIntake,
                calculatePercentage(proteinProgress, proteinIntake))
            binding.pbProteinIntake.progress = calculatePercentage(proteinProgress, proteinIntake)

            binding.tvFatIntake.text = getString(R.string.nutrientsProgress, fatProgress, fatIntake,
                calculatePercentage(fatProgress, fatIntake))
            binding.pbFatIntake.progress = calculatePercentage(fatProgress, fatIntake)

            binding.tvCarbsIntake.text = getString(R.string.nutrientsProgress, carbsProgress, carbsIntake,
                calculatePercentage(carbsProgress, carbsIntake))
            binding.pbCarbsIntake.progress = calculatePercentage(carbsProgress, carbsIntake)
        }

        fun get(){
            calorieProgress = sharedPrefProgress.getInt("calorieProgress", 0)
            proteinProgress = sharedPrefProgress.getInt("proteinProgress", 0)
            fatProgress = sharedPrefProgress.getInt("fatProgress", 0)
            carbsProgress = sharedPrefProgress.getInt("carbsProgress", 0)
        }


        // In order to show values before button was clicked
        text()

        binding.btnAdd.setOnClickListener{
            // TODO: Change values of variables according to user input
            editorProgress.apply{
                putInt("calorieProgress", calorieProgress)
                putInt("proteinProgress", proteinProgress)
                putInt("fatProgress", fatProgress)
                putInt("carbsProgress", carbsProgress)
                apply()
            }
            get()
            text()
        }

        // Reset values every other day
        val sharedPrefStorage = requireContext().getSharedPreferences("StoragePref", Context.MODE_PRIVATE)
        val editorStorage = sharedPrefStorage.edit()

        val currDate = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val currDateFormatted = currDate.format(formatter)
        val today = sharedPrefStorage.getString("dateToday", null)


        if(today == null){
            editorStorage.apply{
                putString("dateToday", currDateFormatted)
                apply()
            }
        } else if(currDateFormatted != today){
            editorStorage.apply {
                putString("dateYesterday", today)
                putString("dateToday", currDateFormatted)
                putInt("calorieIntake", calorieProgress)
                apply()
            }
            editorProgress.apply {
                putInt("calorieProgress", 0)
                putInt("proteinProgress", 0)
                putInt("fatProgress", 0)
                putInt("carbsProgress", 0)
                apply()
                get()
                text()
            }
        }


        return binding.root
    }

}
