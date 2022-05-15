package com.marko590.tabtestfinal
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.marko590.tabtestfinal.data.Day
import com.marko590.tabtestfinal.data.Food
import com.marko590.tabtestfinal.data.UserViewFood
import com.marko590.tabtestfinal.databinding.DailyFragmentBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt


class DailyFragment : Fragment() {

    private lateinit var mUserViewModel : UserViewFood
    private var calorieIntake = 1
    private var proteinIntake = 1
    private var fatIntake = 1
    private var carbsIntake = 1

    private var calorieProgress = 0f
    private var proteinProgress = 0f
    private var fatProgress = 0f
    private var carbsProgress = 0f

    private fun getCurrentDate(): String? {
        val currDate = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return currDate.format(formatter)
    }


    private fun calculatePercentage(curr: Float, target: Int) : Int {
        return (curr / (target / 100.0)).roundToInt()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<DailyFragmentBinding>(inflater,
            R.layout.daily_fragment,container,false)

        val sharedPrefIntake = requireContext().getSharedPreferences("UserInfoPref", Context.MODE_PRIVATE)
        calorieIntake = sharedPrefIntake.getInt("spCalorieIntake", 1)
        proteinIntake = sharedPrefIntake.getInt("spProteinIntake", 1)
        fatIntake = sharedPrefIntake.getInt("spFatIntake", 1)
        carbsIntake = sharedPrefIntake.getInt("spCarbsIntake", 1)

        val sharedPrefProgress = requireContext().getSharedPreferences("NutrientsPref", Context.MODE_PRIVATE)
        val editorProgress = sharedPrefProgress.edit()

        fun get(){
            calorieProgress = sharedPrefProgress.getFloat("spCalorieProgress", 0f)
            proteinProgress = sharedPrefProgress.getFloat("spProteinProgress", 0f)
            fatProgress = sharedPrefProgress.getFloat("spFatProgress", 0f)
            carbsProgress = sharedPrefProgress.getFloat("spCarbsProgress", 0f)
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

        // In order to show values before button was clicked
        get()
        text()

        mUserViewModel= ViewModelProvider(this)[UserViewFood::class.java]
        var data: List<Food>? = null
        mUserViewModel.readAllFood.observe(viewLifecycleOwner) {
            data = it
        }

        binding.btnAdd.setOnClickListener {
            mUserViewModel.readAllFood.observe(viewLifecycleOwner) {
                data = it
            }
            val foodName = binding.txtFoodName.text.toString()
            for(food in data!!){
                if(food.name == foodName){
                    get()
                    if(calorieProgress < calorieIntake) {
                        calorieProgress += food.calorie
                        if (calorieProgress >= calorieIntake)
                            calorieProgress = calorieIntake.toFloat()
                    }

                    if(proteinProgress < proteinIntake){
                        proteinProgress += food.protein
                        if (proteinProgress >= proteinIntake)
                            proteinProgress = proteinIntake.toFloat()
                    }

                    if(fatProgress < fatIntake){
                        fatProgress += food.fat
                        if (fatProgress >= fatIntake)
                            fatProgress = fatIntake.toFloat()
                    }
                    if(carbsProgress < carbsIntake){
                        carbsProgress += food.carbs
                        if (carbsProgress >= carbsIntake)
                            carbsProgress = carbsIntake.toFloat()
                    }
                    editorProgress.apply {
                        putFloat("spCalorieProgress", calorieProgress)
                        putFloat("spProteinProgress", proteinProgress)
                        putFloat("spFatProgress", fatProgress)
                        putFloat("spCarbsProgress", carbsProgress)
                        apply()
                    }
                    get()
                    text()
                }
            }
        }

        // Reset values every other day
        val sharedPrefStorage = requireContext().getSharedPreferences("StoragePref", Context.MODE_PRIVATE)
        val editorStorage = sharedPrefStorage.edit()

        val currDateFormatted = getCurrentDate()
        val today = sharedPrefStorage.getString("spDateToday", null)

        if(today == null){
            editorStorage.apply{
                putString("spDateToday", currDateFormatted)
                apply()
            }
        } else if(currDateFormatted != today){
            editorStorage.apply {
                putString("spDateToday", currDateFormatted)
                get()
                mUserViewModel.addDay(Day(0,today, calorieProgress, proteinProgress, fatProgress, carbsProgress))
                apply()
            }
            editorProgress.apply {
                putFloat("spCalorieProgress", 0f)
                putFloat("spProteinProgress", 0f)
                putFloat("spFatProgress", 0f)
                putFloat("spCarbsProgress", 0f)
                apply()
                get()
                text()
            }
        }
        return binding.root
    }
}
