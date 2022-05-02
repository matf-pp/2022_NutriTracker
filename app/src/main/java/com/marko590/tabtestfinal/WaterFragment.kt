package com.marko590.tabtestfinal

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.marko590.tabtestfinal.databinding.WaterFragmentBinding
import kotlin.math.round
import kotlin.math.roundToInt

class WaterFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<WaterFragmentBinding>(inflater,
            R.layout.water_fragment,container,false)


        val progressBar = binding.pbWaterIntake
        val sharedPrefIntake = requireActivity().getSharedPreferences("mySharedPref", Activity.MODE_PRIVATE)
        val glassSize = resources.getStringArray(R.array.glassSize)
        val spinner = binding.sGlasses
        if(spinner != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                glassSize
            )
            spinner.adapter = adapter
        }

        fun calculatePercentage(curr: Int, target: Int) : Int {
            return (curr / (target / 100.0)).roundToInt()
        }
        val editorProgress = sharedPrefIntake.edit()

        var progress = sharedPrefIntake.getInt("waterProgress", 0)
        var waterToday = sharedPrefIntake.getInt("waterIntake", 0)
        binding.tvWaterIntake.text = waterToday.toString()+"ml/2000ml"
        progressBar.progress=progress
        binding.tvWaterProgress.text = "("+progress.toString()+"%)"
        binding.btnPlus.setOnClickListener {
            if(progress < 100) {
                var selectedSize = spinner.selectedItem.toString().take(3).toInt()

                waterToday = waterToday + selectedSize
                progress = (100.0 * waterToday / 2000).toInt()
                if(progress > 100){
                    progress = 100

                }
                binding.tvWaterIntake.text = waterToday.toString()+"ml/2000ml"
                binding.tvWaterProgress.text = "("+progress.toString()+"%)"
                sharedPrefIntake.edit().putInt("waterProgress", progress).apply()
                sharedPrefIntake.edit().putInt("waterIntake", waterToday).apply()

                progressBar.progress = progress



            }


        }











        return binding.root
    }
}