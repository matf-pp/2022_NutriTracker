package com.marko590.tabtestfinal
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.room.Query
import com.marko590.tabtestfinal.data.Day
import com.marko590.tabtestfinal.data.Food
import com.marko590.tabtestfinal.data.UserViewFood
import com.marko590.tabtestfinal.databinding.DailyFragmentBinding
import kotlinx.android.synthetic.main.daily_fragment.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt


class DailyFragment : Fragment() {

    private lateinit var mUserViewModel : UserViewFood

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<DailyFragmentBinding>(inflater,
            R.layout.daily_fragment,container,false)


        val sharedPrefIntake = requireContext().getSharedPreferences("UserInfoPref", Context.MODE_PRIVATE)
        val calorieIntake = sharedPrefIntake.getInt("calorieIntake", 1)
        val proteinIntake = sharedPrefIntake.getInt("proteinIntake", 1)
        val fatIntake = sharedPrefIntake.getInt("fatIntake", 1)
        val carbsIntake = sharedPrefIntake.getInt("carbsIntake", 1)

        val sharedPrefProgress = requireContext().getSharedPreferences("NutrientsRef", Context.MODE_PRIVATE)
        val editorProgress = sharedPrefProgress.edit()

        var calorieProgress = sharedPrefProgress.getFloat("calorieProgress", 0f)
        var proteinProgress = sharedPrefProgress.getFloat("proteinProgress", 0f)
        var fatProgress = sharedPrefProgress.getFloat("fatProgress", 0f)
        var carbsProgress = sharedPrefProgress.getFloat("carbsProgress", 0f)



        fun calculatePercentage(curr: Float, target: Int) : Int {
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
            calorieProgress = sharedPrefProgress.getFloat("calorieProgress", 0f)
            proteinProgress = sharedPrefProgress.getFloat("proteinProgress", 0f)
            fatProgress = sharedPrefProgress.getFloat("fatProgress", 0f)
            carbsProgress = sharedPrefProgress.getFloat("carbsProgress", 0f)
        }


        // In order to show values before button was clicked
        text()



        mUserViewModel= ViewModelProvider(this).get(UserViewFood::class.java)
        var data: List<Food>? = null
        mUserViewModel.readAllFood.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            data=it
        })

        binding.btnAdd.setOnClickListener {

            mUserViewModel.readAllFood.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

                    data=it
                })
            val foodName = binding.txtFoodName.text.toString()
            for(food in data!!){
                if(food.name == foodName){
                    get()


                    if(calculatePercentage(calorieProgress, calorieIntake)<100) {
                        calorieProgress += food.calorie
                    }

                    if(calculatePercentage(calorieProgress, calorieIntake)>100)
                    {
                        pbCaloriesTracker.progress=100
                        calorieProgress=calorieIntake.toFloat()
                    }


                    if(calculatePercentage(proteinProgress, proteinIntake)<100) {
                        proteinProgress += food.protein
                    }
                    if(calculatePercentage(proteinProgress, proteinIntake)>100){
                        pbProteinIntake.progress=100
                        proteinProgress=proteinIntake.toFloat()
                    }

                    if(calculatePercentage(fatProgress, fatIntake)<100) {
                        fatProgress += food.fat
                    }
                    if(calculatePercentage(fatProgress, fatIntake)>100){
                        pbFatIntake.progress=100
                        fatProgress=fatIntake.toFloat()
                    }

                    if(calculatePercentage(carbsProgress, carbsIntake)<100) {
                        carbsProgress += food.carbs
                    }
                    if(calculatePercentage(carbsProgress, carbsIntake)>100){
                        pbCarbsIntake.progress=100
                        carbsProgress=carbsIntake.toFloat()
                    }

                    editorProgress.apply {
                        putFloat("calorieProgress", calorieProgress)
                        putFloat("proteinProgress", proteinProgress)
                        putFloat("fatProgress", fatProgress)
                        putFloat("carbsProgress", carbsProgress)
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
                putFloat("calorieIntake", calorieProgress)
                get()
                mUserViewModel.addDay(Day(0,today, calorieProgress, proteinProgress, fatProgress, carbsProgress))
                apply()
            }
            editorProgress.apply {
                putFloat("calorieProgress", 0f)
                putFloat("proteinProgress", 0f)
                putFloat("fatProgress", 0f)
                putFloat("carbsProgress", 0f)
                apply()
                get()
                text()
            }
        }


        return binding.root
    }

}
